<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="Flight.aspx.cs" Inherits="RESTService.Flight1" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Flight</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link type="text/css" href="style.css" rel="stylesheet"/>
</head>
<body>
   

     <!-- ##########################################################  Navigation bar #####################################################   -->
    
    <nav class="navbar navbar-inverse" >
      <div class="container-fluid">
        <div class="navbar-header" >
          <a class="navbar-brand active"  href="http://asistentslivko.azurewebsites.net/MainPage.aspx">Asistent Slivko</a>
        </div>
        <ul class="nav navbar-nav">
     
          <li><a href="http://asistentslivko.azurewebsites.net/Flight.aspx" >Plan</a></li>
 
        </ul>
        <ul class="nav navbar-nav navbar-right">
 
          <li><a href="http://asistentslivko.azurewebsites.net/Login.aspx" > <span class="glyphicon glyphicon-log-in"> <span  id="loginNav" runat="server" >Login</span></span></a></li>
        </ul>
      </div>
    </nav>

 <form id="form1" runat="server">

    <!-- ###############################################################  Flight data #####################################################  -->
        
            <h1 style="text-align:center" >Podatki o letu</h1>
            
            <asp:GridView ID="GridView2" runat="server" AutoGenerateColumns="False" DataSourceID="CurrentTravelDataInfo">
                <Columns>
                    <asp:BoundField DataField="orderID" HeaderText="orderID" SortExpression="orderID" />
                    <asp:BoundField DataField="fromLocationName" HeaderText="fromLocationName" SortExpression="fromLocationName" />
                    <asp:BoundField DataField="toLocationName" HeaderText="toLocationName" SortExpression="toLocationName" />
                    <asp:BoundField DataField="initialPrice" HeaderText="initialPrice" SortExpression="initialPrice" />
                    <asp:BoundField DataField="initialDate" HeaderText="initialDate" SortExpression="initialDate" />
                    <asp:BoundField DataField="initialDiscount" HeaderText="initialDiscount" SortExpression="initialDiscount" />
                    <asp:BoundField DataField="initialPlaneName" HeaderText="initialPlaneName" SortExpression="initialPlaneName" />
                    <asp:BoundField DataField="initialPlaneCompany" HeaderText="initialPlaneCompany" SortExpression="initialPlaneCompany" />
                    <asp:BoundField DataField="returnDate" HeaderText="returnDate" SortExpression="returnDate" />
                    <asp:BoundField DataField="returnDiscount" HeaderText="returnDiscount" SortExpression="returnDiscount" />
                    <asp:BoundField DataField="returnPrice" HeaderText="returnPrice" SortExpression="returnPrice" />
                    <asp:BoundField DataField="returnPlaneName" HeaderText="returnPlaneName" SortExpression="returnPlaneName" />
                    <asp:BoundField DataField="returnPlaneCompany" HeaderText="returnPlaneCompany" SortExpression="returnPlaneCompany" />
                    <asp:BoundField DataField="returnToLocation" HeaderText="returnToLocation" SortExpression="returnToLocation" />
                    <asp:BoundField DataField="returnFromLocation" HeaderText="returnFromLocation" SortExpression="returnFromLocation" />
                </Columns>
             </asp:GridView>
             <asp:ObjectDataSource ID="CurrentTravelDataInfo" runat="server" SelectMethod="ReturnTravel" TypeName="RESTService.ServiceTravelData">
                 <SelectParameters>
                     <asp:SessionParameter DefaultValue="&quot;-1&quot;" Name="googleID" SessionField="googleID" Type="String" />
                     <asp:SessionParameter DefaultValue="-1" Name="orderID" SessionField="orderID" Type="String" />
                 </SelectParameters>
             </asp:ObjectDataSource>
     <div id="inputTravelData" runat="server">
            <table>
                <tr>
                    <th style="text-align:center"><asp:Label runat="server" Text="Mesto odhoda"></asp:Label></th>
                    <th style="text-align:center"><asp:Label runat="server" Text="Mesto prihoda"></asp:Label></th>
                    <th style="text-align:center"><asp:Label runat="server" Text="Datum odhoda"></asp:Label></th>
                    <th style="text-align:center"><asp:Label runat="server" Text="Razred odhoda"></asp:Label></th>
                </tr>
                <tr style="text-align:center">
                    <td><input id="mestoOdhoda_input" class="textInput" type="text" runat="server"/></td>
                    <td><input id="mestoPrihoda_input" class="textInput" type="text" runat="server"/></td>
                    <td><input id="datumOdhoda_date" class="textInput" type="date" runat="server"/></td>
                    <td><asp:DropDownList ID="razredOdhoda_drop" runat="server">
                           <asp:ListItem Selected="True">Prvi</asp:ListItem>
                           <asp:ListItem>Drugi</asp:ListItem>
                       </asp:DropDownList>
                   </td>
                </tr>
            </table>
            <br />
            <p style="text-align:center"><asp:Label runat="server" Text="Dvosmerna"></asp:Label>
                <br />
                <asp:CheckBox ID="dvosmerna_checkbox" runat="server" OnCheckedChanged="dvosmerna_checkbox_CheckedChanged"  AutoPostBack="True" />
            </p>
            <br />
            <table id="dvosmernaTable" runat="server">
                 <tr >
                    <th style="text-align:center"><asp:Label runat="server" Text="Datum prihoda"></asp:Label></th>
                    <th style="text-align:center"><asp:Label runat="server" Text="Razred prihoda"></asp:Label></th>
                </tr>
                <tr style="text-align:center">
                    <td><input id="datumPrihoda_date" class="textInput" type="date" runat="server"/></td>
                    <td><asp:DropDownList ID="razredPrihoda_drop" runat="server">
                           <asp:ListItem Selected="True">Prvi</asp:ListItem>
                           <asp:ListItem>Drugi</asp:ListItem>
                       </asp:DropDownList>
                   </td>
                </tr>

            </table>
         
        </div>






        <!--###################################################### Passenger data #######################################################-->
        
            <h1 style="text-align:center" >Potniki</h1>
            
 
            <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" DataSourceID="PotnikiDataSource">
                <Columns>
                    <asp:BoundField DataField="name" HeaderText="name" SortExpression="name" />
                    <asp:BoundField DataField="surname" HeaderText="surname" SortExpression="surname" />
                    <asp:BoundField DataField="gender" HeaderText="gender" SortExpression="gender" />
                    <asp:BoundField DataField="age" HeaderText="age" SortExpression="age" />
                </Columns>
            </asp:GridView>
            <asp:ObjectDataSource ID="PotnikiDataSource" runat="server" SelectMethod="ReturnPassengers" TypeName="RESTService.ServicePersonData">
                <SelectParameters>
                    <asp:SessionParameter DefaultValue="-1" Name="orderID" SessionField="orderID" Type="String" />
                </SelectParameters>
            </asp:ObjectDataSource>
            
            <br />
     <div id="inputPassenger" runat="server">
            <table>
                <tr>
                    <th><asp:Label runat="server" Text="Ime"></asp:Label></th>
                    <th><asp:Label runat="server" Text="Priimek"></asp:Label></th>
                    <th><asp:Label runat="server" Text="Spol"></asp:Label></th>
                    <th><asp:Label runat="server" Text="Rojstvo"></asp:Label></th>

                </tr>
                <tr>
                    <td><input id="ime_input" class="textInput" type="text" runat="server"/><br /></td>
                    <td><input id="priimek_input" class="textInput" type="text" runat="server"/><br /></td>
                    <td><input id="spol_input" class="textInput" type="text" runat="server"/><br /></td>
                    <td><input id="rojstvo_input" class="textInput" type="text" runat="server"/><br /></td>
                    <td><asp:Button ID="b_accept" class="button1" runat="server" OnClick="b_accept_Click" Text="Dodaj potnika" /></td>
                </tr>

            </table>
     

        </div>
        <!-- ########################################################## Finish order ######################################################### -->

        <div id="zakljucekPlacila" runat="server">
            <h1 style="text-align:center" >Zaključek plačila</h1>
       

            <br />
            <p style="text-align:center"><asp:Label runat="server" Text="Kreditna kartica: "></asp:Label> 
                <br />
                <asp:CheckBox ID="kartica_checkbox" runat="server" OnCheckedChanged="kartica_checkbox_CheckedChanged" AutoPostBack="True" />
            </p>
            
      
            <table runat="server" id="karticaTable" >
                 <tr >
                    <th style="text-align:center"><asp:Label runat="server" Text="Ime plačnika"></asp:Label></th>
                    <th style="text-align:center"><asp:Label runat="server" Text="Priimek plačnika"></asp:Label></th>
                     <th style="text-align:center"><asp:Label runat="server" Text="Kartica"></asp:Label></th>
                </tr>
                <tr style="text-align:center">
                    <td><input id="imePlacnika_input" class="textInput" type="text" runat="server"/></td>
                    <td><input id="priimekPlacnika_input" class="textInput" type="text" runat="server"/></td>
                    <td><input id="kartica_input" class="textInput" type="number" runat="server"/></td>
                </tr>

            </table>
                <p style="text-align:center"><asp:Label id="cena_label" runat="server" Text="Končna cena: "></asp:Label> </p>
                <br />
                <p style="text-align:center"><asp:Button ID="b_potrdiNarocilo" class="button1" style="width:200px;" runat="server" OnClick="b_potrdiNarocilo_Click" Text="Potrdi naročilo" /></p>
        </div>
    </form>




</body>
</html>
