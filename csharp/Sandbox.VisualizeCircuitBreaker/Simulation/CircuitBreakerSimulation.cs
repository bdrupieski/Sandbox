using System;
using System.Collections.Generic;
using System.Linq;
using Fasterflect;
using Polly;
using Polly.CircuitBreaker;

namespace Sandbox.VisualizeCircuitBreaker.Simulation
{
    public class CircuitBreakerSimulation
    {
        public double FailureThreshold { get; }
        public double SamplingDurationSeconds { get; }
        public int MinimumThroughput { get; }
        public double DurationOfBreakSeconds { get; }

        private readonly CircuitBreakerPolicy _circuitBreaker;

        public CircuitBreakerSimulation(
            double failureThreshold, 
            double samplingDurationSeconds, 
            int minimumThroughput, 
            double durationOfBreakSeconds)
        {
            FailureThreshold = failureThreshold;
            SamplingDurationSeconds = samplingDurationSeconds;
            MinimumThroughput = minimumThroughput;
            DurationOfBreakSeconds = durationOfBreakSeconds;

            _circuitBreaker = Policy
                .Handle<BreakOnThisException>()
                .AdvancedCircuitBreaker(
                    failureThreshold, 
                    TimeSpan.FromSeconds(samplingDurationSeconds), 
                    minimumThroughput, 
                    TimeSpan.FromSeconds(durationOfBreakSeconds));
        }

        public void TriggerSuccess() => _circuitBreaker.ExecuteAndCapture(() => { });
        public void TriggerFailure() => _circuitBreaker.ExecuteAndCapture(() => throw new BreakOnThisException());

        public SimulationState GetSimulationState()
        {
            var (healthCounts, bucketCount) = GetHealthCounts();

            return new SimulationState
            {
                CircuitState = _circuitBreaker.CircuitState,
                ConsolidatedHealthCount = BuildConsolidatedHealthCount(healthCounts),
                HealthCounts = healthCounts,
                BucketCount = bucketCount,
            };
        }

        private HealthCount BuildConsolidatedHealthCount(List<HealthCount> healthCounts)
        {
            if (!healthCounts.Any())
                return null;

            return new HealthCount(
                healthCounts.Sum(x => x.Successes), 
                healthCounts.Sum(x => x.Failures), 
                healthCounts.Last().StartedAt);
        }

        private (List<HealthCount> healthCounts, int totalPossibleBuckets) GetHealthCounts()
        {
            var controller = _circuitBreaker.GetFieldValue("_breakerController");
            var metrics = controller.GetFieldValue("_metrics");
            var windows = (IEnumerable<object>)metrics.GetFieldValue("_windows");

            var array = windows.GetFieldValue("_array");
            var bucketCount = (int)array.GetPropertyValue("Length");

            var healthCounts = new List<HealthCount>();
            HealthCount previous = null;
            foreach (var window in windows)
            {
                var successes = (int)window.GetPropertyValue("Successes");
                var failures = (int)window.GetPropertyValue("Failures");
                var startedAt = new DateTime((long)window.GetPropertyValue("StartedAt"), DateTimeKind.Utc);
                var difference = startedAt - previous?.StartedAt;

                var healthCount = new HealthCount(successes, failures, startedAt, difference);

                healthCounts.Add(healthCount);
                previous = healthCount;
            }

            return (healthCounts, bucketCount);
        }
    }
}