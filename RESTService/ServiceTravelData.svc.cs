using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace RESTService
{

    public class ServiceTravelData : IServiceTravelData
    {
        string cs = ConfigurationManager.ConnectionStrings["SlivkoDatabaseConnectionString"].ConnectionString;


        public List<TravelSendInfo> ReturnTravels(string googleID)
        {
            var travelsInfo = new List<TravelSendInfo>();
            
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open(); //TODO get all the data
                string sql = "" +
                    "SELECT orderID, Plane.name as returnPlaneName, Plane.company as returnPlaneCompany, returnToLocation, returnFromLocation, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany,  returnDate, returnDiscount, returnPrice FROM Plane " +
                    "    JOIN(SELECT orderID, Location.name as returnToLocation, returnFromLocation, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany, returnDate, returnDiscount, returnPrice, returnPlaneID  FROM Location " +
                    "        JOIN(SELECT orderID, Location.name as returnFromLocation, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany, returnToLocationID, returnDate, returnDiscount, returnPrice, returnPlaneID  FROM Location " +
                    "            JOIN(SELECT orderID, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany, Flight.fromLocationID as returnFromLocationID, Flight.toLocationID as returnToLocationID, date as returnDate, discount as returnDiscount, price as returnPrice, planeID as returnPlaneID FROM Flight " +
                    "                JOIN(SELECT orderID, fromLocationName, toLocationName, returnFlightID, initialPrice, initialDate, initialDiscount, Plane.name as initialPlaneName, Plane.company as initialPlaneCompany FROM Plane " +
                    "                    JOIN(SELECT orderID, FromLocation.name as fromLocationName, toLocationName, returnFlightID, planeID as initialPlaneID, price as initialPrice, date as initialDate, discount as initialDiscount   FROM Location as FromLocation " +
                    "                        JOIN(SELECT orderID, fromLocationID, planeID, date, discount, price, ToLocation.name as toLocationName, returnFlightID FROM Location as ToLocation " +
                    "                            JOIN(SELECT orderID, fromLocationID, planeID, toLocationID, date, discount, price, returnFlightID FROM Flight as AllInitialFlights " +
                    "                                JOIN(SELECT orderID, initialFlightID, returnFlightID  FROM[Order]  AS AllOrders " +
                    "                                    WHERE AllOrders.userID = ( " +
                    "                                        SELECT[User].userID FROM[User] " +
                    "                                            WHERE[User].googleID = @googleID)) as Orders " +
                    "                                On(Orders.initialFlightID = AllInitialFlights.flightID)) as InitialFlight " +
                    "                           ON(InitialFlight.toLocationID = ToLocation.locationID)) AS InitialFlightWithLocationTo1 " +
                    "                        ON(FromLocation.locationID = InitialFlightWithLocationTo1.fromLocationID)) AS InitialWithLocations " +
                    "                    ON(Plane.planeID = initialPlaneID)) AS InitialWhole " +
                    "                ON(Flight.flightID = returnFlightID)) AS FlightReturn " +
                    "            ON(FlightReturn.returnFromLocationID = Location.locationID)) AS ReturnWithFromLocation " +
                    "        ON(ReturnWithFromLocation.returnToLocationID = Location.LocationID)) AS ReturnWithLocations " +
                    "      ON(Plane.planeID = ReturnWithLocations.returnPlaneID);";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("googleID", googleID));


                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {    
                        travelsInfo.Add(new TravelSendInfo
                        {
                            orderID = (int)reader["orderID"],
                            fromLocationName = reader["fromLocationName"].ToString(),
                            toLocationName = reader["toLocationName"].ToString(),
                            initialPrice = reader["initialPrice"].ToString(),
                            initialDate =reader["initialDate"].ToString(),
                            initialDiscount = reader["initialDiscount"].ToString(),
                            initialPlaneName = reader["initialPlaneName"].ToString(),
                            initialPlaneCompany = reader["initialPlaneCompany"].ToString(),

                            returnDate = reader["returnDate"].ToString(),
                            returnDiscount = reader["returnDiscount"].ToString(),
                            returnPrice = reader["returnPrice"].ToString(),
                            returnPlaneName = reader["returnPlaneName"].ToString(),
                            returnPlaneCompany = reader["returnPlaneCompany"].ToString(),
                            returnToLocation = reader["returnToLocation"].ToString(),
                            returnFromLocation = reader["returnFromLocation"].ToString(),

                        });
                    }
                }
                con.Close();        
            }
            
            return travelsInfo;
        }


        public int GetMaxIndex(SqlConnection con, string tableName, string idName)
        {
       
            string sql = "SELECT MAX("+ idName + ") FROM [" + tableName + "]";
            SqlCommand cmd = new SqlCommand(sql, con);
     

            using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
            {
                if (reader.Read())
                {
                    return Convert.ToInt32(reader[0]);
                       
                }
            }
     
            return -1;
        }

        public void AddTravel(TravelReceiveInfo info)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {
                string sql;
                SqlCommand cmd;

                con.Open();


                // FIRST INITIAL FLIGHT

                // ADD FROM LOCATION

                sql = "INSERT INTO [Location] (name) VALUES (@0)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.fromLocation));
                cmd.ExecuteNonQuery();
                int InitialFromLocationID = GetMaxIndex(con, "Location", "locationID");

                // THEN ADD TO LOCATION

                sql = "INSERT INTO [Location] (name) VALUES (@0)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.toLocation));
                cmd.ExecuteNonQuery();
                int InitialToLocationID = GetMaxIndex(con, "Location", "locationID");


                //ADD PLANE
                sql = "INSERT INTO [Plane] (name, company) VALUES (@0, @1)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.planeName));
                cmd.Parameters.Add(new SqlParameter("1", info.planeCompany));
                cmd.ExecuteNonQuery();
                int initialPlaneID = GetMaxIndex(con, "Plane", "planeID");


                //ADD INITIAL FLIGHT INFO
                sql = "INSERT INTO [Flight] (fromLocationID, planeID, toLocationID, date, discount, price) VALUES (@0, @1, @2, @3, @4, @5)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", InitialFromLocationID));
                cmd.Parameters.Add(new SqlParameter("1", initialPlaneID));
                cmd.Parameters.Add(new SqlParameter("2", InitialToLocationID));
                cmd.Parameters.Add(new SqlParameter("3", Convert.ToDateTime(info.departureDate)));
                cmd.Parameters.Add(new SqlParameter("4", info.discount));
                cmd.Parameters.Add(new SqlParameter("5", info.price));
                cmd.ExecuteNonQuery();
                int initialFlightID = GetMaxIndex(con, "Flight", "flightID");


                //ADD PLANE
                sql = "INSERT INTO [Plane] (name, company) VALUES (@0, @1)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.planeName));
                cmd.Parameters.Add(new SqlParameter("1", info.planeCompany));
                cmd.ExecuteNonQuery();
                int returnPlaneID = GetMaxIndex(con, "Plane", "planeID");



                //ADD RETURN FLIGHT INFO
                sql = "INSERT INTO [Flight] (toLocationID, planeID, fromLocationID, date, discount, price) VALUES (@0, @1, @2, @3, @4, @5)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", InitialToLocationID));
                cmd.Parameters.Add(new SqlParameter("1", returnPlaneID));
                cmd.Parameters.Add(new SqlParameter("2", InitialFromLocationID));
                cmd.Parameters.Add(new SqlParameter("3", Convert.ToDateTime(info.returnDate)));
                cmd.Parameters.Add(new SqlParameter("4", info.discount));
                cmd.Parameters.Add(new SqlParameter("5", info.price));
                cmd.ExecuteNonQuery();
                int returnFlightID = GetMaxIndex(con, "Flight", "flightID");

                


                // GET USER ID TO CONNECT IT TO ORDER

                int userID = -1;

          
                sql = "SELECT [User].userID FROM [User] WHERE [User].googleID=@0";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.googleID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {
                        userID = (int)reader[0];
                    }
                }


                //ADD ORDER
                sql = "INSERT INTO [Order] (userID, initialFlightID, returnFlightID) VALUES (@0, @1, @2)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", userID));
                cmd.Parameters.Add(new SqlParameter("1", initialFlightID));
                cmd.Parameters.Add(new SqlParameter("2", returnFlightID));
                cmd.ExecuteNonQuery();
                int orderID = GetMaxIndex(con, "Order", "orderID");

                
                // WHEN WE HAVE ORDER WE CAN CONNECT IT WITH PASSENGERS
                for (int i = 0; i < info.passengerData.Count; i += 4)
                {
                    sql = "INSERT INTO [Passenger] (name, surname, gender, age, orderID) VALUES (@0, @1, @2, @3, @4)";
                    cmd = new SqlCommand(sql, con);


                    cmd.Parameters.Add(new SqlParameter("0", info.passengerData[i + 0]));
                    cmd.Parameters.Add(new SqlParameter("1", info.passengerData[i + 1]));
                    cmd.Parameters.Add(new SqlParameter("2", info.passengerData[i + 2]));
                    cmd.Parameters.Add(new SqlParameter("3", Convert.ToInt32(info.passengerData[i + 3])));
                    cmd.Parameters.Add(new SqlParameter("4", orderID));
                    cmd.ExecuteNonQuery();
                }
                
            
                con.Close();
            }
        }


        public void RemoveOrder(string orderID)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();

                string sql = "DELETE FROM [Passenger] WHERE orderID=@OID";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("OID", orderID));
                cmd.ExecuteNonQuery();

                sql = "DELETE FROM [Order] WHERE orderID=@OID";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("OID", orderID));
                cmd.ExecuteNonQuery();

                con.Close();
            }
        }

    }
}
