using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Vaja2;

namespace WebApplication1
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void plus_Click(object sender, EventArgs e)
        {
            result.Text = 
                (Convert.ToDouble(val1.Text) + Convert.ToDouble(val2.Text)).ToString();
        }

        protected void minus_Click(object sender, EventArgs e)
        {
            result.Text =
                (Convert.ToDouble(val1.Text) - Convert.ToDouble(val2.Text)).ToString();
        }

        protected void times_Click(object sender, EventArgs e)
        {
            result.Text =
                (Convert.ToDouble(val1.Text) * Convert.ToDouble(val2.Text)).ToString();
        }

        protected void divide_Click(object sender, EventArgs e)
        {
            result.Text =
                (Convert.ToDouble(val1.Text) / Convert.ToDouble(val2.Text)).ToString();
        }

        protected void clear_Click(object sender, EventArgs e)
        {
            val1.Text = val2.Text = "";
            result.Text = "Rezultat";
        }

        protected void paramPloscina_Click(object sender, EventArgs e)
        {
            result.Text = new Pravokotnik(Convert.ToDouble(val1.Text), Convert.ToDouble(val2.Text)).
                IzracunPloscine().ToString();
        }

        protected void noParamPloscina_Click(object sender, EventArgs e)
        {
            result.Text = new Pravokotnik().IzracunPloscine().ToString();
        }
    }
}