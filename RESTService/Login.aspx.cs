using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using ASPSnippets.GoogleAPI;
using System.Web.Script.Serialization;
using System.Diagnostics;

namespace RESTService
{
    public partial class Login : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            
            GoogleConnect.ClientId = "331110486379-5b5815ianb75del9dflgrrqcs1lbtnva.apps.googleusercontent.com";
            GoogleConnect.ClientSecret = "aAIHWqBKYH8ayXelGqEy1foG";

            GoogleConnect.RedirectUri = Request.Url.AbsoluteUri.Split('?')[0];
         
            if (!string.IsNullOrEmpty(Request.QueryString["code"]))
            {
        
                string code = Request.QueryString["code"];
                string json = GoogleConnect.Fetch("me", code);
                GoogleProfile profile = new JavaScriptSerializer().Deserialize<GoogleProfile>(json);
                LblId.Text = profile.ID;
                LblName.Text = profile.DisplayName;
                LblEmail.Text = profile.Emails.Find(email => email.Type == "account").value;
                LblGender.Text = profile.Gender;
                LblType.Text = profile.ObjectType;
                ProfileImage.ImageUrl = profile.Image.Url;
                pnlProfile.Visible = true;
                btnLogin.Enabled = false;

                //GoogleLoginAndRegistration LoginManager = new GoogleLoginAndRegistration();
                //LoginManager.AddGUserIfNotExist(profile);
            }
            if (Request.QueryString["error"] == "access_denied")
            {
                ClientScript.RegisterClientScriptBlock(this.GetType(), "alert", "alert('Access denied.')", true);
            }
            
        }


        protected void Clear_click(object sender, EventArgs e) //clear
        {
            GoogleConnect.Clear(Request.QueryString["code"]);
        }

        protected void btnLogin_Click(object sender, EventArgs e)
        {
            GoogleConnect.Authorize("profile", "email");
        }
    }
}