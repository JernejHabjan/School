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
            
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT * FROM [User] WHERE [User].googleID=@0";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", GoogleID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {
                        user.userID= Convert.ToInt32(reader[0]);
                        user.roleID= Convert.ToInt32(reader[1]);
                        user.googleID = reader.GetString(2);
                        user.name = reader.GetString(3);
                        user.email = reader.GetString(4);                 
                    }
                }
                con.Close();
                return user;
            }
        }

        public List<User> ReturnUsers()
        {
            var retVal = new List<User>();

            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT * FROM [User]";
                SqlCommand cmd = new SqlCommand(sql, con);


                using (SqlDataReader reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        retVal.Add(new User
                        {
                            userID = Convert.ToInt32(reader[0]),
                            roleID = Convert.ToInt32(reader[1]),
                            googleID = reader.GetString(2),
                            name = reader.GetString(3),
                            email = reader.GetString(4)
   
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
                    "INSERT INTO [User] (roleID, googleID, name, email) VALUES (@0, @1, @2, @3)";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", user.roleID));
                cmd.Parameters.Add(new SqlParameter("1", user.googleID));
                cmd.Parameters.Add(new SqlParameter("2", user.name));
                cmd.Parameters.Add(new SqlParameter("3", user.email));
                cmd.ExecuteNonQuery();
                con.Close();

            }
        }

        public void RemoveUser(string googleID)
        {
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "DELETE FROM [User] WHERE googleID=@googleID";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("googleID", googleID));
                cmd.ExecuteNonQuery();
                con.Close();
            }
        }

        public void UpdateUser(User user)
        {
            bool updateRole = user.roleID != 0;
            bool updateName = user.name != "";
            bool updateEmail = user.email != "";

            if(user.googleID == "" || (!updateRole && !updateName && !updateEmail)){
                return; //Invalid data
            }

            using(SqlConnection con = new SqlConnection(cs))
            {
                con.Open();

                string sql = "UPDATE [User] set "; 
                if (updateRole) sql+= " [User].roleID=@0, ";
                if(updateName) sql+= " [User].name=@2, ";
                if(updateEmail) sql+= " [User].email=@3 ";

                //Remove comma at the end
                sql = sql.Remove(sql.Length - 1);
                sql += " WHERE [User].googleID=@1";

                

                SqlCommand cmd = new SqlCommand(sql, con);
                if (updateRole) cmd.Parameters.Add(new SqlParameter("0", user.roleID));
                cmd.Parameters.Add(new SqlParameter("1", user.googleID));
                if(updateName) cmd.Parameters.Add(new SqlParameter("2", user.name));
                if(updateEmail) cmd.Parameters.Add(new SqlParameter("3", user.email));
                cmd.ExecuteNonQuery();
                con.Close();
            }
        }


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

            return passengers;
        }

    }
}
