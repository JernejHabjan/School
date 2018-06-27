using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Text;

namespace WcfService1
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the interface name "IService1" in both code and config file together.
    [ServiceContract]
    public interface IService1
    {

        [OperationContract]
        string GetData(int value);
        [OperationContract]
        [WebGet(UriTemplate = "Ime/{id}")]
        string vrniIme(int id);
        [OperationContract]
        [WebGet(UriTemplate = "Produkti")]
        List<Produkt> vrniProdukte();
   
        


        [OperationContract]
        CompositeType GetDataUsingDataContract(CompositeType composite);

        // TODO: Add your service operations here

    }
    [DataContract]
    public class Produkt
    {
        private int v1;
        private string v2;
        private string v3;

        public Produkt(int v1, string v2, string v3)
        {
            this.v1 = v1;
            this.v2 = v2;
            this.v3 = v3;
        }

        [DataMember]
        public int ProduktId { get; set; }
        [DataMember]
        public string ProduktIme { get; set; }
        [DataMember]
        public string ProduktKategorija { get; set; }
    }

    // Use a data contract as illustrated in the sample below to add composite types to service operations.
    [DataContract]
    public class CompositeType
    {
        bool boolValue = true;
        string stringValue = "Hello ";

        [DataMember]
        public bool BoolValue
        {
            get { return boolValue; }
            set { boolValue = value; }
        }

        [DataMember]
        public string StringValue
        {
            get { return stringValue; }
            set { stringValue = value; }
        }
    }

  
}
