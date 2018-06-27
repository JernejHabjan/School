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
            errorMsg.Text = "";
        }

        protected void plus_Click(object sender, EventArgs e)
        {
            result.Text = 
                (ConvertToInt32(val1.Text) + ConvertToInt32(val2.Text)).ToString();
        }

        protected void minus_Click(object sender, EventArgs e)
        {
            result.Text =
                (ConvertToInt32(val1.Text) - ConvertToInt32(val2.Text)).ToString();
        }

        protected void times_Click(object sender, EventArgs e)
        {
            result.Text =
                (ConvertToInt32(val1.Text) * ConvertToInt32(val2.Text)).ToString();
        }

        protected void divide_Click(object sender, EventArgs e)
        {
            double divison = 0;

            try
            {
                divison = ConvertToInt32(val1.Text) / ConvertToInt32(val2.Text);
            }
            catch (DivideByZeroException)
            {
                errorMsg.Text = "Zgodila se je napaka! Deljenje z 0!";
            }
            
            result.Text = divison.ToString();

        }
        
        protected void clear_Click(object sender, EventArgs e)
        {
            val1.Text = val2.Text = "";
            result.Text = "Rezultat";
        }

        protected void paramPloscina_Click(object sender, EventArgs e)
        {
            result.Text = new Pravokotnik(ConvertToInt32(val1.Text), ConvertToInt32(val2.Text)).
                IzracunPloscine().ToString();
        }

        protected void noParamPloscina_Click(object sender, EventArgs e)
        {
            result.Text = new Pravokotnik().IzracunPloscine().ToString();
        }

        private double ConvertToInt32(string text)
        {      
            if (text.Length == 0)
            {
                errorMsg.Text = "Vnosno polje je prazno!";
                return 0;
            }

            int retVal = 0;

            try
            {
                retVal = Convert.ToInt32(text);
            }
            catch (Exception)
            {
                errorMsg.Text = "V vnosnem polju ni bilo vnešeno število!";
            }
            
            return retVal;
        }
    }
}