using System;
using System.Collections.Generic;
using Polly.CircuitBreaker;

namespace Sandbox.VisualizeCircuitBreaker.Simulation
{
    public class SimulationState
    {
        public CircuitState CircuitState { get; set; }
        public IEnumerable<HealthCount> HealthCounts { get; set; }
        public int BucketCount { get; set; }
        public HealthCount ConsolidatedHealthCount { get; set; }
        public DateTime BlockedUntil { get; set; }

        public double? FailureRatio => ConsolidatedHealthCount == null || ConsolidatedHealthCount.Total == 0 ? 
            (double?)null : 
            (double)ConsolidatedHealthCount.Failures / ConsolidatedHealthCount.Total;
    }
}