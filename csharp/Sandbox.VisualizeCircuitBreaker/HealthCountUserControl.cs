using System;
using System.Windows.Forms;
using Sandbox.VisualizeCircuitBreaker.Simulation;

namespace Sandbox.VisualizeCircuitBreaker
{
    public sealed partial class HealthCountUserControl : UserControl
    {
        public bool ActLikeConsolidatedHealthCount { get; set; }

        public HealthCountUserControl()
        {
            InitializeComponent();
        }

        public void UpdateBasedOnHealthCount(CircuitBreakerSimulation circuitBreakerSimulation, HealthCount healthCount)
        {
            Visible = healthCount != null;

            var timeSinceStarted = DateTime.UtcNow - healthCount?.StartedAt;
            var timeLeftBeforeDropped = TimeSpan.FromSeconds(circuitBreakerSimulation.SamplingDurationSeconds) - timeSinceStarted;

            labelSuccesses.Text = healthCount?.Successes.ToString();
            labelFailures.Text = healthCount?.Failures.ToString();

            if (ActLikeConsolidatedHealthCount)
            {
                labelTime.Visible = false;
            }
            else
            {
                var secondsAfterPreviousBucket = healthCount?.DifferenceFromNextHealthCount?.TotalSeconds.ToString("#.##") ?? string.Empty;

                var displayTime = timeLeftBeforeDropped != null && timeLeftBeforeDropped > TimeSpan.Zero
                    ? timeLeftBeforeDropped.Value
                    : TimeSpan.Zero;

                labelTime.Text = displayTime.ToString("s\\.f");
            }
        }
    }
}
