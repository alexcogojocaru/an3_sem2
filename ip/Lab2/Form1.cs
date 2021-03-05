using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApp1
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void groupBox1_Enter(object sender, EventArgs e)
        {

        }

        private void radioButton1_CheckedChanged_1(object sender, EventArgs e)
        {
            if (eqPolyRadioButton.Checked == true)
            {
                eqPolyRadioButton.Checked = false;
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void trigEqComboBox_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void calculateButton_Click(object sender, EventArgs e)
        {
            IEquation eq;

            if (eqPolyRadioButton.Checked == true)
            {
                double x2 = Double.Parse(textBox1.Text);
                double x1 = Double.Parse(textBox2.Text);
                double x0 = Double.Parse(textBox3.Text);

                eq = new PolyEquation(x2, x1, x0);

                solutionTextBox.Text = eq.Solve();
            }
            else if (eqTrigRadioButton.Checked == true)
            {
                double arg = Double.Parse(textBox4.Text);
                TrigEquation.TrigonometricFunction func = (TrigEquation.TrigonometricFunction)trigEqComboBox.SelectedItem;

                eq = new TrigEquation(func, arg);

                solutionTextBox.Text = eq.Solve();
            }

            //try
            //{
            //    if (eqPolyRadioButton.Checked == true)
            //    {
            //        double x2 = Double.Parse(textBox1.Text);
            //        double x1 = Double.Parse(textBox2.Text);
            //        double x0 = Double.Parse(textBox3.Text);

            //        eq = new PolyEquation(x2, x1, x0);

            //        solutionTextBox.Text = eq.Solve();
            //    }
            //    else if (eqTrigRadioButton.Checked == true)
            //    {
            //        double arg = Double.Parse(textBox4.Text);
            //        TrigEquation.TrigonometricFunction func = (TrigEquation.TrigonometricFunction)trigEqComboBox.SelectedItem;

            //        eq = new TrigEquation(func, arg);

            //        solutionTextBox.Text = eq.Solve();
            //    }
            //}
            //catch (PolyException ex)
            //{
            //    solutionTextBox.Text = ex.Message;
            //}
            //catch (TrigException ex)
            //{
            //    solutionTextBox.Text = ex.Message;
            //}
            //catch (System.FormatException ex)
            //{
            //    solutionTextBox.Text = "Campuri goale";
            //}
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void eqPolyRadioButton_CheckedChanged(object sender, EventArgs e)
        {
            if (eqTrigRadioButton.Checked == true)
            {
                eqTrigRadioButton.Checked = false;
            }
        }
    }
}
