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

        public GoogleProfile ReturnEntryFromGID(string GID)
        {
            GoogleProfile oseba = new GoogleProfile();



            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql = "SELECT * FROM Entries WHERE GID=@param1";
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("param1", GID));

                using (SqlDataReader reader = cmd.ExecuteReader(System.Data.CommandBehavior.SingleRow))
                {
                    if (reader.Read())
                    {
                        oseba.ID = reader.GetString(0);
                        oseba.DisplayName = reader.GetString(2);
                        oseba.Gender= reader.GetString(3);
                        oseba.ObjectType = reader.GetString(4);
                     
                        //oseba.Image = reader.GetString(5);
                        //oseba.Emals = reader.GetString(6);
                    }
                }
                con.Close();
                return oseba;
            }
        }



        public void AddGUserIfNotExist(GoogleProfile profile)
        {

            if (ReturnEntryFromGID(profile.ID).ID == null)
            {

            }
            using (SqlConnection con = new SqlConnection(cs))
            {
                con.Open();
                string sql =
                   "INSERT INTO Entries (GID, Gname) VALUES (@0, @1)";
             
                SqlCommand cmd = new SqlCommand(sql, con);
                cmd.Parameters.Add(new SqlParameter("0", profile.ID));
                cmd.Parameters.Add(new SqlParameter("1", profile.DisplayName));
                cmd.ExecuteNonQuery();
                con.Close();

            }
        }
    }

}