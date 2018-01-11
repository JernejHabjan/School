using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.SessionState;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RESTService
{
    public partial class MainPage : System.Web.UI.Page
    {

        DataTable dt = new DataTable();
        Boolean isAdmin = false;

        protected void Page_Load(object sender, EventArgs e)
        {

            int orderID = Convert.ToInt32(Session["orderID"]);
            string googleID = Session["orderID"] as string;



            // FOR TEST PURPOSES --- TODO - REMOVE THIS AFTER DEBUGGING
            orderID = 109;
            googleID = "104967849801990887085";




            // SET LOGIN TEXT
            string sessionUserId = Session["googleID"] as string;

            if (!String.IsNullOrEmpty(sessionUserId) && sessionUserId != "")
            {

                Response.Write("Profile data restored.");
                ServicePersonData sPersonData = new ServicePersonData();
                User user = sPersonData.ReturnUser(googleID);
                loginNav.InnerText = " Pozdravljen " + user.name;

                // get role - admin and user right
                List<string> role = sPersonData.GetRole(googleID);
                int roleID = Convert.ToInt32(role[0]);
                isAdmin = Convert.ToBoolean(roleID);

                string roleName = role[1];

            }
            else
            {
                Response.Write("Login to display additional data");
            }

            // setup data table with ids
            dt.Columns.Add("orderID");
            dt.Columns.Add("fromLocationName");
            dt.Columns.Add("toLocationName");
            dt.Columns.Add("initialPrice");
            dt.Columns.Add("initialDate");
            dt.Columns.Add("initialDiscount");
            dt.Columns.Add("initialPlaneName");
            dt.Columns.Add("initialPlaneCompany");
            dt.Columns.Add("returnDate");
            dt.Columns.Add("returnDiscount");
            dt.Columns.Add("returnPrice");
            dt.Columns.Add("returnPlaneName");
            dt.Columns.Add("returnPlaneCompany");
            dt.Columns.Add("returnToLocation");
            dt.Columns.Add("returnFromLocation");

            if (isAdmin)
            {
                // set visibiliy of element to true if admin
                displayUsersData.Style.Add("visibility", "visible");


                // On postback - reload data in gridview when admin selects user and views its previous travels
                if (IsPostBack)
                {
                    Bind();
                }
            }


            
        }
     

        protected void GridViewFlights_OnRowCommand(object sender, GridViewCommandEventArgs e)
        {
            if (e.CommandName != "RemoveOrder") return;
            int orderID = Convert.ToInt32(e.CommandArgument);
            // do something
            Response.Write("Removed order with ID: " + orderID.ToString());

        }

        protected void GridViewFlights_SelectedIndexChanged(object sender, EventArgs e)
        {
            Response.Write("TODO");
        }
        private void Bind()
        {
            string selectedGoogleID = userIDDropdownList.SelectedValue;

            GridView gridView = GridView1;
            DataRow dr;

            ServiceTravelData sTravelData = new ServiceTravelData();
            List<TravelSendInfo> travelsInfo = sTravelData.ReturnTravels(selectedGoogleID);

            foreach (TravelSendInfo travelInfo in travelsInfo)
            {
                // add new data
                dr = dt.NewRow();
                dr["orderID"] = travelInfo.orderID;
                dr["fromLocationName"] = travelInfo.fromLocationName;
                dr["toLocationName"] = travelInfo.toLocationName;
                dr["initialPrice"] = travelInfo.initialPrice;
                dr["initialDate"] = travelInfo.initialDate;
                dr["initialDiscount"] = travelInfo.initialDiscount;
                dr["initialPlaneName"] = travelInfo.initialPlaneName;
                dr["initialPlaneCompany"] = travelInfo.initialPlaneCompany;
                dr["returnDate"] = travelInfo.returnDate;
                dr["returnDiscount"] = travelInfo.returnDiscount;
                dr["returnPrice"] = travelInfo.returnPrice;
                dr["returnPlaneName"] = travelInfo.returnPlaneName;
                dr["returnPlaneCompany"] = travelInfo.returnPlaneCompany;
                dr["returnToLocation"] = travelInfo.returnToLocation;
                dr["returnFromLocation"] = travelInfo.returnFromLocation;


                dt.Rows.Add(dr);
            }

            // bind data to gridView
            gridView.DataSourceID = null;
            gridView.DataSource = dt;
            gridView.DataBind();

        }

        protected void ddComapanyFilter_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (isAdmin)
            {
                Bind();
            }
            
        }

    }
   

}
 