using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Linq;
using System.ServiceModel;
using System.Web;

namespace RESTService
{
    public class GoogleProfile
    {

        public string ID { get; set; }
        public string DisplayName { get; set; }
        public string Gender { get; set; }
        public string ObjectType { get; set; }
        public Image Image { get; set; }
        public List<Email> Emails { get; set; }

    }
    public class Email
    {

        public string value { get; set; }
        public string Type { get; set; }
    }
    public class Image
    {
          public string Url { get; set; }

    }


    public class GoogleLoginAndRegistration
    {
        string cs = ConfigurationManager.ConnectionStrings["SlivkoDatabaseConnectionString"].ConnectionString;

        public int ReturnUserIDFromGID(string GID)
        {
            int userID = -1;

            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT [User].userID FROM [User] WHERE [User].googleID=@param1";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("param1", GID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {

                        userID = reader.GetInt32(0);
                    }
                }
                con.Close();
                return userID;
            }
        }



        public void AddGUserIfNotExist(GoogleProfile profile)
        {

            if (ReturnUserIDFromGID(profile.ID) == -1) // NOT INSERTED
            {
                using (SqlConnection con = new SqlConnection(cs))
                {
                    con.Open();
                    string sql = "INSERT INTO [User] (roleID, googleID, name, email) VALUES (@0, @1, @2, @3)";

                    SqlCommand cmd = new SqlCommand(sql, con);
                    cmd.Parameters.Add(new SqlParameter("0", 1)); //insert as regular user
                    cmd.Parameters.Add(new SqlParameter("1", profile.ID));
                    cmd.Parameters.Add(new SqlParameter("2", profile.DisplayName));
                    cmd.Parameters.Add(new SqlParameter("3", profile.Emails));




                    cmd.ExecuteNonQuery();
                    con.Close();

                }
            }
           
        }
    }

}