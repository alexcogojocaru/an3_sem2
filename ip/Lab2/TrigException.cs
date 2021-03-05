using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace WindowsFormsApp1
{
    class TrigException : Exception
    {
        private double _argument;
        private string _message;

        public TrigException(string message, double argument)
        {
            this._argument = argument;
            this._message = message;
        }

        public override string Message
        {
            get => _message;
        }
    }
}
