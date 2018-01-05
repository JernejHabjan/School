using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.SessionState;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RESTService
{
    public partial class MainPage : System.Web.UI.Page
    {


        protected void Page_Load(object sender, EventArgs e)
        {

            // SET LOGIN TEXT
            string sessionUserId = Session["googleID"] as string;

            if (!String.IsNullOrEmpty(sessionUserId) && sessionUserId != "")
            {

                Response.Write("RESTORING PROFILE DATA:");
                ServicePersonData sPersonData = new ServicePersonData();
                User user = sPersonData.ReturnUser(Session["googleID"].ToString());
                loginNav.InnerText = " Pozdravljen " + user.name;



                // TODOOOOOOOOOOOOOOOOOOOOOOOOOO TEMP GOOGLE ID THAT HAS TRAVELS INSERTED - FOR TESTING ---- DELETE THIS AFTER
                sessionUserId = "5432t532g52f432g432";


                /*

                // RETRIEVE FLIGHT DATA FOR USER
                ServiceTravelData sTravelData = new ServiceTravelData();
                List<TravelSendInfo> travelsInfo = sTravelData.ReturnTravels(sessionUserId);
                
                foreach(TravelSendInfo travelInfo in travelsInfo)
                {
                    Response.Write(travelInfo.initialDiscount);
                    Response.Write(travelInfo.returnFromLocation);
          
                    Display_TravelInfo(travelInfo);
                }
             */





                /*

          public string googleID { get; set; }

            [DataMember]
            public string planeName { get; set; }
            [DataMember]
            public string planeCompany { get; set; }

            [DataMember]
            public string departureName{ get; set; }
            [DataMember]
            public string arrivalName { get; set; }

            [DataMember]
            public string departureDate { get; set; }
            [DataMember]
            public string returnDate { get; set; }

            [DataMember]
            public float price { get; set; }
            [DataMember]
            public float discount { get; set; }

            [DataMember]
            public List<string> passengerData { get; set; }
                 */



            }
            else
            {
                Response.Write("No session id found yet");
            }

            ServiceTravelData sTravelData = new ServiceTravelData();
            sTravelData.AddTravel(new TravelReceiveInfo
            {
                googleID = "108315117481134440467",
                planeName = "jurešnik",
                planeCompany = "companyPlaneJurešnik",

                departureName = "grem z loga",
                arrivalName = "grem na kitajsk",

                departureDate = "12.2.2222",
                returnDate = "31.2.2223",

                price = 243,
                discount = 33,

                passengerData = new List<string>(new string[] { "pasName1", "passSurname1", "passgender1", "passAge1", "pasName2", "passSurname2", "passgender2", "passAge2" })




        });




        }
        private void Display_TravelInfo(TravelSendInfo travelInfo)
        {
            // TODO




        }

        protected void SqlDataSource1_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
        {

        }
    }
   

}