﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.42000
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace RESTService.ServiceReferencePersonData {
    using System.Runtime.Serialization;
    using System;
    
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="User", Namespace="http://schemas.datacontract.org/2004/07/RESTService")]
    [System.SerializableAttribute()]
    public partial class User : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string emailField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string googleIDField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string nameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int roleIDField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int userIDField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string email {
            get {
                return this.emailField;
            }
            set {
                if ((object.ReferenceEquals(this.emailField, value) != true)) {
                    this.emailField = value;
                    this.RaisePropertyChanged("email");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string googleID {
            get {
                return this.googleIDField;
            }
            set {
                if ((object.ReferenceEquals(this.googleIDField, value) != true)) {
                    this.googleIDField = value;
                    this.RaisePropertyChanged("googleID");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string name {
            get {
                return this.nameField;
            }
            set {
                if ((object.ReferenceEquals(this.nameField, value) != true)) {
                    this.nameField = value;
                    this.RaisePropertyChanged("name");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int roleID {
            get {
                return this.roleIDField;
            }
            set {
                if ((this.roleIDField.Equals(value) != true)) {
                    this.roleIDField = value;
                    this.RaisePropertyChanged("roleID");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int userID {
            get {
                return this.userIDField;
            }
            set {
                if ((this.userIDField.Equals(value) != true)) {
                    this.userIDField = value;
                    this.RaisePropertyChanged("userID");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.Runtime.Serialization", "4.0.0.0")]
    [System.Runtime.Serialization.DataContractAttribute(Name="Passenger", Namespace="http://schemas.datacontract.org/2004/07/RESTService")]
    [System.SerializableAttribute()]
    public partial class Passenger : object, System.Runtime.Serialization.IExtensibleDataObject, System.ComponentModel.INotifyPropertyChanged {
        
        [System.NonSerializedAttribute()]
        private System.Runtime.Serialization.ExtensionDataObject extensionDataField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int ageField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string genderField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string nameField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private int passengerIDField;
        
        [System.Runtime.Serialization.OptionalFieldAttribute()]
        private string surnameField;
        
        [global::System.ComponentModel.BrowsableAttribute(false)]
        public System.Runtime.Serialization.ExtensionDataObject ExtensionData {
            get {
                return this.extensionDataField;
            }
            set {
                this.extensionDataField = value;
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int age {
            get {
                return this.ageField;
            }
            set {
                if ((this.ageField.Equals(value) != true)) {
                    this.ageField = value;
                    this.RaisePropertyChanged("age");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string gender {
            get {
                return this.genderField;
            }
            set {
                if ((object.ReferenceEquals(this.genderField, value) != true)) {
                    this.genderField = value;
                    this.RaisePropertyChanged("gender");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string name {
            get {
                return this.nameField;
            }
            set {
                if ((object.ReferenceEquals(this.nameField, value) != true)) {
                    this.nameField = value;
                    this.RaisePropertyChanged("name");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public int passengerID {
            get {
                return this.passengerIDField;
            }
            set {
                if ((this.passengerIDField.Equals(value) != true)) {
                    this.passengerIDField = value;
                    this.RaisePropertyChanged("passengerID");
                }
            }
        }
        
        [System.Runtime.Serialization.DataMemberAttribute()]
        public string surname {
            get {
                return this.surnameField;
            }
            set {
                if ((object.ReferenceEquals(this.surnameField, value) != true)) {
                    this.surnameField = value;
                    this.RaisePropertyChanged("surname");
                }
            }
        }
        
        public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;
        
        protected void RaisePropertyChanged(string propertyName) {
            System.ComponentModel.PropertyChangedEventHandler propertyChanged = this.PropertyChanged;
            if ((propertyChanged != null)) {
                propertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(propertyName));
            }
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="ServiceReferencePersonData.IServicePersonData")]
    public interface IServicePersonData {
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/ReturnUsers", ReplyAction="http://tempuri.org/IServicePersonData/ReturnUsersResponse")]
        RESTService.ServiceReferencePersonData.User[] ReturnUsers();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/ReturnUsers", ReplyAction="http://tempuri.org/IServicePersonData/ReturnUsersResponse")]
        System.Threading.Tasks.Task<RESTService.ServiceReferencePersonData.User[]> ReturnUsersAsync();
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/ReturnUser", ReplyAction="http://tempuri.org/IServicePersonData/ReturnUserResponse")]
        RESTService.ServiceReferencePersonData.User ReturnUser(string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/ReturnUser", ReplyAction="http://tempuri.org/IServicePersonData/ReturnUserResponse")]
        System.Threading.Tasks.Task<RESTService.ServiceReferencePersonData.User> ReturnUserAsync(string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/AddUser", ReplyAction="http://tempuri.org/IServicePersonData/AddUserResponse")]
        void AddUser(RESTService.ServiceReferencePersonData.User user);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/AddUser", ReplyAction="http://tempuri.org/IServicePersonData/AddUserResponse")]
        System.Threading.Tasks.Task AddUserAsync(RESTService.ServiceReferencePersonData.User user);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/RemoveUser", ReplyAction="http://tempuri.org/IServicePersonData/RemoveUserResponse")]
        void RemoveUser(string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/RemoveUser", ReplyAction="http://tempuri.org/IServicePersonData/RemoveUserResponse")]
        System.Threading.Tasks.Task RemoveUserAsync(string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/UpdateUser", ReplyAction="http://tempuri.org/IServicePersonData/UpdateUserResponse")]
        void UpdateUser(RESTService.ServiceReferencePersonData.User user, string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/UpdateUser", ReplyAction="http://tempuri.org/IServicePersonData/UpdateUserResponse")]
        System.Threading.Tasks.Task UpdateUserAsync(RESTService.ServiceReferencePersonData.User user, string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/ReturnPassengers", ReplyAction="http://tempuri.org/IServicePersonData/ReturnPassengersResponse")]
        RESTService.ServiceReferencePersonData.Passenger[] ReturnPassengers(string googleID);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/IServicePersonData/ReturnPassengers", ReplyAction="http://tempuri.org/IServicePersonData/ReturnPassengersResponse")]
        System.Threading.Tasks.Task<RESTService.ServiceReferencePersonData.Passenger[]> ReturnPassengersAsync(string googleID);
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface IServicePersonDataChannel : RESTService.ServiceReferencePersonData.IServicePersonData, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class ServicePersonDataClient : System.ServiceModel.ClientBase<RESTService.ServiceReferencePersonData.IServicePersonData>, RESTService.ServiceReferencePersonData.IServicePersonData {
        
        public ServicePersonDataClient() {
        }
        
        public ServicePersonDataClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public ServicePersonDataClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ServicePersonDataClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public ServicePersonDataClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        public RESTService.ServiceReferencePersonData.User[] ReturnUsers() {
            return base.Channel.ReturnUsers();
        }
        
        public System.Threading.Tasks.Task<RESTService.ServiceReferencePersonData.User[]> ReturnUsersAsync() {
            return base.Channel.ReturnUsersAsync();
        }
        
        public RESTService.ServiceReferencePersonData.User ReturnUser(string googleID) {
            return base.Channel.ReturnUser(googleID);
        }
        
        public System.Threading.Tasks.Task<RESTService.ServiceReferencePersonData.User> ReturnUserAsync(string googleID) {
            return base.Channel.ReturnUserAsync(googleID);
        }
        
        public void AddUser(RESTService.ServiceReferencePersonData.User user) {
            base.Channel.AddUser(user);
        }
        
        public System.Threading.Tasks.Task AddUserAsync(RESTService.ServiceReferencePersonData.User user) {
            return base.Channel.AddUserAsync(user);
        }
        
        public void RemoveUser(string googleID) {
            base.Channel.RemoveUser(googleID);
        }
        
        public System.Threading.Tasks.Task RemoveUserAsync(string googleID) {
            return base.Channel.RemoveUserAsync(googleID);
        }
        
        public void UpdateUser(RESTService.ServiceReferencePersonData.User user, string googleID) {
            base.Channel.UpdateUser(user, googleID);
        }
        
        public System.Threading.Tasks.Task UpdateUserAsync(RESTService.ServiceReferencePersonData.User user, string googleID) {
            return base.Channel.UpdateUserAsync(user, googleID);
        }
        
        public RESTService.ServiceReferencePersonData.Passenger[] ReturnPassengers(string googleID) {
            return base.Channel.ReturnPassengers(googleID);
        }
        
        public System.Threading.Tasks.Task<RESTService.ServiceReferencePersonData.Passenger[]> ReturnPassengersAsync(string googleID) {
            return base.Channel.ReturnPassengersAsync(googleID);
        }
    }
}