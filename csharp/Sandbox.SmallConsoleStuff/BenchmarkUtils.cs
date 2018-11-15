using System;
using System.Diagnostics;

namespace Sandbox.SmallConsoleStuff
{
    public static class BenchmarkUtils
    {
        public static long Benchmark(Action action, int numberofTimes)
        {
            // warm up, do any JIT magic I guess if that happens
            for (int i = 0; i < 100; i++)
            {
                action();
            }

            var sw = Stopwatch.StartNew();
            for (int i = 0; i < numberofTimes; i++)
            {
                action();
            }
            sw.Stop();
            return sw.ElapsedMilliseconds;
        }
    }
}