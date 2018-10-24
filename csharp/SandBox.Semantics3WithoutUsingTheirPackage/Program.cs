using System;
using System.Collections.Generic;
using System.Net;
using Newtonsoft.Json;

namespace SandBox.Semantics3WithoutUsingTheirPackage
{
    class Program
    {
        static void Main(string[] args)
        {
            var uri = new Uri(@"https://api.semantics3.com/v1/products?q={""upc"":""0046500733437""}");

            var client = new WebClient();
            string header = OAuthUtil.GenerateHeader(uri, "consumerkey", "consumersecret", null, null, "GET");
            client.Headers.Add(header);

            var content = client.DownloadString(uri);

            var response = JsonConvert.DeserializeObject<Semantics3Response>(content);

            Console.WriteLine(content);
        }
    }

    public class Semantics3Response
    {
        [JsonProperty("code")]
        public string Code { get; set; }

        [JsonProperty("offset")]
        public int Offset { get; set; }

        [JsonProperty("results")]
        public List<Semantics3Result> Results { get; set; }

        [JsonProperty("results_count")]
        public int ResultsCount { get; set; }

        [JsonProperty("total_results_count")]
        public int TotalResultsCount { get; set; }
    }

    public class Semantics3Result
    {
        public string Brand { get; set; }
        public string Category { get; set; }
        public string Description { get; set; }
        public string Ean { get; set; }
        public string Manufacturer { get; set; }
        public string Model { get; set; }
        public string Mpn { get; set; }
        public string Name { get; set; }
        public double Price { get; set; }
        public string Upc { get; set; }
    }
}