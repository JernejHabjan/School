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
                    "SELECT Plane.name as returnPlaneName, Plane.company as returnPlaneCompany, returnToLocation, returnFromLocation, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany,  returnDate, returnDiscount, returnPrice FROM Plane " +
                    "    JOIN(SELECT Location.name as returnToLocation, returnFromLocation, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany, returnDate, returnDiscount, returnPrice, returnPlaneID  FROM Location " +
                    "        JOIN(SELECT Location.name as returnFromLocation, fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany, returnToLocationID, returnDate, returnDiscount, returnPrice, returnPlaneID  FROM Location " +
                    "            JOIN(SELECT fromLocationName, toLocationName, initialPrice, initialDate, initialDiscount, initialPlaneName, initialPlaneCompany, Flight.fromLocationID as returnFromLocationID, Flight.toLocationID as returnToLocationID, date as returnDate, discount as returnDiscount, price as returnPrice, planeID as returnPlaneID FROM Flight " +
                    "                JOIN(SELECT fromLocationName, toLocationName, returnFlightID, initialPrice, initialDate, initialDiscount, Plane.name as initialPlaneName, Plane.company as initialPlaneCompany FROM Plane " +
                    "                    JOIN(SELECT FromLocation.name as fromLocationName, toLocationName, returnFlightID, planeID as initialPlaneID, price as initialPrice, date as initialDate, discount as initialDiscount   FROM Location as FromLocation " +
                    "                        JOIN(SELECT fromLocationID, planeID, date, discount, price, ToLocation.name as toLocationName, returnFlightID FROM Location as ToLocation " +
                    "                            JOIN(SELECT fromLocationID, planeID, toLocationID, date, discount, price, returnFlightID FROM Flight as AllInitialFlights " +
                    "                                JOIN(SELECT  initialFlightID, returnFlightID  FROM[Order]  AS AllOrders " +
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
                    "    ON(Plane.planeID = ReturnWithLocations.returnPlaneID);";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("googleID", googleID));


                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
              
                        travelsInfo.Add(new TravelSendInfo
                        {

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


        public void AddTravel(TravelReceiveInfo info)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {

             

                string sql;
                SqlCommand cmd;

                con.Open();


                // FIRST INITIAL FLIGHT

                    // ADD FROM LOCATION
                
                    sql ="INSERT INTO [Location] (name) VALUES (@0)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", info.departureName));
                    int InitialFromLocationID = (int)(decimal)cmd.ExecuteScalar();
                
                    // THEN ADD TO LOCATION

                    sql = "INSERT INTO [Location] (name) VALUES (@0)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", info.arrivalName)); 
                    int InitialToLocationID = (int)(decimal)cmd.ExecuteScalar();


                    //ADD PLANE
                    sql = "INSERT INTO [Plane] (name, company) VALUES (@0, @1)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", info.planeName));
                    cmd.Parameters.Add(new SqlParameter("1", info.planeCompany));
               
                    int initialPlaneID = (int)(decimal)cmd.ExecuteScalar();


                    //ADD INITIAL FLIGHT INFO
                    sql = "INSERT INTO [Flight] (fromLocationID, planeID, toLocationID, date, discount, price) VALUES (@0, @1, @2, @3, @4, @5)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", InitialFromLocationID));
                    cmd.Parameters.Add(new SqlParameter("1", initialPlaneID));
                    cmd.Parameters.Add(new SqlParameter("2", InitialToLocationID));
                    cmd.Parameters.Add(new SqlParameter("3", info.departureDate));
                    cmd.Parameters.Add(new SqlParameter("4", info.discount));
                    cmd.Parameters.Add(new SqlParameter("5", info.price));
                    int initialFlightID = (int)(decimal)cmd.ExecuteScalar();

                // THEN RETURN FLIGHT

                    // ADD FROM LOCATION

                    sql = "INSERT INTO [Location] (name) VALUES (@0)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", info.departureName));
                    int returnFromLocationID = (int)(decimal)cmd.ExecuteScalar();

                    // THEN ADD TO LOCATION

                    sql = "INSERT INTO [Location] (name) VALUES (@0)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", info.arrivalName));
                    int returnToLocationID = (int)(decimal)cmd.ExecuteScalar();




                    //ADD PLANE
                    sql = "INSERT INTO [Plane] (name, company) VALUES (@0, @1)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", info.planeName));
                    cmd.Parameters.Add(new SqlParameter("1", info.planeCompany));

                    int returnPlaneID = (int)(decimal)cmd.ExecuteScalar();


                    //ADD RETURN FLIGHT INFO
                    sql = "INSERT INTO [Flight] (toLocationID, planeID, fromLocationID, date, discount, price) VALUES (@0, @1, @2, @3, @4, @5)";  
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", returnToLocationID));
                    cmd.Parameters.Add(new SqlParameter("1", returnPlaneID));
                    cmd.Parameters.Add(new SqlParameter("2", returnFromLocationID));
                    cmd.Parameters.Add(new SqlParameter("3", info.returnDate));
                    cmd.Parameters.Add(new SqlParameter("4", info.discount));
                    cmd.Parameters.Add(new SqlParameter("5", info.price));
                    int returnFlightID = (int)(decimal)cmd.ExecuteScalar();



                // GET USER ID TO CONNECT IT TO ORDER

                int userID = -1;
                
                con.Open();
                sql = "SELECT [User].userID FROM [User] WHERE [User].googleID=@0";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.googleID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {
                        userID= (int)(decimal)reader[0];
                    }
                }
                
          

                //ADD ORDER

                sql = "INSERT INTO [Order] (userID, flightID, Fli_flightID) VALUES (@0, @1, @2)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", userID));
                cmd.Parameters.Add(new SqlParameter("1", initialFlightID));
                cmd.Parameters.Add(new SqlParameter("2", returnFlightID));
                int orderID = (int)(decimal)cmd.ExecuteScalar();
                con.Close();



                // WHEN WE HAVE ORDER WE CAN CONNECT IT WITH PASSENGERS
                for (int i = 0; i < info.passengerData.Count; i+=4)
                {
                    sql = "INSERT INTO [Passenger] (name, surname, gender, age, orderID) VALUES (@0, @1, @2, @3, @4)";
                    cmd = new SqlCommand(sql, con);

                 
                    cmd.Parameters.Add(new SqlParameter("0", info.passengerData[i + 0].ToString()));
                    cmd.Parameters.Add(new SqlParameter("1", info.passengerData[i + 1].ToString()));
                    cmd.Parameters.Add(new SqlParameter("2", info.passengerData[i + 2].ToString()));
                    cmd.Parameters.Add(new SqlParameter("3", info.passengerData[i + 3].ToString()));
                    cmd.Parameters.Add(new SqlParameter("4", orderID));
                    int passengerID = (int)(decimal)cmd.ExecuteScalar();
                    
                    
                }

                con.Close();
            }
        }

        public void RemoveTravel(string googleID)
        {
            throw new NotImplementedException();
        }


       /* public void UpdateTravel(TravelInfo travel, string googleID)
        {
            throw new NotImplementedException();
        }*/


        public List<Passenger> ReturnPassengers(string orderID)
        {
            var passengers = new List<Passenger>();

            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT [Passenger].name, [Passenger].surname, [Passenger].gender, [Passenger].age " +
                    " FROM [Passenger] " +
                    " JOIN [Order] USING(passengerID) " +
                    " WHERE [Order].orderID=@OID";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("OID", orderID));

                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        passengers.Add(new Passenger
                        {
                            name = reader.GetString(0),
                            surname = reader.GetString(1),
                            gender = reader.GetString(2),
                            age = reader.GetInt32(3),
                        });

                    }
                }
                con.Close();
            }

            //passengers.Add(new Passenger { name = "Tester", surname = "Habjan", age = 12, gender = "Habjan" });

            return passengers;
        }

    }
}
