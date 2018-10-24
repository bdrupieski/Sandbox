using System;
using System.Collections.Generic;
using System.Globalization;
using System.Security.Cryptography;
using System.Text;
using System.Web;

namespace SandBox.Semantics3WithoutUsingTheirPackage
{
    /// <summary>
    /// Original code from http://eran.sandler.co.il/
    /// </summary>
    public class OAuthBase
    {
        public static string OAuthVersion = "1.0";
        public static string OAuthParameterPrefix = "oauth_";

        // Known and used oauth parameter names
        public static string OAuthConsumerKeyKey = "oauth_consumer_key";
        public static string OAuthConsumerSecretKey = "oauth_consumer_secret";
        public static string OAuthCallbackKey = "oauth_callback";
        public static string OAuthVersionKey = "oauth_version";
        public static string OAuthSignatureMethodKey = "oauth_signature_method";
        public static string OAuthSignatureKey = "oauth_signature";
        public static string OAuthTimestampKey = "oauth_timestamp";
        public static string OAuthNonceKey = "oauth_nonce";
        public static string OAuthTokenKey = "oauth_token";
        public static string OAuthTokenSecretKey = "oauth_token_secret";
        public static string OAuthVerifierKey = "oauth_verifier";
        public static string OAuthScopeKey = "scope";

        // Known and used OAuth 2.0 parameter names
        public static string OAuth2ClientId = "client_id";
        public static string OAuth2ClientSecret = "client_secret";
        public static string OAuth2RedirectUri = "redirect_uri";
        public static string OAuth2AccessType = "access_type";
        public static string OAuth2GrantType = "grant_type";
        public static string OAuth2ResponseType = "response_type";
        public static string OAuth2State = "state";
        public static string OAuth2ApprovalPrompt = "approval_prompt";
        public static string OAuth2AccessCode = "code";
        public static string OAuth2AccessToken = "access_token";
        public static string OAuth2TokenType = "token_type";
        public static string OAuth2RefreshToken = "refresh_token";
        public static string OAuth2ExpiresIn = "expires_in";

        public const string HmacSha1SignatureType = "HMAC-SHA1";
        public const string PlainTextSignatureType = "PLAINTEXT";
        public const string RsaSha1SignatureType = "RSA-SHA1";

        protected static string UnreservedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-_.~";

        /// <summary>
        /// Helper function to compute a hash value
        /// </summary>
        /// <param name="hashAlgorithm">The hashing algorithm used. If that algorithm needs some initialization,
        /// like HMAC and its derivatives, they should be initialized prior to passing it to this function</param>
        /// <param name="data">The data to hash</param>
        /// <returns>a Base64 string of the hash value</returns>
        private static string ComputeHash(HashAlgorithm hashAlgorithm, string data)
        {
            if (hashAlgorithm == null)
                throw new ArgumentNullException(nameof(hashAlgorithm));

            if (string.IsNullOrEmpty(data))
                throw new ArgumentNullException(nameof(data));

            byte[] dataBuffer = Encoding.ASCII.GetBytes(data);
            byte[] hashBytes = hashAlgorithm.ComputeHash(dataBuffer);

            return Convert.ToBase64String(hashBytes);
        }

        /// <summary>
        /// Internal function to parse query string parameters and merge them with an existing dictionary
        /// </summary>
        /// <param name="querystring">The query string part of the Url</param>
        /// <param name="dict">The dictionary to be merged with the query string parameters</param>
        /// <returns>A sorted dictionary with string keys and values representing the query parameters merged with
        /// the values taken from the dictionary passed as parameter</returns>
        public static SortedDictionary<string, string> GetQueryParameters(string querystring, IDictionary<string, string> dict)
        {
            if (querystring.StartsWith("?"))
                querystring = querystring.Remove(0, 1);

            SortedDictionary<string, string> result = dict == null 
                ? new SortedDictionary<string, string>()
                : new SortedDictionary<string, string>(dict);

            if (!string.IsNullOrEmpty(querystring))
            {
                string[] p = querystring.Split('&');
                foreach (string s in p)
                {
                    if (!string.IsNullOrEmpty(s))
                    {
                        if (s.IndexOf('=') > -1)
                        {
                            string[] temp = s.Split('=');

                            // now temp[1], the value, might contain encoded data, that would be double encoded later.
                            // also it MIGHT contain encoding of the lowercase kind, which throws OAUTH off
                            // the same is true for the name
                            string name = HttpUtility.UrlDecode(temp[0]);
                            string value = HttpUtility.UrlDecode(temp[1]);

                            if (result.ContainsKey(name))
                                result[name] = value;
                            else
                                result.Add(name, value);

                        }
                        else
                        {
                            result.Add(HttpUtility.UrlDecode(s), string.Empty);
                        }
                    }
                }
            }

            return result;
        }

        /// <summary>
        /// All parameter names and values are escaped using the [RFC3986]
        /// percent-encoding (%xx) mechanism. Characters not in the unreserved character
        /// MUST be encoded. Characters in the unreserved character set MUST NOT be encoded.
        /// Hexadecimal characters in encodings MUST be upper case. Text names and values MUST be
        /// encoded as UTF-8 octets before percent-encoding them per [RFC3629]
        /// </summary>
        /// <param name="value">The value to Url encode</param>
        /// <returns>Returns a Url encoded string</returns>
        public static string EncodingPerRfc3986(string value)
        {
            if (string.IsNullOrEmpty(value))
                return string.Empty;

            StringBuilder result = new StringBuilder();

            foreach (char symbol in value)
            {
                if (UnreservedChars.IndexOf(symbol) != -1)
                {
                    result.Append(symbol);
                }
                else
                {
                    result.Append(PercentEncode(symbol.ToString()));
                }
            }

            return result.ToString();
        }

        public static string PercentEncode(string value)
        {
            var bytes = Encoding.UTF8.GetBytes(value);
            var sb = new StringBuilder();
            foreach (var b in bytes)
            {
                // Support proper encoding of special characters (\n\r\t\b)
                if ((b > 7 && b < 11) || b == 13)
                {
                    sb.Append($"%0{b:X}");
                }
                else
                {
                    sb.Append($"%{b:X}");
                }
            }
            return sb.ToString();
        }


        /// <summary>
        /// Normalizes the request parameters according to the spec for the signature generation.
        /// </summary>
        /// <param name="parameters">The sorted dictionary containing parameters</param>
        /// <returns>a string representing the normalized parameters</returns>
        protected static string NormalizeRequestParameters(SortedDictionary<string, string> parameters)
        {
            if (parameters.Count == 0)
                return "";

            bool first = true;
            StringBuilder sb = new StringBuilder();
            foreach (KeyValuePair<string, string> p in parameters)
            {
                if (!first)
                {
                    sb.Append("&");
                }
                first = false;

                sb.AppendFormat(CultureInfo.InvariantCulture,
                    "{0}={1}",
                    EncodingPerRfc3986(p.Key),
                    EncodingPerRfc3986(p.Value));
            }

            return sb.ToString();
        }

        /// <summary>
        /// Generate the signature base that is used to produce the signature
        /// </summary>
        /// <param name="url">The full url that needs to be signed including its non OAuth url parameters</param>
        /// <param name="httpMethod">The http method used. Must be a valid HTTP method verb (POST,GET,PUT, etc)</param>
        /// <param name="parameters">The OAuth parameters</param>
        /// <returns>The signature base</returns>
        public static string GenerateSignatureBase(Uri url, string httpMethod, OAuthParameters parameters)
        {
            if (string.IsNullOrEmpty(parameters.ConsumerKey))
                throw new ArgumentException("consumerKey");

            if (string.IsNullOrEmpty(httpMethod))
                throw new ArgumentException(nameof(httpMethod));

            if (string.IsNullOrEmpty(parameters.SignatureMethod))
                throw new ArgumentException("signatureMethod");

            SortedDictionary<string, string> allParameters = GetQueryParameters(url.Query, parameters.BaseProperties);

            if (!allParameters.ContainsKey(OAuthVersionKey))
                allParameters.Add(OAuthVersionKey, OAuthVersion);

            var normalizedUrl = $"{url.Scheme}://{url.Host}";
            if (!(url.Scheme == "http" && url.Port == 80 || url.Scheme == "https" && url.Port == 443))
            {
                normalizedUrl += ":" + url.Port;
            }
            normalizedUrl += url.AbsolutePath;
            var normalizedRequestParameters = NormalizeRequestParameters(allParameters);

            StringBuilder signatureBase = new StringBuilder();
            signatureBase.AppendFormat(CultureInfo.InvariantCulture, "{0}&", httpMethod.ToUpper());
            signatureBase.AppendFormat(CultureInfo.InvariantCulture, "{0}&", EncodingPerRfc3986(normalizedUrl));
            signatureBase.AppendFormat(CultureInfo.InvariantCulture, "{0}", EncodingPerRfc3986(normalizedRequestParameters));

            return signatureBase.ToString();
        }

        /// <summary>
        /// Generate the signature value based on the given signature base and hash algorithm
        /// </summary>
        /// <param name="signatureBase">The signature based as produced by the GenerateSignatureBase method or by any other means</param>
        /// <param name="hash">The hash algorithm used to perform the hashing. If the hashing algorithm requires initialization or a key it should be set prior to calling this method</param>
        /// <returns>A base64 string of the hash value</returns>
        public static string GenerateSignatureUsingHash(string signatureBase, HashAlgorithm hash)
        {
            return ComputeHash(hash, signatureBase);
        }

        /// <summary>
        /// Generates a signature using the specified signatureMethod
        /// </summary>
        /// <param name="url">The full url that needs to be signed including its non OAuth url parameters</param>
        /// <param name="httpMethod">The http method used. Must be a valid HTTP method verb (POST,GET,PUT, etc)</param>
        /// <param name="parameters">The OAuth parameters</param>
        /// <returns>A base64 string of the hash value</returns>
        public static string GenerateSignature(Uri url, string httpMethod, OAuthParameters parameters)
        {
            switch (parameters.SignatureMethod)
            {
                case PlainTextSignatureType:
                    return HttpUtility.UrlEncode($"{parameters.ConsumerKey}&{parameters.TokenSecret}");
                case HmacSha1SignatureType:
                    string signatureBase = GenerateSignatureBase(url, httpMethod, parameters);

                    HMACSHA1 hmacsha1 = new HMACSHA1();
                    hmacsha1.Key = Encoding.ASCII.GetBytes(GenerateOAuthSignature(parameters.ConsumerSecret, parameters.TokenSecret));

                    return GenerateSignatureUsingHash(signatureBase, hmacsha1);
                case RsaSha1SignatureType:
                    throw new NotImplementedException();
                default:
                    throw new ArgumentException("Unknown signature type");
            }
        }

        /// <summary>
        /// oauth_signature is set to the concatenated encoded values of the Consumer Secret and Token Secret,
        /// separated by a ‘&’ character (ASCII code 38), even if either secret is empty.
        /// The result MUST be encoded again.
        /// </summary>
        /// <param name="consumerSecret"></param>
        /// <param name="tokenSecret"></param>
        /// <returns></returns>
        public static string GenerateOAuthSignature(string consumerSecret, string tokenSecret)
        {
            return $"{EncodingPerRfc3986(consumerSecret)}&{(string.IsNullOrEmpty(tokenSecret) ? "" : EncodingPerRfc3986(tokenSecret))}";
        }

        /// <summary>
        /// Generate the timestamp for the signature
        /// </summary>
        /// <returns></returns>
        public static string GenerateTimeStamp()
        {
            // Default implementation of UNIX time of the current UTC time
            TimeSpan ts = DateTime.UtcNow - new DateTime(1970, 1, 1, 0, 0, 0, 0);
            string timeStamp = ts.TotalSeconds.ToString(CultureInfo.InvariantCulture);
            // remove any fractions of seconds
            int pointIndex = timeStamp.IndexOf(".", StringComparison.Ordinal);
            if (pointIndex != -1)
            {
                timeStamp = timeStamp.Substring(0, pointIndex);
            }
            return timeStamp;
        }

        /// <summary>
        /// Generate a nonce
        /// </summary>
        /// <returns></returns>
        public static string GenerateNonce()
        {
            // changed from the original oauth code to use Guid
            return Guid.NewGuid().ToString().ToLower().Replace("-", "");
        }
    }
}
