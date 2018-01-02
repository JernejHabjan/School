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

        
        public List<TravelInfo> ReturnTravels(string googleID)
        {
            var retVal = new List<TravelInfo>();

            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT Plane.name, Location.name " +
                    " FROM User " +
                    " JOIN Order USING(userID) " +
                    " JOIN passengers USING (orderID) " +
                    " JOIN Flight USING (flightID) " +
                    " JOIN Location USING (locationID) " +
                    " JOIN Plane USING (planeID) " +
                    " WHERE googleID=@googleID";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("googleID", googleID));


                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        retVal.Add(new TravelInfo
                        {
                            Plane_name = reader.GetString(0),
                            Location_name = reader.GetString(1)

                        });

                    }
                }
                con.Close();

                return retVal;
            }
        }


        public void AddTravel(List<Passenger> passengers, Location fromLocation, Location toLocation, Plane plane, Flight initialFlight, Flight returnFlight, Order order, string googleID)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {
                string sql;
                SqlCommand cmd;

                con.Open();



                
                // ADD FROM LOCATION

                sql ="INSERT INTO Location (name) VALUES (@0)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", fromLocation.name));
                int fromLocationID = (int)cmd.ExecuteScalar();

                // THEN TO ADD LOCATION

                sql = "INSERT INTO Location (name) VALUES (@0)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", toLocation.name)); 
                int toLocationID = (int)cmd.ExecuteScalar();




                //ADD PLANE
                sql = "INSERT INTO Plane (name, seats, company, price) VALUES (@0, @1, @2, @3)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", plane.name));
                cmd.Parameters.Add(new SqlParameter("1", plane.seats));
                cmd.Parameters.Add(new SqlParameter("2", plane.company));
                cmd.Parameters.Add(new SqlParameter("3", plane.price));
                int planeID = (int)cmd.ExecuteScalar();


                //ADD FLIGHT INFO
                sql = "INSERT INTO Flight (locationID, planeID, Loc_locationID, date, discount) VALUES (@0, @1, @2, @3, @4)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", fromLocationID));
                cmd.Parameters.Add(new SqlParameter("1", planeID));
                cmd.Parameters.Add(new SqlParameter("2", toLocationID));
                cmd.Parameters.Add(new SqlParameter("3", initialFlight.date));
                cmd.Parameters.Add(new SqlParameter("4", initialFlight.discount));
                int initialFlightID = (int)cmd.ExecuteScalar();

                //ADD FLIGHT INFO
                sql = "INSERT INTO Flight (locationID, planeID, Loc_locationID, date, discount) VALUES (@0, @1, @2, @3, @4)";  // RETURN FLIGHT TO CHECK TODO
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", toLocationID));
                cmd.Parameters.Add(new SqlParameter("1", planeID));
                cmd.Parameters.Add(new SqlParameter("2", fromLocationID));
                cmd.Parameters.Add(new SqlParameter("3", returnFlight.date));
                cmd.Parameters.Add(new SqlParameter("4", returnFlight.discount));
                int returnFlightID = (int)cmd.ExecuteScalar();



                // GET USER ID TO CONNECT IT TO ORDER

                int userID = -1;
                
                con.Open();
                sql = "SELECT userID FROM User WHERE googleID=@0";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", googleID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {
                        userID= Convert.ToInt32(reader[0]);
                            
                    }
                }
                con.Close();
          

                //ADD ORDER

                sql = "INSERT INTO Order (userID, flightID, Fli_flightID) VALUES (@0, @1, @2)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", userID));
                cmd.Parameters.Add(new SqlParameter("1", initialFlightID));
                cmd.Parameters.Add(new SqlParameter("2", returnFlightID));
                int orderID = (int)cmd.ExecuteScalar();
                con.Close();



                // WHEN WE HAVE ORDER WE CAN CONNECT IT WITH PASSENGERS ADD PASSENGERS
                foreach (Passenger passenger in passengers)
                {
                    sql = "INSERT INTO Passenger (name, surname, gender, age) VALUES (@0, @1, @2, @3)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", passenger.name));
                    cmd.Parameters.Add(new SqlParameter("1", passenger.surname));
                    cmd.Parameters.Add(new SqlParameter("2", passenger.gender));
                    cmd.Parameters.Add(new SqlParameter("3", passenger.age));
                    int passengerID = (int)cmd.ExecuteScalar();

                    // INSERT V VMESNO TABELO passengers
                    sql = "INSERT INTO passengers (orderID, passengerID) VALUES (@0, @1)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", orderID));
                    cmd.Parameters.Add(new SqlParameter("1", passengerID));
                    cmd.ExecuteNonQuery();

                }




            }
        }

        public void RemoveTravel(string googleID)
        {
            throw new NotImplementedException();
        }


        public void UpdateTravel(TravelInfo travel, string googleID)
        {
            throw new NotImplementedException();
        }
    }
}
