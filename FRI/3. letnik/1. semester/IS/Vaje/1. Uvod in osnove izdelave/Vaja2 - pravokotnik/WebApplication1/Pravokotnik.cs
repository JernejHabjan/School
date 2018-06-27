using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Vaja2
{
    public class Pravokotnik
    {
        private double sirina;
        private double visina;

        public Pravokotnik()
        {
            sirina = visina = 5;
        }

        public Pravokotnik(double sirina, double visina)
        {
            this.sirina = sirina;
            this.visina = visina;
        }

        public double IzracunObsega()
        {
            return 2*sirina + 2*visina;
        }

        public double IzracunPloscine()
        {
            return sirina*visina;
        }

        public void DolociVision(double visina)
        {
            this.visina = visina;
        }

        public void DolociSirino(double sirina)
        {
            this.sirina = sirina;
        }
    }
}