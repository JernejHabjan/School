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


                user.name = "NOV NAME";
                sPersonData.UpdateUser(user);
         
                Response.Write("UPDATED USER");

                // TODOOOOOOOOOOOOOOOOOOOOOOOOOO TEMP GOOGLE ID THAT HAS TRAVELS INSERTED - FOR TESTING ---- DELETE THIS AFTER
                //sessionUserId = "5432t532g52f432g432";


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



            }
            else
            {
                Response.Write("No session id found yet");
            }

            ServiceTravelData sTravelData = new ServiceTravelData();

            /*
             
      [DataMember]
        public string googleID { get; set; }

        [DataMember]
        public string planeName { get; set; }
        [DataMember]
        public string planeCompany { get; set; }

        [DataMember]
        public string fromLocation { get; set; }
        [DataMember]
        public string toLocation { get; set; }

        [DataMember]
        public DateTime departureDate { get; set; }
        [DataMember]
        public string departureClass { get; set; }
        
        [DataMember]
        public float price { get; set; }
        [DataMember]
        public float discount { get; set; }
    
        [DataMember]
        public DateTime returnDate { get; set; }
        [DataMember]
        public string returnClass { get; set; }

        [DataMember]
        public List<string> passengerData { get; set; }*/

            // TESTING INSERT - --------------------------------------- DELETE THIS AFTER IMPLEMENTED

            sTravelData.AddTravel(new TravelReceiveInfo
            {
                googleID = "104967849801990887085",
                planeName = "jurešnik",
                planeCompany = "companyPlaneJurešnik",

                fromLocation = "grem z loga",
                toLocation = "grem na kitajsk",


                departureClass = "prvi",
                returnClass = "prvi",

                departureDate = Convert.ToDateTime("2017-12-03T00:00:00.0000000"),
                returnDate = Convert.ToDateTime("2017-12-03T00:00:00.0000000"),

                price = 243,
                discount = 33,

                passengerData = new List<string>(new string[] { "pasName1", "passSurname1", "male", "1", "pasName2", "passSurname2", "female", "1" })




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
 