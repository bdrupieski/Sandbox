using System;
using System.Collections.Generic;
using System.Diagnostics;
using Jil;
using Newtonsoft.Json;
using Utf8Json.Resolvers;
using JsonSerializer = Utf8Json.JsonSerializer;

namespace Sandbox.JsonSerializationBenchmark
{
    class Program
    {
        private static int _numberOfTimes = 10_000;

        static void Main(string[] args)
        {
            var p = new Person();
            p.Name = "Brian";
            p.Age = 32;
            p.BunchOfStuff = new List<List<object>>
            {
                new List<object>(),
                new List<object>(),
            };

            for (int i = 0; i < 100; i++)
            {
                p.BunchOfStuff.Add(new List<object>
                {
                    new object[] { 3, 41234123478L, 23.4D, 0.32f, 9.99m },
                    new object[] { "hello", "goodbye", 'a', 'z', 1, 2, 3 },
                });
            }

            Console.WriteLine($"{Benchmark(() => Utf8Json(p))} ms for {nameof(Utf8Json)}");
            Console.WriteLine($"{Benchmark(() => Jil(p))} ms for {nameof(Jil)}");
            Console.WriteLine($"{Benchmark(() => Newtonsoft(p))} ms for {nameof(Newtonsoft)}");
        }

        private static string Utf8Json(Person p)
        {
            return JsonSerializer.ToJsonString(p, StandardResolver.CamelCase);
        }

        private static string Jil(Person p)
        {
            return JSON.SerializeDynamic(p, Options.CamelCase);
        }

        private static string Newtonsoft(Person p)
        {
            return JsonConvert.SerializeObject(p);
        }

        private static long Benchmark(Action action)
        {
            // warm up
            for (int i = 0; i < 100; i++)
                action();

            var sw = Stopwatch.StartNew();

            for (int i = 0; i < _numberOfTimes; i++)
                action();

            sw.Stop();
            return sw.ElapsedMilliseconds;
        }
    }

    public class Person
    {
        public string Name { get; set; }
        public int Age { get; set; }
        public List<List<object>> BunchOfStuff { get; set; }
    }
}
