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
        [WebInvoke(UriTemplate = "Order/{orderID}", ResponseFormat = WebMessageFormat.Json, Method = "DELETE")]
        void RemoveOrder(string orderID);

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
        public string fromLocation { get; set; }
        [DataMember]
        public string toLocation { get; set; }

        [DataMember]
        public string departureDate { get; set; }
        [DataMember]
        public string departureClass { get; set; }
        
        [DataMember]
        public float price { get; set; }
        [DataMember]
        public float discount { get; set; }
    
        [DataMember]
        public string returnDate { get; set; }
        [DataMember]
        public string returnClass { get; set; }

        [DataMember]
        public string passengerData { get; set; }
    }


    [DataContract]
    public class TravelSendInfo
    {
        [DataMember]
        public int orderID { get; set; }
        [DataMember]
        public string fromLocationName { get; set; }
        [DataMember]
        public string toLocationName { get; set; }
        [DataMember]
        public string initialPrice { get; set; }
        [DataMember]
        public string initialDate { get; set; }
        [DataMember]
        public string initialDiscount { get; set; }
        [DataMember]
        public string initialPlaneName { get; set; }
        [DataMember]
        public string initialPlaneCompany { get; set; }

        [DataMember]
        public string returnDate { get; set; }
        [DataMember]
        public string returnDiscount { get; set; }
        [DataMember]
        public string returnPrice { get; set; }
        [DataMember]
        public string returnPlaneName { get; set; }
        [DataMember]
        public string returnPlaneCompany { get; set; }
        [DataMember]
        public string returnToLocation { get; set; }
        [DataMember]
        public string returnFromLocation { get; set; }
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
        public int fromLocationID { get; set; }
        [DataMember]
        public int planeID { get; set; }
        [DataMember]
        public int toLocationID { get; set; }
        [DataMember]
        public DateTime date { get; set; }
        [DataMember]
        public float discount { get; set; }
        [DataMember]
        public float price { get; set; }
    }
    [DataContract]
    public class Order
    {
        [DataMember]
        public int orderID { get; set; }
        [DataMember]
        public int userID { get; set; }
        [DataMember]
        public int initialFlightID { get; set; }
        [DataMember]
        public int returnFlightID { get; set; }

    }






}
