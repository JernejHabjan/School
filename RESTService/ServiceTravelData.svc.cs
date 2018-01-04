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
                string sql = "SELECT [Order].orderID" +
                    " FROM [Order] " +
                    /*" JOIN [Order] USING(userID) " +
                    " JOIN [Flight] USING (flightID) " +
                    " JOIN [Location] USING (locationID) " +
                    " JOIN [Plane] USING (planeID) " +*/
                    " WHERE [User].googleID=@googleID";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("googleID", googleID));


                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        travelsInfo.Add(new TravelSendInfo
                        {
                            orderID = reader.GetInt32(0),
                            /*departureLocation = reader.GetString(1),
                            arrivalLocation = reader.GetString(2),
                            departureDate = reader.GetString(3),
                            returnlDate = reader.GetString(4),
                            price = reader.GetFloat(5)*/
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




                // ADD FROM LOCATION
                
                sql ="INSERT INTO [Location] (name) VALUES (@0)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.departureName));
                int fromLocationID = (int)cmd.ExecuteScalar();

                // THEN ADD TO LOCATION

                sql = "INSERT INTO [Location] (name) VALUES (@0)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.arrivalName)); 
                int toLocationID = (int)cmd.ExecuteScalar();




                //ADD PLANE
                sql = "INSERT INTO [Plane] (name, company) VALUES (@0, @1)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", info.planeName));
                cmd.Parameters.Add(new SqlParameter("1", info.planeCompany));
               
                int planeID = (int)cmd.ExecuteScalar();


                //ADD INITIAL FLIGHT INFO
                sql = "INSERT INTO [Flight] (locationID, planeID, Loc_locationID, date, discount, price) VALUES (@0, @1, @2, @3, @4, @5)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", fromLocationID));
                cmd.Parameters.Add(new SqlParameter("1", planeID));
                cmd.Parameters.Add(new SqlParameter("2", toLocationID));
                cmd.Parameters.Add(new SqlParameter("3", info.departureDate));
                cmd.Parameters.Add(new SqlParameter("4", info.discount));
                cmd.Parameters.Add(new SqlParameter("5", info.price));
                int initialFlightID = (int)cmd.ExecuteScalar();

                //ADD RETURN FLIGHT INFO
                sql = "INSERT INTO [Flight] (locationID, planeID, Loc_locationID, date, discount, price) VALUES (@0, @1, @2, @3, @4, @5)";  
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", toLocationID));
                cmd.Parameters.Add(new SqlParameter("1", planeID));
                cmd.Parameters.Add(new SqlParameter("2", fromLocationID));
                cmd.Parameters.Add(new SqlParameter("3", info.returnDate));
                cmd.Parameters.Add(new SqlParameter("4", info.discount));
                cmd.Parameters.Add(new SqlParameter("5", info.price));
                int returnFlightID = (int)cmd.ExecuteScalar();



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
                        userID= Convert.ToInt32(reader[0]);
                            
                    }
                }
                
          

                //ADD ORDER

                sql = "INSERT INTO [Order] (userID, flightID, Fli_flightID) VALUES (@0, @1, @2)";
                cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", userID));
                cmd.Parameters.Add(new SqlParameter("1", initialFlightID));
                cmd.Parameters.Add(new SqlParameter("2", returnFlightID));
                int orderID = (int)cmd.ExecuteScalar();
                con.Close();



                // WHEN WE HAVE ORDER WE CAN CONNECT IT WITH PASSENGERS ADD PASSENGERS
                foreach (string passenger in info.passengerData)
                {
                    /*
                     
                 TODO --- NEVEM KKO PARSAT TA PASSENGER STRING
                
                    sql = "INSERT INTO [Passenger] (name, surname, gender, age) VALUES (@0, @1, @2, @3)";
                    cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", passenger.name));
                    cmd.Parameters.Add(new SqlParameter("1", passenger.surname));
                    cmd.Parameters.Add(new SqlParameter("2", passenger.gender));
                    cmd.Parameters.Add(new SqlParameter("3", passenger.age));
                    int passengerID = (int)cmd.ExecuteScalar();
                    
                    */
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


        public List<Passenger> ReturnPassengers(int orderID)
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
