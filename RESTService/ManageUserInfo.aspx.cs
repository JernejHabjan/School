using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RESTService
{
    public partial class ManageUserInfo : System.Web.UI.Page
    {


        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void b_accept_Click(object sender, EventArgs e)
        {
            // if inserted valid email and name, save profile
            if(!String.IsNullOrEmpty(email_input.Value) && !String.IsNullOrWhiteSpace(email_input.Value) && !String.IsNullOrEmpty(name_input.Value) && !String.IsNullOrWhiteSpace(name_input.Value))
            {
                ServicePersonData sPersonData = new ServicePersonData();
                User user = sPersonData.ReturnUser(Session["googleID"].ToString());
                user.email = email_input.Value;
                user.name = name_input.Value;

                sPersonData.UpdateUser(user/*, user.googleID*/);


                Response.Redirect("http://asistentslivko.azurewebsites.net/Login.aspx");
            }
            else
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Vnesite nov email in prikazno ime')", true);
            }

            
            
        }

        protected void b_cancel_Click(object sender, EventArgs e)
        {
            Response.Redirect("http://asistentslivko.azurewebsites.net/Login.aspx");
        }
    }
}