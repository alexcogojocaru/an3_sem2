using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    class PolyException : Exception
    {
        private string _message;
        private double _x0;
        private double _x1;
        private double _x2;

        public PolyException(string message, double x2, double x1, double x0) : base(message)
        {
            this._message = message;
            this._x0 = x0;
            this._x1 = x1;
            this._x2 = x2;
        }

        public override string Message
        {
            get => _message;
        }
    }
}
