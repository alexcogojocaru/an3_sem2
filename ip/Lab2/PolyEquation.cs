using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    class PolyEquation : IEquation
    {
        enum EquationType
        {
            FirstDegree,
            SecondDegree
        };

        private EquationType _eqType;
        private double _x0;
        private double _x1;
        private double _x2;

        public PolyEquation(double x2, double x1, double x0)
        {
            this._x0 = x0;
            this._x1 = x1;
            this._x2 = x2;
            this._eqType = EquationType.SecondDegree;
        }

        public string Solve()
        {
            double delta = _x1 * _x1 - 4 * _x2 * _x0;
            string solution;

            if ((_x2 == 0) && (_x1 == 0) && (_x0 == 0))
            {
                throw new PolyException("O infinitate de solutii", _x2, _x1, _x0);
            }
            else if ((_x2 == 0) && (_x1 == 0) && (_x0 != 0))
            {
                throw new PolyException("Nicio solutie", _x2, _x1, _x0);
            }

            if (_x2 == 0)
            {
                _eqType = EquationType.FirstDegree;
                solution = (-_x0 / _x1).ToString();
            }
            else if (delta > 0)
            {
                double sqrtDelta = Math.Sqrt(delta);
                double sol1 = (-_x1 + sqrtDelta) / (2.0 * _x2);
                double sol2 = (-_x1 - sqrtDelta) / (2.0 * _x2);

                solution = "x1 = " + sol1.ToString() + "; x2 = " + sol2.ToString();
            }
            else
            {
                double rsol = -_x1 / (2.0 * _x2);
                double isol = Math.Sqrt(-delta) / (2.0 * _x2);

                solution = "x1 = " + rsol + " + " + isol + "i";
                solution += "; x2 = " + rsol + " - " + isol + "i";
            }

            return solution;
        }
    }
}
