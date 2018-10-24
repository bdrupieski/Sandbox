using System;
using System.Diagnostics;
using Sandbox.SmallConsoleStuff.RefIntFastDateTimeFormatBenchmark;

namespace Sandbox.SmallConsoleStuff
{
    public static class BenchmarkRefIntPerformance
    {
        private static readonly DateTime _dateToUse = new DateTime(2018, 10, 24, 12, 34, 22);
        private static int _numberOfTimes = 15_000_000;

        public static void DoIt()
        {
            HardcodedIndexes_Versus_IncrementedSoNoLeadingZeroes();
        }

        private static void HardcodedIndexes_Versus_IncrementedSoNoLeadingZeroes()
        {
            // In release mode with optimizations on, hard-coding the char[] indexes takes
            // about 90% as long as incrementing an integer index to account for trailing zeroes.
            // Not really worth it.
            System.Console.WriteLine($"{Benchmark(FastFormatRef)} ms for {nameof(FastFormatRef)}");
            System.Console.WriteLine($"{Benchmark(FastFormatHardcodedIndexes)} ms for {nameof(FastFormatHardcodedIndexes)}");
            System.Console.WriteLine();
        }

        private static void Ref_Versus_NoRef()
        {
            // No material difference between these two.
            System.Console.WriteLine($"{Benchmark(FastFormatRef)} ms for {nameof(FastFormatRef)}");
            System.Console.WriteLine($"{Benchmark(FastFormatNoRef)} ms for {nameof(FastFormatNoRef)}");
            System.Console.WriteLine();
        }

        private static void FastFormatHardcodedIndexes()
        {
            FastDateTimeFormatHardcodedIndexes.FastFormat(_dateToUse);
        }

        private static void FastFormatRef()
        {
            FastDateTimeFormatRef.FastFormat(_dateToUse);
        }

        private static void FastFormatNoRef()
        {
            FastDateTimeFormatNoRef.FastFormat(_dateToUse);
        }

        private static long Benchmark(Action action)
        {
            // warm up, do any JIT magic I guess if that happens
            for (int i = 0; i < 100; i++)
            {
                action();
            }

            var sw = Stopwatch.StartNew();
            for (int i = 0; i < _numberOfTimes; i++)
            {
                action();
            }
            sw.Stop();
            return sw.ElapsedMilliseconds;
        }
    }
}