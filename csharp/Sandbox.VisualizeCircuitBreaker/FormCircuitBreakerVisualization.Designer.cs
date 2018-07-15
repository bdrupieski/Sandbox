namespace Sandbox.VisualizeCircuitBreaker
{
    partial class FormCircuitBreakerVisualization
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(FormCircuitBreakerVisualization));
            this.buttonSuccess = new System.Windows.Forms.Button();
            this.buttonFailure = new System.Windows.Forms.Button();
            this.labelCircuitState = new System.Windows.Forms.Label();
            this.labelCircuitStateValue = new System.Windows.Forms.Label();
            this.timerCheckOnCircuitBreaker = new System.Windows.Forms.Timer(this.components);
            this.labelFailureThreshold = new System.Windows.Forms.Label();
            this.numericUpDownFailureThreshold = new System.Windows.Forms.NumericUpDown();
            this.groupBoxSimulationParameters = new System.Windows.Forms.GroupBox();
            this.numericUpDownSamplingDurationSeconds = new System.Windows.Forms.NumericUpDown();
            this.labelSamplingDurationSeconds = new System.Windows.Forms.Label();
            this.numericUpDownDurationBreakSeconds = new System.Windows.Forms.NumericUpDown();
            this.labelDurationBreakSeconds = new System.Windows.Forms.Label();
            this.numericUpDownMinimumThroughput = new System.Windows.Forms.NumericUpDown();
            this.labelMinimumThroughput = new System.Windows.Forms.Label();
            this.buttonStartSimulation = new System.Windows.Forms.Button();
            this.groupBoxCircuitBreakerState = new System.Windows.Forms.GroupBox();
            this.tableLayoutPanelHealthCounts = new System.Windows.Forms.TableLayoutPanel();
            this.groupBoxConsolidatedHealthCount = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.labelTotalSuccessCount = new System.Windows.Forms.Label();
            this.labelTotalFailureCount = new System.Windows.Forms.Label();
            this.labelTotalFailureRate = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownFailureThreshold)).BeginInit();
            this.groupBoxSimulationParameters.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownSamplingDurationSeconds)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownDurationBreakSeconds)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownMinimumThroughput)).BeginInit();
            this.groupBoxCircuitBreakerState.SuspendLayout();
            this.groupBoxConsolidatedHealthCount.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.SuspendLayout();
            // 
            // buttonSuccess
            // 
            this.buttonSuccess.Location = new System.Drawing.Point(494, 52);
            this.buttonSuccess.Name = "buttonSuccess";
            this.buttonSuccess.Size = new System.Drawing.Size(75, 23);
            this.buttonSuccess.TabIndex = 0;
            this.buttonSuccess.Text = "Success";
            this.buttonSuccess.UseVisualStyleBackColor = true;
            this.buttonSuccess.Click += new System.EventHandler(this.buttonSuccess_Click);
            // 
            // buttonFailure
            // 
            this.buttonFailure.Location = new System.Drawing.Point(575, 52);
            this.buttonFailure.Name = "buttonFailure";
            this.buttonFailure.Size = new System.Drawing.Size(75, 23);
            this.buttonFailure.TabIndex = 1;
            this.buttonFailure.Text = "Failure";
            this.buttonFailure.UseVisualStyleBackColor = true;
            this.buttonFailure.Click += new System.EventHandler(this.buttonFailure_Click);
            // 
            // labelCircuitState
            // 
            this.labelCircuitState.AutoSize = true;
            this.labelCircuitState.Location = new System.Drawing.Point(6, 32);
            this.labelCircuitState.Name = "labelCircuitState";
            this.labelCircuitState.Size = new System.Drawing.Size(67, 13);
            this.labelCircuitState.TabIndex = 2;
            this.labelCircuitState.Text = "Circuit State:";
            // 
            // labelCircuitStateValue
            // 
            this.labelCircuitStateValue.AutoSize = true;
            this.labelCircuitStateValue.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelCircuitStateValue.Location = new System.Drawing.Point(71, 25);
            this.labelCircuitStateValue.Name = "labelCircuitStateValue";
            this.labelCircuitStateValue.Size = new System.Drawing.Size(339, 29);
            this.labelCircuitStateValue.TabIndex = 3;
            this.labelCircuitStateValue.Text = "SOMETHING_GOES_HERE";
            // 
            // labelFailureThreshold
            // 
            this.labelFailureThreshold.AutoSize = true;
            this.labelFailureThreshold.Location = new System.Drawing.Point(256, 19);
            this.labelFailureThreshold.Name = "labelFailureThreshold";
            this.labelFailureThreshold.Size = new System.Drawing.Size(91, 13);
            this.labelFailureThreshold.TabIndex = 5;
            this.labelFailureThreshold.Text = "Failure Threshold:";
            // 
            // numericUpDownFailureThreshold
            // 
            this.numericUpDownFailureThreshold.DecimalPlaces = 2;
            this.numericUpDownFailureThreshold.Increment = new decimal(new int[] {
            1,
            0,
            0,
            131072});
            this.numericUpDownFailureThreshold.Location = new System.Drawing.Point(392, 17);
            this.numericUpDownFailureThreshold.Maximum = new decimal(new int[] {
            1,
            0,
            0,
            0});
            this.numericUpDownFailureThreshold.Name = "numericUpDownFailureThreshold";
            this.numericUpDownFailureThreshold.Size = new System.Drawing.Size(71, 20);
            this.numericUpDownFailureThreshold.TabIndex = 6;
            this.numericUpDownFailureThreshold.Value = new decimal(new int[] {
            80,
            0,
            0,
            131072});
            // 
            // groupBoxSimulationParameters
            // 
            this.groupBoxSimulationParameters.Controls.Add(this.numericUpDownSamplingDurationSeconds);
            this.groupBoxSimulationParameters.Controls.Add(this.labelSamplingDurationSeconds);
            this.groupBoxSimulationParameters.Controls.Add(this.numericUpDownDurationBreakSeconds);
            this.groupBoxSimulationParameters.Controls.Add(this.labelDurationBreakSeconds);
            this.groupBoxSimulationParameters.Controls.Add(this.numericUpDownMinimumThroughput);
            this.groupBoxSimulationParameters.Controls.Add(this.labelMinimumThroughput);
            this.groupBoxSimulationParameters.Controls.Add(this.labelFailureThreshold);
            this.groupBoxSimulationParameters.Controls.Add(this.numericUpDownFailureThreshold);
            this.groupBoxSimulationParameters.Location = new System.Drawing.Point(12, 12);
            this.groupBoxSimulationParameters.Name = "groupBoxSimulationParameters";
            this.groupBoxSimulationParameters.Size = new System.Drawing.Size(476, 75);
            this.groupBoxSimulationParameters.TabIndex = 7;
            this.groupBoxSimulationParameters.TabStop = false;
            this.groupBoxSimulationParameters.Text = "Simulation Parameters";
            // 
            // numericUpDownSamplingDurationSeconds
            // 
            this.numericUpDownSamplingDurationSeconds.DecimalPlaces = 1;
            this.numericUpDownSamplingDurationSeconds.Increment = new decimal(new int[] {
            1,
            0,
            0,
            65536});
            this.numericUpDownSamplingDurationSeconds.Location = new System.Drawing.Point(157, 17);
            this.numericUpDownSamplingDurationSeconds.Maximum = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            this.numericUpDownSamplingDurationSeconds.Name = "numericUpDownSamplingDurationSeconds";
            this.numericUpDownSamplingDurationSeconds.Size = new System.Drawing.Size(71, 20);
            this.numericUpDownSamplingDurationSeconds.TabIndex = 12;
            this.numericUpDownSamplingDurationSeconds.Value = new decimal(new int[] {
            20,
            0,
            0,
            0});
            // 
            // labelSamplingDurationSeconds
            // 
            this.labelSamplingDurationSeconds.AutoSize = true;
            this.labelSamplingDurationSeconds.Location = new System.Drawing.Point(6, 19);
            this.labelSamplingDurationSeconds.Name = "labelSamplingDurationSeconds";
            this.labelSamplingDurationSeconds.Size = new System.Drawing.Size(145, 13);
            this.labelSamplingDurationSeconds.TabIndex = 11;
            this.labelSamplingDurationSeconds.Text = "Sampling Duration (seconds):";
            // 
            // numericUpDownDurationBreakSeconds
            // 
            this.numericUpDownDurationBreakSeconds.DecimalPlaces = 1;
            this.numericUpDownDurationBreakSeconds.Increment = new decimal(new int[] {
            1,
            0,
            0,
            65536});
            this.numericUpDownDurationBreakSeconds.Location = new System.Drawing.Point(392, 43);
            this.numericUpDownDurationBreakSeconds.Maximum = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            this.numericUpDownDurationBreakSeconds.Name = "numericUpDownDurationBreakSeconds";
            this.numericUpDownDurationBreakSeconds.Size = new System.Drawing.Size(71, 20);
            this.numericUpDownDurationBreakSeconds.TabIndex = 10;
            this.numericUpDownDurationBreakSeconds.Value = new decimal(new int[] {
            5,
            0,
            0,
            0});
            // 
            // labelDurationBreakSeconds
            // 
            this.labelDurationBreakSeconds.AutoSize = true;
            this.labelDurationBreakSeconds.Location = new System.Drawing.Point(256, 45);
            this.labelDurationBreakSeconds.Name = "labelDurationBreakSeconds";
            this.labelDurationBreakSeconds.Size = new System.Drawing.Size(130, 13);
            this.labelDurationBreakSeconds.TabIndex = 9;
            this.labelDurationBreakSeconds.Text = "Duration Break (seconds):";
            // 
            // numericUpDownMinimumThroughput
            // 
            this.numericUpDownMinimumThroughput.Location = new System.Drawing.Point(157, 43);
            this.numericUpDownMinimumThroughput.Maximum = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            this.numericUpDownMinimumThroughput.Name = "numericUpDownMinimumThroughput";
            this.numericUpDownMinimumThroughput.Size = new System.Drawing.Size(71, 20);
            this.numericUpDownMinimumThroughput.TabIndex = 8;
            this.numericUpDownMinimumThroughput.Value = new decimal(new int[] {
            4,
            0,
            0,
            0});
            // 
            // labelMinimumThroughput
            // 
            this.labelMinimumThroughput.AutoSize = true;
            this.labelMinimumThroughput.Location = new System.Drawing.Point(6, 45);
            this.labelMinimumThroughput.Name = "labelMinimumThroughput";
            this.labelMinimumThroughput.Size = new System.Drawing.Size(101, 13);
            this.labelMinimumThroughput.TabIndex = 7;
            this.labelMinimumThroughput.Text = "Minimum Threshold:";
            // 
            // buttonStartSimulation
            // 
            this.buttonStartSimulation.Location = new System.Drawing.Point(494, 21);
            this.buttonStartSimulation.Name = "buttonStartSimulation";
            this.buttonStartSimulation.Size = new System.Drawing.Size(156, 23);
            this.buttonStartSimulation.TabIndex = 8;
            this.buttonStartSimulation.Text = "Restart Simulation";
            this.buttonStartSimulation.UseVisualStyleBackColor = true;
            this.buttonStartSimulation.Click += new System.EventHandler(this.buttonStartSimulation_Click);
            // 
            // groupBoxCircuitBreakerState
            // 
            this.groupBoxCircuitBreakerState.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBoxCircuitBreakerState.Controls.Add(this.tableLayoutPanelHealthCounts);
            this.groupBoxCircuitBreakerState.Location = new System.Drawing.Point(12, 93);
            this.groupBoxCircuitBreakerState.Name = "groupBoxCircuitBreakerState";
            this.groupBoxCircuitBreakerState.Size = new System.Drawing.Size(905, 149);
            this.groupBoxCircuitBreakerState.TabIndex = 9;
            this.groupBoxCircuitBreakerState.TabStop = false;
            this.groupBoxCircuitBreakerState.Text = "Circuit Breaker Health Counts (buckets of time and what happened during that time" +
    ")";
            // 
            // tableLayoutPanelHealthCounts
            // 
            this.tableLayoutPanelHealthCounts.ColumnCount = 1;
            this.tableLayoutPanelHealthCounts.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelHealthCounts.Dock = System.Windows.Forms.DockStyle.Fill;
            this.tableLayoutPanelHealthCounts.GrowStyle = System.Windows.Forms.TableLayoutPanelGrowStyle.AddColumns;
            this.tableLayoutPanelHealthCounts.Location = new System.Drawing.Point(3, 16);
            this.tableLayoutPanelHealthCounts.Name = "tableLayoutPanelHealthCounts";
            this.tableLayoutPanelHealthCounts.RowCount = 1;
            this.tableLayoutPanelHealthCounts.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.tableLayoutPanelHealthCounts.Size = new System.Drawing.Size(899, 130);
            this.tableLayoutPanelHealthCounts.TabIndex = 10;
            // 
            // groupBoxConsolidatedHealthCount
            // 
            this.groupBoxConsolidatedHealthCount.Controls.Add(this.labelTotalFailureRate);
            this.groupBoxConsolidatedHealthCount.Controls.Add(this.labelTotalFailureCount);
            this.groupBoxConsolidatedHealthCount.Controls.Add(this.labelTotalSuccessCount);
            this.groupBoxConsolidatedHealthCount.Location = new System.Drawing.Point(923, 93);
            this.groupBoxConsolidatedHealthCount.Name = "groupBoxConsolidatedHealthCount";
            this.groupBoxConsolidatedHealthCount.Size = new System.Drawing.Size(74, 149);
            this.groupBoxConsolidatedHealthCount.TabIndex = 10;
            this.groupBoxConsolidatedHealthCount.TabStop = false;
            this.groupBoxConsolidatedHealthCount.Text = "Total";
            // 
            // groupBox2
            // 
            this.groupBox2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.groupBox2.Controls.Add(this.labelCircuitState);
            this.groupBox2.Controls.Add(this.labelCircuitStateValue);
            this.groupBox2.Location = new System.Drawing.Point(656, 12);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(341, 75);
            this.groupBox2.TabIndex = 11;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "Circuit Breaker State";
            // 
            // labelTotalSuccessCount
            // 
            this.labelTotalSuccessCount.AutoSize = true;
            this.labelTotalSuccessCount.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTotalSuccessCount.ForeColor = System.Drawing.Color.SeaGreen;
            this.labelTotalSuccessCount.Location = new System.Drawing.Point(6, 29);
            this.labelTotalSuccessCount.Name = "labelTotalSuccessCount";
            this.labelTotalSuccessCount.Size = new System.Drawing.Size(210, 24);
            this.labelTotalSuccessCount.TabIndex = 0;
            this.labelTotalSuccessCount.Text = "TOTAL_SUCCESSES";
            // 
            // labelTotalFailureCount
            // 
            this.labelTotalFailureCount.AutoSize = true;
            this.labelTotalFailureCount.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTotalFailureCount.ForeColor = System.Drawing.Color.Red;
            this.labelTotalFailureCount.Location = new System.Drawing.Point(6, 56);
            this.labelTotalFailureCount.Name = "labelTotalFailureCount";
            this.labelTotalFailureCount.Size = new System.Drawing.Size(186, 24);
            this.labelTotalFailureCount.TabIndex = 1;
            this.labelTotalFailureCount.Text = "TOTAL_FAILURES";
            // 
            // labelTotalFailureRate
            // 
            this.labelTotalFailureRate.AutoSize = true;
            this.labelTotalFailureRate.Font = new System.Drawing.Font("Microsoft Sans Serif", 15.75F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTotalFailureRate.Location = new System.Drawing.Point(7, 93);
            this.labelTotalFailureRate.Name = "labelTotalFailureRate";
            this.labelTotalFailureRate.Size = new System.Drawing.Size(180, 25);
            this.labelTotalFailureRate.TabIndex = 2;
            this.labelTotalFailureRate.Text = "FAILURE_RATE";
            // 
            // FormCircuitBreakerVisualization
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1009, 254);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBoxConsolidatedHealthCount);
            this.Controls.Add(this.groupBoxCircuitBreakerState);
            this.Controls.Add(this.buttonStartSimulation);
            this.Controls.Add(this.groupBoxSimulationParameters);
            this.Controls.Add(this.buttonFailure);
            this.Controls.Add(this.buttonSuccess);
            this.DoubleBuffered = true;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.KeyPreview = true;
            this.Name = "FormCircuitBreakerVisualization";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Circuit Breaker Sliding Window Visualization";
            this.KeyDown += new System.Windows.Forms.KeyEventHandler(this.FormCircuitBreakerVisualization_KeyDown);
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownFailureThreshold)).EndInit();
            this.groupBoxSimulationParameters.ResumeLayout(false);
            this.groupBoxSimulationParameters.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownSamplingDurationSeconds)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownDurationBreakSeconds)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDownMinimumThroughput)).EndInit();
            this.groupBoxCircuitBreakerState.ResumeLayout(false);
            this.groupBoxConsolidatedHealthCount.ResumeLayout(false);
            this.groupBoxConsolidatedHealthCount.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button buttonSuccess;
        private System.Windows.Forms.Button buttonFailure;
        private System.Windows.Forms.Label labelCircuitState;
        private System.Windows.Forms.Label labelCircuitStateValue;
        private System.Windows.Forms.Timer timerCheckOnCircuitBreaker;
        private System.Windows.Forms.Label labelFailureThreshold;
        private System.Windows.Forms.NumericUpDown numericUpDownFailureThreshold;
        private System.Windows.Forms.GroupBox groupBoxSimulationParameters;
        private System.Windows.Forms.NumericUpDown numericUpDownMinimumThroughput;
        private System.Windows.Forms.Label labelMinimumThroughput;
        private System.Windows.Forms.Label labelDurationBreakSeconds;
        private System.Windows.Forms.NumericUpDown numericUpDownDurationBreakSeconds;
        private System.Windows.Forms.Label labelSamplingDurationSeconds;
        private System.Windows.Forms.NumericUpDown numericUpDownSamplingDurationSeconds;
        private System.Windows.Forms.Button buttonStartSimulation;
        private System.Windows.Forms.GroupBox groupBoxCircuitBreakerState;
        private System.Windows.Forms.TableLayoutPanel tableLayoutPanelHealthCounts;
        private System.Windows.Forms.GroupBox groupBoxConsolidatedHealthCount;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.Label labelTotalFailureCount;
        private System.Windows.Forms.Label labelTotalSuccessCount;
        private System.Windows.Forms.Label labelTotalFailureRate;
    }
}

