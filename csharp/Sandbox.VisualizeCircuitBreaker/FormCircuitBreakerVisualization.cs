using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Windows.Forms;
using MoreLinq.Extensions;
using Polly.CircuitBreaker;
using Sandbox.VisualizeCircuitBreaker.Simulation;

namespace Sandbox.VisualizeCircuitBreaker
{
    public partial class FormCircuitBreakerVisualization : Form
    {
        private CircuitBreakerSimulation _circuitBreakerSimulation;
        private List<HealthCountUserControl> _healthCountQueue;

        public FormCircuitBreakerVisualization()
        {
            InitializeComponent();
            StartNewSimulation();

            timerCheckOnCircuitBreaker.Tick += TimerCheckOnCircuitBreaker_Tick;
            timerCheckOnCircuitBreaker.Start();
        }

        private void StartNewSimulation()
        {
            _circuitBreakerSimulation = BuildSimulationFromParameters();
            UpdateViewOfSimulationState();
        }

        private CircuitBreakerSimulation BuildSimulationFromParameters()
        {
            var failureThreshold = (double)numericUpDownFailureThreshold.Value;
            var samplingDurationSeconds = (double)numericUpDownSamplingDurationSeconds.Value;
            var minimumThroughput = (int)numericUpDownMinimumThroughput.Value;
            var breakSeconds = (double)numericUpDownDurationBreakSeconds.Value;

            return new CircuitBreakerSimulation(failureThreshold, samplingDurationSeconds, minimumThroughput, breakSeconds);
        }

        private void MakeSureHealthCountUserControlQueueIsBuiltAndHasTheRightNumberOfThingsInIt(SimulationState simulationState)
        {
            if (_healthCountQueue == null || _healthCountQueue.Count != simulationState.BucketCount)
            {
                _healthCountQueue = Enumerable
                    .Range(1, simulationState.BucketCount)
                    .Select(x => new HealthCountUserControl { Dock = DockStyle.Right })
                    .ToList();

                var controls = _healthCountQueue.OfType<Control>().ToArray();

                tableLayoutPanelHealthCounts.SuspendLayout();
                tableLayoutPanelHealthCounts.Controls.Clear();
                tableLayoutPanelHealthCounts.Controls.AddRange(controls);
                tableLayoutPanelHealthCounts.ResumeLayout();
                tableLayoutPanelHealthCounts.PerformLayout();
            }
        }

        private void UpdateViewOfSimulationState()
        {
            var simulationState = _circuitBreakerSimulation.GetSimulationState();

            MakeSureHealthCountUserControlQueueIsBuiltAndHasTheRightNumberOfThingsInIt(simulationState);
            UpdateCircuitStateLabel(simulationState);
            UpdateConsolidatedHealthCountView(simulationState);

            var healthCountsAndControls = _healthCountQueue.ZipLongest(simulationState.HealthCounts, (control, count) => (control, count));
            foreach (var (healthCountControl, healthCount) in healthCountsAndControls)
            {
                healthCountControl.UpdateBasedOnHealthCount(_circuitBreakerSimulation, healthCount);
            }
        }

        private void UpdateConsolidatedHealthCountView(SimulationState simulationState)
        {
            labelTotalSuccessCount.Text = simulationState.ConsolidatedHealthCount?.Successes.ToString();
            labelTotalFailureCount.Text = simulationState.ConsolidatedHealthCount?.Failures.ToString();

            labelTotalFailureRate.Text = simulationState.FailureRatio?.ToString("#.##");
        }

        private void UpdateCircuitStateLabel(SimulationState simulationState)
        {
            string text = string.Empty;
            Color textColor = Color.Black;

            var circuitState = simulationState.CircuitState;
            if (circuitState == CircuitState.Closed)
            {
                text = "Closed";
            }
            else if (circuitState == CircuitState.HalfOpen)
            {
                text = "Half Open";
                textColor = Color.Gold;
            }
            else if (circuitState == CircuitState.Isolated)
            {
                text = "Isolated";
            }
            else if (circuitState == CircuitState.Open)
            {
                var timeRemainingOpen = (simulationState.BlockedUntil - DateTime.UtcNow).TotalSeconds;

                text = $"Open {timeRemainingOpen:#.##}";
                textColor = Color.Red;
            }

            labelCircuitStateValue.ForeColor = textColor;
            labelCircuitStateValue.Text = text;
        }

        private void TimerCheckOnCircuitBreaker_Tick(object sender, EventArgs e)
        {
            UpdateViewOfSimulationState();
        }

        private void buttonSuccess_Click(object sender, EventArgs e)
        {
            _circuitBreakerSimulation.TriggerSuccess();

            UpdateViewOfSimulationState();
        }

        private void buttonFailure_Click(object sender, EventArgs e)
        {
            _circuitBreakerSimulation.TriggerFailure();

            UpdateViewOfSimulationState();
        }

        private void buttonStartSimulation_Click(object sender, EventArgs e)
        {
            StartNewSimulation();
        }

        private void FormCircuitBreakerVisualization_KeyDown(object sender, KeyEventArgs e)
        {
            if (e.KeyCode == Keys.S)
            {
                _circuitBreakerSimulation.TriggerSuccess();
            }
            else if (e.KeyCode == Keys.F)
            {
                _circuitBreakerSimulation.TriggerFailure();
            }

            UpdateViewOfSimulationState();
        }
    }
}
