using System;

namespace WindowsFormsApp1
{
    partial class Form1
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
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.solutionTextBox = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.textBox4 = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.trigEqComboBox = new System.Windows.Forms.ComboBox();
            this.eqTrigRadioButton = new System.Windows.Forms.RadioButton();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.resultLabel = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.textBox3 = new System.Windows.Forms.TextBox();
            this.textBox2 = new System.Windows.Forms.TextBox();
            this.textBox1 = new System.Windows.Forms.TextBox();
            this.eqPolyRadioButton = new System.Windows.Forms.RadioButton();
            this.calculateButton = new System.Windows.Forms.Button();
            this.aboutButton = new System.Windows.Forms.Button();
            this.exitButton = new System.Windows.Forms.Button();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.solutionTextBox);
            this.groupBox1.Controls.Add(this.label5);
            this.groupBox1.Location = new System.Drawing.Point(42, 287);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(550, 100);
            this.groupBox1.TabIndex = 2;
            this.groupBox1.TabStop = false;
            this.groupBox1.Enter += new System.EventHandler(this.groupBox1_Enter);
            // 
            // solutionTextBox
            // 
            this.solutionTextBox.Location = new System.Drawing.Point(28, 49);
            this.solutionTextBox.Name = "solutionTextBox";
            this.solutionTextBox.ReadOnly = true;
            this.solutionTextBox.Size = new System.Drawing.Size(483, 20);
            this.solutionTextBox.TabIndex = 1;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(25, 16);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(42, 13);
            this.label5.TabIndex = 0;
            this.label5.Text = "Solutie:";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.textBox4);
            this.groupBox2.Controls.Add(this.label4);
            this.groupBox2.Controls.Add(this.trigEqComboBox);
            this.groupBox2.Controls.Add(this.eqTrigRadioButton);
            this.groupBox2.Location = new System.Drawing.Point(42, 151);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(550, 100);
            this.groupBox2.TabIndex = 3;
            this.groupBox2.TabStop = false;
            // 
            // textBox4
            // 
            this.textBox4.Location = new System.Drawing.Point(183, 53);
            this.textBox4.Name = "textBox4";
            this.textBox4.Size = new System.Drawing.Size(33, 20);
            this.textBox4.TabIndex = 10;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(160, 56);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(13, 13);
            this.label4.TabIndex = 9;
            this.label4.Text = "=";
            // 
            // trigEqComboBox
            // 
            this.trigEqComboBox.DataSource = new WindowsFormsApp1.TrigEquation.TrigonometricFunction[] {
        WindowsFormsApp1.TrigEquation.TrigonometricFunction.Sin,
        WindowsFormsApp1.TrigEquation.TrigonometricFunction.Cos,
        WindowsFormsApp1.TrigEquation.TrigonometricFunction.Tan};
            this.trigEqComboBox.FormattingEnabled = true;
            this.trigEqComboBox.DataSource = Enum.GetValues(typeof(TrigEquation.TrigonometricFunction));
            this.trigEqComboBox.Location = new System.Drawing.Point(24, 53);
            this.trigEqComboBox.Name = "trigEqComboBox";
            this.trigEqComboBox.Size = new System.Drawing.Size(121, 21);
            this.trigEqComboBox.TabIndex = 8;
            this.trigEqComboBox.SelectedIndexChanged += new System.EventHandler(this.trigEqComboBox_SelectedIndexChanged);
            // 
            // eqTrigRadioButton
            // 
            this.eqTrigRadioButton.AutoSize = true;
            this.eqTrigRadioButton.Location = new System.Drawing.Point(24, 19);
            this.eqTrigRadioButton.Name = "eqTrigRadioButton";
            this.eqTrigRadioButton.Size = new System.Drawing.Size(130, 17);
            this.eqTrigRadioButton.TabIndex = 1;
            this.eqTrigRadioButton.Text = "Ecuatie trigonometrica";
            this.eqTrigRadioButton.UseVisualStyleBackColor = true;
            this.eqTrigRadioButton.CheckedChanged += new System.EventHandler(this.radioButton1_CheckedChanged_1);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.resultLabel);
            this.groupBox3.Controls.Add(this.label3);
            this.groupBox3.Controls.Add(this.label2);
            this.groupBox3.Controls.Add(this.label1);
            this.groupBox3.Controls.Add(this.textBox3);
            this.groupBox3.Controls.Add(this.textBox2);
            this.groupBox3.Controls.Add(this.textBox1);
            this.groupBox3.Controls.Add(this.eqPolyRadioButton);
            this.groupBox3.Location = new System.Drawing.Point(42, 12);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(550, 100);
            this.groupBox3.TabIndex = 3;
            this.groupBox3.TabStop = false;
            // 
            // resultLabel
            // 
            this.resultLabel.AutoSize = true;
            this.resultLabel.Location = new System.Drawing.Point(350, 63);
            this.resultLabel.Name = "resultLabel";
            this.resultLabel.Size = new System.Drawing.Size(0, 13);
            this.resultLabel.TabIndex = 7;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(331, 63);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(13, 13);
            this.label3.TabIndex = 6;
            this.label3.Text = "=";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(225, 62);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(24, 13);
            this.label2.TabIndex = 5;
            this.label2.Text = "x + ";
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(91, 62);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(33, 13);
            this.label1.TabIndex = 4;
            this.label1.Text = "x^2 +";
            this.label1.Click += new System.EventHandler(this.label1_Click);
            // 
            // textBox3
            // 
            this.textBox3.Location = new System.Drawing.Point(273, 60);
            this.textBox3.Name = "textBox3";
            this.textBox3.Size = new System.Drawing.Size(52, 20);
            this.textBox3.TabIndex = 3;
            // 
            // textBox2
            // 
            this.textBox2.Location = new System.Drawing.Point(153, 60);
            this.textBox2.Name = "textBox2";
            this.textBox2.Size = new System.Drawing.Size(52, 20);
            this.textBox2.TabIndex = 2;
            // 
            // textBox1
            // 
            this.textBox1.Location = new System.Drawing.Point(28, 60);
            this.textBox1.Name = "textBox1";
            this.textBox1.Size = new System.Drawing.Size(52, 20);
            this.textBox1.TabIndex = 1;
            this.textBox1.TextChanged += new System.EventHandler(this.textBox1_TextChanged);
            // 
            // eqPolyRadioButton
            // 
            this.eqPolyRadioButton.AutoSize = true;
            this.eqPolyRadioButton.Checked = true;
            this.eqPolyRadioButton.Location = new System.Drawing.Point(24, 25);
            this.eqPolyRadioButton.Name = "eqPolyRadioButton";
            this.eqPolyRadioButton.Size = new System.Drawing.Size(116, 17);
            this.eqPolyRadioButton.TabIndex = 0;
            this.eqPolyRadioButton.TabStop = true;
            this.eqPolyRadioButton.Text = "Ecuatie polinomiala";
            this.eqPolyRadioButton.UseVisualStyleBackColor = true;
            this.eqPolyRadioButton.CheckedChanged += new System.EventHandler(this.eqPolyRadioButton_CheckedChanged);
            // 
            // calculateButton
            // 
            this.calculateButton.Location = new System.Drawing.Point(633, 141);
            this.calculateButton.Name = "calculateButton";
            this.calculateButton.Size = new System.Drawing.Size(132, 23);
            this.calculateButton.TabIndex = 4;
            this.calculateButton.Text = "Calculeaza";
            this.calculateButton.UseVisualStyleBackColor = true;
            this.calculateButton.Click += new System.EventHandler(this.calculateButton_Click);
            // 
            // aboutButton
            // 
            this.aboutButton.Location = new System.Drawing.Point(633, 197);
            this.aboutButton.Name = "aboutButton";
            this.aboutButton.Size = new System.Drawing.Size(132, 23);
            this.aboutButton.TabIndex = 5;
            this.aboutButton.Text = "Despre";
            this.aboutButton.UseVisualStyleBackColor = true;
            // 
            // exitButton
            // 
            this.exitButton.Location = new System.Drawing.Point(633, 251);
            this.exitButton.Name = "exitButton";
            this.exitButton.Size = new System.Drawing.Size(132, 23);
            this.exitButton.TabIndex = 6;
            this.exitButton.Text = "Iesire";
            this.exitButton.UseVisualStyleBackColor = true;
            this.exitButton.Click += new System.EventHandler(this.exitButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(800, 450);
            this.Controls.Add(this.exitButton);
            this.Controls.Add(this.aboutButton);
            this.Controls.Add(this.calculateButton);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.Load += new System.EventHandler(this.Form1_Load);
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.RadioButton eqTrigRadioButton;
        private System.Windows.Forms.RadioButton eqPolyRadioButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.TextBox textBox3;
        private System.Windows.Forms.TextBox textBox2;
        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Label resultLabel;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.ComboBox trigEqComboBox;
        private System.Windows.Forms.TextBox solutionTextBox;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox textBox4;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Button calculateButton;
        private System.Windows.Forms.Button aboutButton;
        private System.Windows.Forms.Button exitButton;
    }
}

