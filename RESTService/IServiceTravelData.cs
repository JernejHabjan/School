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
    public interface IServiceTravelData
    {
  
        [OperationContract]
        [WebGet(UriTemplate = "Travels/{googleID}", ResponseFormat = WebMessageFormat.Json)]
        List<TravelInfo> ReturnTravels(string googleID);


        [OperationContract]
        [WebInvoke(UriTemplate = "Travel", ResponseFormat = WebMessageFormat.Json)]
        void AddTravel(List<Passenger> passengers, Location fromLocation, Location toLocation, Plane plane, Flight initialFlight, Flight returnFlight, Order order, string googleID);

        [OperationContract]
        [WebInvoke(UriTemplate = "Travel/{googleID}", ResponseFormat = WebMessageFormat.Json, Method = "DELETE")]
        void RemoveTravel(string googleID);

        [OperationContract]
        [WebInvoke(UriTemplate = "Travel/{googleID}", ResponseFormat = WebMessageFormat.Json, Method = "PUT")]
        void UpdateTravel(TravelInfo travel, string googleID);




    }


    [DataContract]
    public class TravelInfo
    {
        [DataMember]
        public string Plane_name { get; set; }
        [DataMember]
        public string Location_name { get; set; }


    }

    //#############################################################################

    [DataContract]
    public class Location
    {
        [DataMember]
        public int locationID { get; set; }
        [DataMember]
        public string name { get; set; }


    }
    [DataContract]
    public class Plane
    {
        [DataMember]
        public int planeID { get; set; }
        [DataMember]
        public string name { get; set; }
        [DataMember]
        public int seats { get; set; }
        [DataMember]
        public string company { get; set; }
        [DataMember]
        public float price { get; set; }


    }
    [DataContract]
    public class Flight
    {
        [DataMember]
        public int flightID { get; set; }
        [DataMember]
        public int locationID { get; set; }
        [DataMember]
        public int planeID { get; set; }
        [DataMember]
        public int Loc_locationID { get; set; }
        [DataMember]
        public DateTime date { get; set; }
        [DataMember]
        public float discount { get; set; }
    }
    [DataContract]
    public class Order
    {
        [DataMember]
        public int orderID { get; set; }
        [DataMember]
        public int userID { get; set; }
        [DataMember]
        public int flightID { get; set; }
        [DataMember]
        public int Fli_flightID { get; set; }

    }






}
