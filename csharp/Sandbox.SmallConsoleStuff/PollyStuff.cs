using System;
using Polly;

namespace Sandbox.SmallConsoleStuff
{
    public static class PollyStuff
    {
        public static void DoIt()
        {
            var circuitBreaker = Policy
                .Handle<Exception>()
                .AdvancedCircuitBreaker(0.50, TimeSpan.FromSeconds(10), 10, TimeSpan.FromSeconds(3));

            for (int i = 0; i < 10; i++)
            {
                circuitBreaker.Execute(() => { });
            }
        }
    }
}