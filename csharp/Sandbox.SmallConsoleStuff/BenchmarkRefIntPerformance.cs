using System;
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
            Console.WriteLine($"{BenchmarkUtils.Benchmark(FastFormatRef, _numberOfTimes)} ms for {nameof(FastFormatRef)}");
            Console.WriteLine($"{BenchmarkUtils.Benchmark(FastFormatHardcodedIndexes, _numberOfTimes)} ms for {nameof(FastFormatHardcodedIndexes)}");
            Console.WriteLine();
        }

        private static void Ref_Versus_NoRef()
        {
            // No material difference between these two.
            Console.WriteLine($"{BenchmarkUtils.Benchmark(FastFormatRef, _numberOfTimes)} ms for {nameof(FastFormatRef)}");
            Console.WriteLine($"{BenchmarkUtils.Benchmark(FastFormatNoRef, _numberOfTimes)} ms for {nameof(FastFormatNoRef)}");
            Console.WriteLine();
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
    }
}