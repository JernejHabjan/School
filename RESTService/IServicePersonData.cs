using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace RESTService
{
   
    [ServiceContract]
    public interface IServicePersonData
    {
        
        [OperationContract]
        [WebGet(UriTemplate = "User", ResponseFormat = WebMessageFormat.Json)]
        List<User> ReturnUsers();

        [OperationContract]
        [WebGet(UriTemplate = "User/{googleID}", ResponseFormat = WebMessageFormat.Json)]
        User ReturnUser(string googleID);

        [OperationContract]
        [WebInvoke(UriTemplate = "User", ResponseFormat = WebMessageFormat.Json)]
        void AddUser(User user);

        [OperationContract]
        [WebInvoke(UriTemplate = "User/{googleID}", ResponseFormat = WebMessageFormat.Json, Method = "DELETE")]
        void RemoveUser(string googleID);

        [OperationContract]
        [WebInvoke(UriTemplate = "User/{googleID}", ResponseFormat = WebMessageFormat.Json, Method = "PUT")]
        void UpdateUser(User user,  string googleID);






        [OperationContract]
        [WebInvoke(UriTemplate = "Passenger", ResponseFormat = WebMessageFormat.Json)]
        void AddPassenger(Passenger passenger);



    }

    
    [DataContract]
    public class User
    {
        [DataMember]
        public int userID { get; set; }
        [DataMember]
        public int roleID { get; set; }
        [DataMember]
        public string GoogleID { get; set; }
        [DataMember]
        public string name { get; set; }
        [DataMember]
        public string email { get; set; }
        [DataMember]
        public string gender { get; set; }

    }


    [DataContract]
    public class Role
    {
        [DataMember]
        public int roleID { get; set; }
        [DataMember]
        public string description { get; set; }
       
    }

    [DataContract]
    public class Passenger
    {
        [DataMember]
        public int passengerID { get; set; }
        [DataMember]
        public string name { get; set; }
        [DataMember]
        public string surname { get; set; }
        [DataMember]
        public string gender { get; set; }
        [DataMember]
        public int age { get; set; }

    }
}
