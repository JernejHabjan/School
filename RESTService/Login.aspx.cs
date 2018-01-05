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
                Session["googleID"] = profile.ID; // set session variable
                LblName.Text = profile.DisplayName;
                LblEmail.Text = profile.Emails.Find(email => email.Type == "account").value;
                LblGender.Text = profile.Gender;
                LblType.Text = profile.ObjectType;
                ProfileImage.ImageUrl = profile.Image.Url;
                pnlProfile.Visible = true;
                btnLogin.Visible = false;

            
                ServicePersonData sPersonData = new ServicePersonData();
                User user = sPersonData.ReturnUser(profile.ID);
    
               
                Response.Write("IS USER WRITTEN TO DATABASE:");
                Response.Write(String.IsNullOrEmpty(user.googleID));
          
                if (String.IsNullOrEmpty(user.googleID))
                {
                 
                    sPersonData.AddUser(new RESTService.User
                    {
                        userID = -1,
                        roleID = 1, //regular useer
                        googleID = profile.ID,
                        name = profile.DisplayName,
                        email = profile.Emails[0].value

                    });

                }



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
            //Response.Redirect("http://asistentslivko.azurewebsites.net/MainPage.aspx");
        }

        protected void ChangeInfo(object sender, EventArgs e)
        {

        }
    }
}