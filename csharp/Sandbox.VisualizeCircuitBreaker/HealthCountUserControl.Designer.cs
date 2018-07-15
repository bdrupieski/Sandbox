namespace Sandbox.VisualizeCircuitBreaker
{
    partial class HealthCountUserControl
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

        #region Component Designer generated code

        /// <summary> 
        /// Required method for Designer support - do not modify 
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.labelSuccesses = new System.Windows.Forms.Label();
            this.labelFailures = new System.Windows.Forms.Label();
            this.labelTime = new System.Windows.Forms.Label();
            this.labelTimeDifference = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // labelSuccesses
            // 
            this.labelSuccesses.AutoSize = true;
            this.labelSuccesses.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelSuccesses.ForeColor = System.Drawing.Color.SeaGreen;
            this.labelSuccesses.Location = new System.Drawing.Point(3, 12);
            this.labelSuccesses.Name = "labelSuccesses";
            this.labelSuccesses.Size = new System.Drawing.Size(82, 24);
            this.labelSuccesses.TabIndex = 0;
            this.labelSuccesses.Text = "GREEN";
            // 
            // labelFailures
            // 
            this.labelFailures.AutoSize = true;
            this.labelFailures.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelFailures.ForeColor = System.Drawing.Color.Red;
            this.labelFailures.Location = new System.Drawing.Point(3, 35);
            this.labelFailures.Name = "labelFailures";
            this.labelFailures.Size = new System.Drawing.Size(52, 24);
            this.labelFailures.TabIndex = 1;
            this.labelFailures.Text = "RED";
            // 
            // labelTime
            // 
            this.labelTime.AutoSize = true;
            this.labelTime.Font = new System.Drawing.Font("Microsoft Sans Serif", 14.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTime.Location = new System.Drawing.Point(3, 59);
            this.labelTime.Name = "labelTime";
            this.labelTime.Size = new System.Drawing.Size(112, 24);
            this.labelTime.TabIndex = 2;
            this.labelTime.Text = "TIME_LEFT";
            // 
            // labelTimeDifference
            // 
            this.labelTimeDifference.AutoSize = true;
            this.labelTimeDifference.Font = new System.Drawing.Font("Microsoft Sans Serif", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.labelTimeDifference.Location = new System.Drawing.Point(3, 86);
            this.labelTimeDifference.Name = "labelTimeDifference";
            this.labelTimeDifference.Size = new System.Drawing.Size(160, 20);
            this.labelTimeDifference.TabIndex = 3;
            this.labelTimeDifference.Text = "TIME_DIFFERENCE";
            // 
            // HealthCountUserControl
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.Controls.Add(this.labelTimeDifference);
            this.Controls.Add(this.labelTime);
            this.Controls.Add(this.labelFailures);
            this.Controls.Add(this.labelSuccesses);
            this.Name = "HealthCountUserControl";
            this.Size = new System.Drawing.Size(66, 115);
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label labelSuccesses;
        private System.Windows.Forms.Label labelFailures;
        private System.Windows.Forms.Label labelTime;
        private System.Windows.Forms.Label labelTimeDifference;
    }
}
