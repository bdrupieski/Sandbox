using System;
using System.Collections.Generic;

namespace SandBox.Semantics3WithoutUsingTheirPackage
{
    public class OAuthParameters
    {
        public readonly SortedDictionary<string, string> BaseProperties = new SortedDictionary<string, string>();
        public readonly Dictionary<string, string> ExtraProperties = new Dictionary<string, string>();

        public string Callback
        {
            get => SafeGet(ExtraProperties, OAuthBase.OAuthCallbackKey);
            set => AddOrUpdate(ExtraProperties, OAuthBase.OAuthCallbackKey, value);
        }

        public string ConsumerKey
        {
            get => SafeGet(BaseProperties, OAuthBase.OAuthConsumerKeyKey);
            set => AddOrUpdate(BaseProperties, OAuthBase.OAuthConsumerKeyKey, value);
        }

        public string ConsumerSecret
        {
            get => SafeGet(ExtraProperties, OAuthBase.OAuthConsumerSecretKey);
            set => AddOrUpdate(ExtraProperties, OAuthBase.OAuthConsumerSecretKey, value);
        }

        public string Nonce
        {
            get => SafeGet(BaseProperties, OAuthBase.OAuthNonceKey);
            set => AddOrUpdate(BaseProperties, OAuthBase.OAuthNonceKey, value);
        }

        public string Scope
        {
            get => SafeGet(ExtraProperties, OAuthBase.OAuthScopeKey);
            set => AddOrUpdate(ExtraProperties, OAuthBase.OAuthScopeKey, value);
        }

        public string Signature
        {
            get => SafeGet(ExtraProperties, OAuthBase.OAuthSignatureKey);
            set => AddOrUpdate(ExtraProperties, OAuthBase.OAuthSignatureKey, value);
        }

        public string SignatureMethod
        {
            get => SafeGet(BaseProperties, OAuthBase.OAuthSignatureMethodKey);
            set => AddOrUpdate(BaseProperties, OAuthBase.OAuthSignatureMethodKey, value);
        }

        public string Timestamp
        {
            get => SafeGet(BaseProperties, OAuthBase.OAuthTimestampKey);
            set => AddOrUpdate(BaseProperties, OAuthBase.OAuthTimestampKey, value);
        }

        public string Token
        {
            get => SafeGet(BaseProperties, OAuthBase.OAuthTokenKey);
            set => AddOrUpdate(BaseProperties, OAuthBase.OAuthTokenKey, value);
        }

        public string TokenSecret
        {
            get => SafeGet(ExtraProperties, OAuthBase.OAuthTokenSecretKey);
            set => AddOrUpdate(ExtraProperties, OAuthBase.OAuthTokenSecretKey, value);
        }

        public string Verifier
        {
            get => SafeGet(BaseProperties, OAuthBase.OAuthVerifierKey);
            set => AddOrUpdate(BaseProperties, OAuthBase.OAuthVerifierKey, value);
        }

        protected void AddOrUpdate(IDictionary<string, string> dictionary, string key, string value)
        {
            if (dictionary.ContainsKey(key))
            {
                if (value == null)
                {
                    dictionary.Remove(key);
                }
                else
                {
                    dictionary[key] = value;
                }
            }
            else if (value != null)
            {
                dictionary.Add(key, value);
            }
        }

        protected string SafeGet(IDictionary<string, string> dictionary, string key)
        {
            return dictionary.ContainsKey(key) ? dictionary[key] : null;
        }
    }

    public class OAuth2Parameters
    {
        public OAuth2Parameters()
        {
            AccessType = "offline";
            ResponseType = "code";
            TokenType = "Bearer";
            ApprovalPrompt = "auto";
        }

        public string ClientId { get; set; }

        public string ClientSecret { get; set; }

        public string RedirectUri { get; set; }

        /// <summary>
        /// Valid values are:
        ///   * "offline" (default): token endpoint returns both an access and refresh token.
        ///   * "online": only an access token is returned by the token endpoint.
        /// </summary>
        public string AccessType { get; set; }

        /// <summary>
        /// Valid values are:
        ///   * "code" (default): retrieve a code to be exchanged for an acces token.
        ///   * "token": directly retrieve an access token from the auth endpoint.
        /// </summary>
        public string ResponseType { get; set; }

        /// <summary>
        /// Valid values are:
        ///   * "auto" (default): only show the approval prompt if the user never approved.
        ///   * "force": always show the approval prompt.
        /// </summary>
        public string ApprovalPrompt { get; set; }

        public string State { get; set; }

        public string Scope { get; set; }

        public string TokenUri { get; set; }

        public string AuthUri { get; set; }

        public string AccessCode { get; set; }

        public string AccessToken { get; set; }

        public string TokenType { get; set; }

        public string RefreshToken { get; set; }

        public DateTime TokenExpiry { get; set; }
    }
}
