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

            string sessionUserId = Session["googleID"] as string;


            GoogleConnect.ClientId = "331110486379-5b5815ianb75del9dflgrrqcs1lbtnva.apps.googleusercontent.com";
            GoogleConnect.ClientSecret = "aAIHWqBKYH8ayXelGqEy1foG";
            GoogleConnect.RedirectUri = Request.Url.AbsoluteUri.Split('?')[0];
   

            if (!String.IsNullOrEmpty(sessionUserId) && sessionUserId != "")
            {

                Response.Write("RESTORING PROFILE DATA:");
                ServicePersonData sPersonData = new ServicePersonData();
                User user = sPersonData.ReturnUser(Session["googleID"].ToString());

                LblName.Text = user.name;
                LblEmail.Text = user.email;
                ProfileImage.ImageUrl = "https://openclipart.org/download/247324/abstract-user-flat-1.svg";


                pnlProfile.Visible = true;
                btnLogin.Visible = false;

            }
            

            if (!string.IsNullOrEmpty(Request.QueryString["code"]))
            {

                string code = Request.QueryString["code"];
                string json = GoogleConnect.Fetch("me", code);
                GoogleProfile profile = new JavaScriptSerializer().Deserialize<GoogleProfile>(json);
                Session["googleID"] = profile.ID; // set session variable

                ServicePersonData sPersonData = new ServicePersonData();
                User user = sPersonData.ReturnUser(profile.ID);

                if (String.IsNullOrEmpty(user.googleID))
                {
                    LblName.Text = profile.DisplayName;
                    
                }
                else // if user is already written in database, it may have changed name, so read name from database and display it 
                {
                    LblName.Text = user.name;
                }

                LblEmail.Text = profile.Emails.Find(email => email.Type == "account").value;
                ProfileImage.ImageUrl = profile.Image.Url;
                pnlProfile.Visible = true;
                btnLogin.Visible = false;
                
                /*Response.Write("Current session googleID: " + Session["googleID"] + "\n");
                Response.Write("IS USER WRITTEN TO DATABASE?:" + String.IsNullOrEmpty(user.googleID));*/

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
            Session["googleID"] = "";
            GoogleConnect.Clear(Request.QueryString["code"]);
            
        }




        protected void btnLogin_Click(object sender, EventArgs e)
        {
            GoogleConnect.Authorize("profile", "email");
            //Response.Redirect("http://asistentslivko.azurewebsites.net/MainPage.aspx");
        }

        protected void ChangeInfo(object sender, EventArgs e)
        {
            Response.Redirect("http://asistentslivko.azurewebsites.net/ManageUserInfo.aspx");
        }
    }
}