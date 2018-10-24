using System;
using System.Text;

namespace SandBox.Semantics3WithoutUsingTheirPackage
{
    public class OAuthUtil
    {
        public static string GenerateHeader(Uri uri, string consumerKey, string consumerSecret, string token, string tokenSecret, string httpMethod)
        {
            var parameters = new OAuthParameters
            {
                ConsumerKey = consumerKey,
                ConsumerSecret = consumerSecret,
                Token = token,
                TokenSecret = tokenSecret,
                SignatureMethod = OAuthBase.HmacSha1SignatureType
            };

            return GenerateHeader(uri, httpMethod, parameters);
        }

        public static string GenerateHeader(Uri uri, string httpMethod, OAuthParameters parameters)
        {
            parameters.Timestamp = OAuthBase.GenerateTimeStamp();
            parameters.Nonce = OAuthBase.GenerateNonce();

            string signature = OAuthBase.GenerateSignature(uri, httpMethod, parameters);

            var sb = new StringBuilder();
            sb.Append($@"Authorization: OAuth {OAuthBase.OAuthVersionKey}=""{OAuthBase.OAuthVersion}"",");
            sb.Append($@"{OAuthBase.OAuthNonceKey}=""{OAuthBase.EncodingPerRfc3986(parameters.Nonce)}"",");
            sb.Append($@"{OAuthBase.OAuthTimestampKey}=""{OAuthBase.EncodingPerRfc3986(parameters.Timestamp)}"",");
            sb.Append($@"{OAuthBase.OAuthConsumerKeyKey}=""{OAuthBase.EncodingPerRfc3986(parameters.ConsumerKey)}"",");

            if (parameters.BaseProperties.ContainsKey(OAuthBase.OAuthVerifierKey))
                sb.Append($@"{OAuthBase.OAuthVerifierKey}=""{OAuthBase.EncodingPerRfc3986(parameters.BaseProperties[OAuthBase.OAuthVerifierKey])}"",");

            if (!string.IsNullOrEmpty(parameters.Token))
                sb.Append($@"{OAuthBase.OAuthTokenKey}=""{OAuthBase.EncodingPerRfc3986(parameters.Token)}"",");

            if (parameters.BaseProperties.ContainsKey(OAuthBase.OAuthCallbackKey))
                sb.Append($@"{OAuthBase.OAuthCallbackKey}=""{OAuthBase.EncodingPerRfc3986(parameters.BaseProperties[OAuthBase.OAuthCallbackKey])}"",");

            sb.Append($@"{OAuthBase.OAuthSignatureMethodKey}=""{OAuthBase.HmacSha1SignatureType}"",");
            sb.Append($@"{OAuthBase.OAuthSignatureKey}=""{OAuthBase.EncodingPerRfc3986(signature)}""");

            return sb.ToString();
        }
    }
}
