using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    class TrigEquation : IEquation
    {
        public enum TrigonometricFunction
        {
            Sin,
            Cos,
            Tan
        };

        private double _arg;
        private TrigonometricFunction _function;

        public TrigEquation(TrigonometricFunction tf, double argument)
        {
            this._arg = argument;
            this._function = tf;
        }
        public string Solve()
        {
            string solution = "";

            if ((_function == TrigonometricFunction.Sin) || (_function == TrigonometricFunction.Cos))
            {
                if (Math.Abs(_arg) > 1)
                {
                    throw new TrigException("Argument invalid", _arg);
                }
            }

            if (_function == TrigonometricFunction.Sin)
            {
                solution = Math.Asin(_arg).ToString();
            }
            else if (_function == TrigonometricFunction.Cos)
            {
                solution = Math.Acos(_arg).ToString();
            }
            else // if (_function == TrigonometricFunction.Tan)
            {
                solution = Math.Atan(_arg).ToString();
            }

            return solution;
        }
    }
}
