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
        int cena = 200;

        protected void Page_Load(object sender, EventArgs e)
        {
            // FOR TEST PURPOSES ------- FIXED orderID AND googleID 
            Session["orderID"] = 108;
            Session["googleID"] = "104967849801990887085";

        
            //if onLoad - no orderID - we didnt view previous travel - remove gridview Viewer
            if (Session["orderID"] == null || String.IsNullOrWhiteSpace(Session["orderID"].ToString()))
            {
                GridView2.Style.Add("visibility", "hidden");

            }
            else
            {
                // TODO - uncomment this after debugging - this is commented for debugging

                //inputTravelData.Style.Add("visibility", "hidden");
                //inputPassenger.Style.Add("visibility", "hidden");
                //zakljucekPlacila.Style.Add("visibility", "hidden");
            }

            // setup data table with ids
            dt.Columns.Add("passengerID");
            dt.Columns.Add("name");
            dt.Columns.Add("surname");
            dt.Columns.Add("gender");
            dt.Columns.Add("age");

            // setup default date fields

            datumPrihoda_date.Value = DateTime.Now.ToString("yyyy-MM-dd");
            datumOdhoda_date.Value = DateTime.Now.ToString("yyyy-MM-dd");

            // set kartica unchecked and dvosmerna unchecked
            karticaTable.Style.Add("visibility", "hidden");
            dvosmernaTable.Style.Add("visibility", "hidden");

            //set initial price text
            updatePrice(0); // updates price with 0
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
                DataRow dr;


                
                // ADD PREV DATA
                foreach (GridViewRow gvr in gridView.Rows)
                {
                    dr = dt.NewRow();
                    dr["name"] = gvr.Cells[gvr.GetCellIndexByFieldHandle("name")].Text;
                    dr["surname"] = gvr.Cells[gvr.GetCellIndexByFieldHandle("surname")].Text;
                    dr["gender"] = gvr.Cells[gvr.GetCellIndexByFieldHandle("gender")].Text;
                    dr["age"] = gvr.Cells[gvr.GetCellIndexByFieldHandle("age")].Text;
                    dt.Rows.Add(dr);
                }

                // add new data
                dr = dt.NewRow();
                dr["name"] = ime_input.Value;
                dr["surname"] = priimek_input.Value;
                dr["gender"] = spol_input.Value;
                dr["age"] = rojstvo_input.Value;
                dt.Rows.Add(dr);

                // bind data to gridView
                gridView.DataSourceID = null;
                gridView.DataSource = dt;
                gridView.DataBind();

            }
            else
            {
                ScriptManager.RegisterClientScriptBlock(this, this.GetType(), "alertMessage", "alert('Vnesite vse podatke')", true);
            }



        }

        protected void b_potrdiNarocilo_Click(object sender, EventArgs e)
        {
       

            
            //DateTime dateTime = new DateTime(datumPrihoda_date.Value, )
            if (mestoOdhoda_input.Value != "" &&  //if input field not empty
                mestoPrihoda_input.Value != "" && //if input field not empty
                datumOdhoda_date.Value != "" &&   //if input field not empty
                datumPrihoda_date.Value != "" &&  //if input field not empty
                GridView1.Rows.Count > 0 &&         // if any passengers
                ((kartica_checkbox.Checked &&       // check if kartica checked - then it has to contain all data, else doesnt have to
                imePlacnika_input.Value != "" &&
                priimekPlacnika_input.Value != "" &&
                kartica_input.Value != "")
                ||
                !kartica_checkbox.Checked           // if unchecked kartica
                )) 
            { 
            
                string mestoOdhoda = mestoOdhoda_input.Value;
                string mestoPrihoda = mestoPrihoda_input.Value;
                //DateTime datumOdhoda = Convert.ToDateTime(datumOdhoda_date.Value);
                string datumOdhoda_string = Convert.ToDateTime(datumOdhoda_date.Value).ToString("MM.dd.yyyy");
                string razredOdhoda = razredOdhoda_drop.SelectedValue;

                bool dvosmerna = Convert.ToBoolean(dvosmerna_checkbox.Checked);
                string dvosmerna_string = dvosmerna_checkbox.Checked.ToString();

                //DateTime datumPrihoda = Convert.ToDateTime(datumPrihoda_date.Value);
                string datumPrihoda_string = Convert.ToDateTime(datumPrihoda_date.Value).ToString("MM.dd.yyyy");
                string razredPrihoda = razredPrihoda_drop.SelectedValue;

                string passengerString = "";
                GridView gridView = GridView1;
                for (int i = 0; i < gridView.Rows.Count; i++)
                {
                   
                    GridViewRow row = gridView.Rows[i];
                    string name = row.Cells[0].Text;
                    string surname = row.Cells[1].Text;
                    string gender = row.Cells[2].Text;
                    string age = row.Cells[3].Text;

                    passengerString += name + "$$$" + surname + "$$$" + gender + "$$$" + age;
                    if (i < gridView.Rows.Count - 1)
                        passengerString += "$$$";
                        
                    //Response.Write(i.ToString() + ", ");
                    
                    
                }

                // TODO SHOW DIALOG
                Page.ClientScript.RegisterStartupScript(this.GetType(), "Scripts", "<script> function myFunction() { var txt; if (confirm('Press a button!') == true) { txt = 'You pressed OK!';  } else {txt = 'You pressed Cancel!';} }</script>");


                
                // WRITE TO DATABASE
                ServiceTravelData sTravelData = new ServiceTravelData();
                sTravelData.AddTravel(new TravelReceiveInfo
                {
                    googleID = "104967849801990887085",
                    planeName = "jurešnik",
                    planeCompany = "companyPlaneJurešnik",

                    fromLocation = mestoOdhoda,
                    toLocation = mestoPrihoda,


                    departureClass = razredOdhoda,
                    returnClass = razredPrihoda,

                    departureDate = datumOdhoda_string,//"3.12.2017"
                    returnDate = datumPrihoda_string, //"3.12.2017    MM.dd.yyyy     "




                    price = 243,
                    discount = 33,

                    //passengerData = "pasName1$$$passSurname1$$$male$$$1$$$pasName2$$$passSurname2$$$female$$$1"
                    passengerData = passengerString
                });
                Response.Write("WRITTEN IN DATABASE");

                // TODO - za test je zakomentiran redirect - ko bo končna verzija bo cool
                //Response.Redirect("http://asistentslivko.azurewebsites.net/MainPage.aspx");
            }
            else
            {
                Response.Write("ERROR - VPIŠITE VSE PODATKE");
                Page.ClientScript.RegisterStartupScript(this.GetType(), "Scripts", "<script> alert('Vpišite vse podatke')</script>");
            }
            
                
        
        }

        // call this function whenever user enters place or checks any options
        private void updatePrice(int amount)
        {
            cena += amount;
            cena_label.Text = "Končna cena: " + cena.ToString();
        }


        protected void kartica_checkbox_CheckedChanged(object sender, EventArgs e)
        {
       
            if (kartica_checkbox.Checked)
            {
                karticaTable.Style.Add("visibility","visible");
               
            }
            else
            {
                karticaTable.Style.Add("visibility", "hidden");
                
            }
        }

        protected void dvosmerna_checkbox_CheckedChanged(object sender, EventArgs e)
        {

            if (dvosmerna_checkbox.Checked)
            {
                dvosmernaTable.Style.Add("visibility", "visible");
                updatePrice(100); // TODO - update price doesnt work-------------------------------------!!!!!!!!!!!!!!!!!!!!!!
            }
            else
            {
                dvosmernaTable.Style.Add("visibility", "hidden");
                updatePrice(-100); // TODO - update price doesnt work-------------------------------------!!!!!!!!!!!!!!!!!!!!!!
            }

        }


    }


    public static class Utility
    {
        /// <summary>
        /// Gets the ordinal index of a TableCell in a rendered GridViewRow, using a text fieldHandle (e.g. the corresponding column's DataFieldName/SortExpression/HeaderText)
        /// </summary>
        public static int GetCellIndexByFieldHandle(this GridView grid, string fieldHandle)
        {
            int iCellIndex = -1;

            for (int iColIndex = 0; iColIndex < grid.Columns.Count; iColIndex++)
            {
                if (grid.Columns[iColIndex] is DataControlField)
                {
                    DataControlField col = (DataControlField)grid.Columns[iColIndex];
                    if ((col is BoundField && string.Compare(((BoundField)col).DataField, fieldHandle, true) == 0)
                        || string.Compare(col.SortExpression, fieldHandle, true) == 0
                        || col.HeaderText.Contains(fieldHandle))
                    {
                        iCellIndex = iColIndex;
                        break;
                    }
                }
            }
            return iCellIndex;
        }

        /// <summary>
        /// Gets the ordinal index of a TableCell in a rendered GridViewRow, using a text fieldHandle (e.g. the corresponding column's DataFieldName/SortExpression/HeaderText)
        /// </summary>
        public static int GetCellIndexByFieldHandle(this GridViewRow row, string fieldHandle)
        {
            return GetCellIndexByFieldHandle((GridView)row.Parent.Parent, fieldHandle);
        }
    }
}