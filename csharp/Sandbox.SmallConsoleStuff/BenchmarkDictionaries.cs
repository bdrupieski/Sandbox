using System;
using System.Collections;
using System.Collections.Concurrent;
using System.Collections.Generic;

namespace Sandbox.SmallConsoleStuff
{
    public static class BenchmarkDictionaries
    {
        public static void DoIt()
        {
            int numberOfTimes = 2000;

            Console.WriteLine($"{BenchmarkUtils.Benchmark(GenericDictionary, numberOfTimes)} ms for {nameof(GenericDictionary)}");
            Console.WriteLine($"{BenchmarkUtils.Benchmark(SortedList, numberOfTimes)} ms for {nameof(SortedList)}");
            Console.WriteLine($"{BenchmarkUtils.Benchmark(SortedDictionary, numberOfTimes)} ms for {nameof(SortedDictionary)}");
            Console.WriteLine($"{BenchmarkUtils.Benchmark(Hashtable, numberOfTimes)} ms for {nameof(Hashtable)}");
            Console.WriteLine($"{BenchmarkUtils.Benchmark(ConcurrentDictionary, numberOfTimes)} ms for {nameof(ConcurrentDictionary)}");
        }

        private static void GenericDictionary()
        {
            DoSomeStuff(new Dictionary<string, string>());
        }

        private static void SortedList()
        {
            DoSomeStuff(new SortedList());
        }

        private static void SortedDictionary()
        {
            DoSomeStuff(new SortedDictionary<string, string>());
        }

        private static void Hashtable()
        {
            DoSomeStuff(new Hashtable());
        }

        private static void ConcurrentDictionary()
        {
            DoSomeStuff(new ConcurrentDictionary<string, string>());
        }

        private static void DoSomeStuff(IDictionary dictionary)
        {
            for (int i = 0; i < 5; i++)
            {
                dictionary.Add("hello there " + i, "some other value" + i);
            }

            var keys = dictionary.Keys;

            for (int i = 0; i < 2000; i++)
            {
                foreach (var key in keys)
                {
                    var gotEm = dictionary[key];
                }
            }
        }
    }
}