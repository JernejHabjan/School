using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Vaja5
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        private int counter;
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                counter = 0;
                vrednost.Text = counter.ToString();
            }
        }

        protected void increase_Click(object sender, EventArgs e)
        {
            counter++;
            vrednost.Text = counter.ToString();
        }

        protected void decrease_Click(object sender, EventArgs e)
        {
            counter--;
            vrednost.Text = counter.ToString();
        }
    }
}