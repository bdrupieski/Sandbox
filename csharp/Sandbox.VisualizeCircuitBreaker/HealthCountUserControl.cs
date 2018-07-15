using System;
using System.Windows.Forms;
using Sandbox.VisualizeCircuitBreaker.Simulation;

namespace Sandbox.VisualizeCircuitBreaker
{
    public sealed partial class HealthCountUserControl : UserControl
    {
        public HealthCountUserControl()
        {
            InitializeComponent();
        }

        public void UpdateBasedOnHealthCount(CircuitBreakerSimulation circuitBreakerSimulation, HealthCount healthCount)
        {
            Visible = healthCount != null;

            var timeSinceStarted = DateTime.UtcNow - healthCount?.StartedAt;
            
            labelSuccesses.Text = healthCount?.Successes.ToString();
            labelFailures.Text = healthCount?.Failures.ToString();

            var secondsAfterPreviousBucket = healthCount?.DifferenceFromNextHealthCount != null
                ? healthCount.DifferenceFromNextHealthCount.Value.TotalSeconds.ToString("#.##") + " s"
                : string.Empty;

            labelTimeDifference.Text = secondsAfterPreviousBucket;

            var timeLeftBeforeDropped = TimeSpan.FromSeconds(circuitBreakerSimulation.SamplingDurationSeconds) - timeSinceStarted;
            var displayTime = timeLeftBeforeDropped != null && timeLeftBeforeDropped > TimeSpan.Zero
                ? timeLeftBeforeDropped.Value
                : TimeSpan.Zero;

            labelTime.Text = displayTime.ToString("s\\.f");
        }
    }
}
