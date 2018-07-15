using System;

namespace Sandbox.VisualizeCircuitBreaker.Simulation
{
    public class HealthCount
    {
        public int Successes { get; }
        public int Failures { get; }
        public int Total => Successes + Failures;
        public DateTime StartedAt { get; }
        public TimeSpan? DifferenceFromNextHealthCount { get; }

        public HealthCount(int successes, int failures, DateTime startedAt, TimeSpan? difference = null)
        {
            Successes = successes;
            Failures = failures;
            StartedAt = startedAt;
            DifferenceFromNextHealthCount = difference;
        }
    }
}