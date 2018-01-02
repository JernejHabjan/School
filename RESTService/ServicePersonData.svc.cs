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
    
    public class ServicePersonData : IServicePersonData
    {
        
        string cs = ConfigurationManager.ConnectionStrings["SlivkoDatabaseConnectionString"].ConnectionString;

        public User ReturnUser(string GoogleID)
        {

            User user = new User();
            /*
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT * FROM User WHERE googleID=@0";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", GoogleID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {
                        user.userID= Convert.ToInt32(reader[0]);
                        user.roleID= Convert.ToInt32(reader[1]);
                        user.GoogleID = reader.GetString(2);
                        user.name = reader.GetString(3);
                        user.email = reader.GetString(4);
                        user.gender = reader.GetString(5);
                 
                    }
                }
                con.Close();
                return user;
            }*/
            return new User
            {
                userID = 1,
                roleID =22,
                GoogleID ="wwewew",
                name = "wwewew",
                email = "wwewew",
                gender = "wwewew"
            };


            // TODO- -------- NEVEN ZAKAJ NE POBERE NČ IZ BAZE.... PROBOV SM TUD INSERTAT V BAZO SAM JE NEKI ČUDN... TUJ KLUČ PROBLEM? --- TODO neda sem zdle
        }

        public List<User> ReturnUsers()
        {
            var retVal = new List<User>();

            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT * FROM User";
                SqlCommand cmd = new SqlCommand(sql, con);


                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        retVal.Add(new User
                        {
                            userID = Convert.ToInt32(reader[0]),
                            roleID = Convert.ToInt32(reader[1]),
                            GoogleID = reader.GetString(2),
                            name = reader.GetString(3),
                            email = reader.GetString(4),
                            gender = reader.GetString(5)
                        });

                    }
                }
                con.Close();

                return retVal;
            }
        }

        public void AddUser(User user)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql =
                    "INSERT INTO User (roleID, googleID, name, email, gender) VALUES (@0, @1, @2, @3, @4)";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", user.roleID));
                cmd.Parameters.Add(new SqlParameter("1", user.GoogleID));
                cmd.Parameters.Add(new SqlParameter("2", user.name));
                cmd.Parameters.Add(new SqlParameter("3", user.email));
                cmd.Parameters.Add(new SqlParameter("4", user.gender));
                cmd.ExecuteNonQuery();
                con.Close();

            }
        }

        public void RemoveUser(string googleID)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "DELETE FROM User WHERE googleID=@googleID";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("googleID", googleID));
                cmd.ExecuteNonQuery();
                con.Close();
            }
        }

        public void UpdateUser(User user, string googleID)
        {
            using(SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "UPDATE User set roleID=@0 name=@2, email=@3, gender=@4 WHERE googleID=@1";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", user.roleID));
                cmd.Parameters.Add(new SqlParameter("1", googleID));
                cmd.Parameters.Add(new SqlParameter("2", user.name));
                cmd.Parameters.Add(new SqlParameter("3", user.email));
                cmd.Parameters.Add(new SqlParameter("4", user.gender));
                cmd.ExecuteNonQuery();
                con.Close();

            }
        }








        public void AddPassenger(Passenger passenger) // TODO ------------- WE DONT KNOW WHO ADDED THIS PASSENGER IF WE ADD IT MANUALLY - BUT IF WE ADD IT VIA ORDER WE DO
        {

            
            
            using (SqlConnection con = new SqlConnection(cs))
            {
                string sql = "INSERT INTO Passenger (name, surname, gender, age) VALUES (@0, @1, @2, @3)";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", passenger.name));
                cmd.Parameters.Add(new SqlParameter("1", passenger.surname));
                cmd.Parameters.Add(new SqlParameter("2", passenger.gender));
                cmd.Parameters.Add(new SqlParameter("3", passenger.age));
                int passengerID = (int)cmd.ExecuteScalar();

            }
        }
    }
}
