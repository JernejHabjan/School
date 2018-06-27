using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace WcfService1
{
    public partial class Vaja12 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        protected void Button1_Click(object sender, EventArgs e)
        {
            var client = new ServiceReference1.Service1Client();
            Label1.Text = client.vrniIme(Convert.ToInt16(TextBox1.Text));
            client.Close();
        }
    }
}