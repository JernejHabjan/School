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
        List<TravelSendInfo> ReturnTravels(string googleID);


        [OperationContract]
        [WebInvoke(UriTemplate = "Travel", ResponseFormat = WebMessageFormat.Json)]
        void AddTravel(TravelReceiveInfo info);

        [OperationContract]
        [WebInvoke(UriTemplate = "Travel/{googleID}", ResponseFormat = WebMessageFormat.Json, Method = "DELETE")]
        void RemoveTravel(string googleID);

        /*[OperationContract]
        [WebInvoke(UriTemplate = "Travel/{googleID}", ResponseFormat = WebMessageFormat.Json, Method = "PUT")]
        void UpdateTravel(TravelInfo travel, string googleID);*/


        [OperationContract]
        [WebGet(UriTemplate = "Passengers/{orderID}", ResponseFormat = WebMessageFormat.Json)]
        List<Passenger> ReturnPassengers(int orderID);



    }


    [DataContract]
    public class TravelReceiveInfo
    {
        [DataMember]
        public string googleID { get; set; }

        [DataMember]
        public string planeName { get; set; }
        [DataMember]
        public string planeCompany { get; set; }

        [DataMember]
        public string departureName{ get; set; }
        [DataMember]
        public string arrivalName { get; set; }

        [DataMember]
        public string departureDate { get; set; }
        [DataMember]
        public string returnDate { get; set; }

        [DataMember]
        public float price { get; set; }
        [DataMember]
        public float discount { get; set; }

        [DataMember]
        public List<string> passengerData { get; set; }
    }


    [DataContract]
    public class TravelSendInfo
    {
        [DataMember]
        public int orderID { get; set; }
        [DataMember]
        public string departureLocation { get; set; }
        [DataMember]
        public string arrivalLocation { get; set; }
        [DataMember]
        public string departureDate { get; set; }
        [DataMember]
        public string returnlDate { get; set; }
        [DataMember]
        public float price { get; set; }

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
