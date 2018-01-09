using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace RESTService
{
    public partial class Flight1 : System.Web.UI.Page
    {
        DataTable dt = new DataTable();
       

        protected void Page_Load(object sender, EventArgs e)
        {
            // FOR TEST PURPOSES ------- FIXED orderID AND googleID 
            Session["orderID"] = 108;
            Session["googleID"] = "104967849801990887085";


            // setup data table with ids
            dt.Columns.Add("passengerID");
            dt.Columns.Add("name");
            dt.Columns.Add("surname");
            dt.Columns.Add("gender");
            dt.Columns.Add("age");


        }


        protected void b_accept_Click(object sender, EventArgs e)
        {
            // if inserted valid email and name, save profile
            if (!String.IsNullOrEmpty(ime_input.Value)     && !String.IsNullOrWhiteSpace(ime_input.Value)     && 
                !String.IsNullOrEmpty(priimek_input.Value) && !String.IsNullOrWhiteSpace(priimek_input.Value) && 
                !String.IsNullOrEmpty(rojstvo_input.Value) && !String.IsNullOrWhiteSpace(rojstvo_input.Value) && 
                !String.IsNullOrEmpty(spol_input.Value)    && !String.IsNullOrWhiteSpace(spol_input.Value))
            {
                // dodamo passengerja v tabelo
                GridView gridView = GridView1;
             

               
                DataRow dr = dt.NewRow();
                dr["name"] = ime_input.Value;
                dr["surname"] = priimek_input.Value;
                dr["gender"] = spol_input.Value;
                dr["age"] = rojstvo_input.Value;
                dt.Rows.Add(dr);

                

                gridView.DataSourceID = null;
                gridView.DataSource = dt;
                gridView.DataBind();

                /* TODO ne dela še za več passengerju, prav tako se pobriše prejšni data ki je v njem*/

            }
            else
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Vnesite vse podatke')", true);
            
            }



        }

        protected void b_potrdiNarocilo_Click(object sender, EventArgs e)
        {
            // TODO SHOW DIALOG



            // WRITE TO DATABASE
            ServiceTravelData sTravelData = new ServiceTravelData();
            sTravelData.AddTravel(new TravelReceiveInfo
            {
                googleID = "104967849801990887085",
                planeName = "jurešnik",
                planeCompany = "companyPlaneJurešnik",

                fromLocation = "grem z loga",
                toLocation = "grem na kitajsk",


                departureClass = "prvi",
                returnClass = "prvi",

                departureDate = "3.12.2017",
                returnDate = "3.12.2017",

                price = 243,
                discount = 33,

                passengerData = "pasName1$$$passSurname1$$$male$$$1$$$pasName2$$$passSurname2$$$female$$$1"
            });



            Response.Redirect("http://asistentslivko.azurewebsites.net/MainPage.aspx");
        }
    }
}