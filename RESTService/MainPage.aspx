<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="MainPage.aspx.cs" Inherits="RESTService.MainPage" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title>Main page</title>
    
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <link type="text/css" href="style.css" rel="stylesheet"/>
     <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    
</head>


<body>

    <!-- #######################################################  Navigation bar ####################################################################### -->
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



<div class="row">

  <div class="col-6">
  <div id="map"></div>
  </div>

  <aside class="col-6">

  <fieldset>
    <legend><b> Add address </b></legend>

    <div style="float:left; position:relative; width:100%; display:inline-block;">     
      <p id="total-distance"> Total Distance: 0.0km</p>
      <p id="total-duration"> Total Duration: 0min</p>   
      <input size=25 id="address" type="text" name="address" autofocus />
      <button id="add" onclick="handleAddressInput();">Add</button> <br>
    </div>

  </fieldset>


  <div class="tab" style="float:left;" >
    <button id="defaultTab" class="tabs" onclick="setContent(event, 'route')">Route</button>

    <select id="travelType" onchange="displayRoute(); setContent(null, `route`); clearList();">
        <option value="DRIVING">Driving</option>
        <option value="WALKING">Walking</option>
        <!-- <option value="BICYCLING">Bicycling</option> -->
        
        <!--<option value="TRANSIT">Transit</option>-->
      </select>

  </div>

  <div style="float:right; padding-right:1%;">
      <button id="back_route" style="display: none;">&larr; Back to route</button>
      <button id="back_list" style="display: none;">&larr; Back to list</button>    
  </div>


   

  <div style="height:400px; width:100%; overflow-y:scroll;" id="address_view"> 

    <div id="route" class="content"> 
      <table id="point-table">
        <tr>
          <th></th>
          <th></th>
          <th>Address</th>
          <th>Distance</th>
          <th>Time</th>
          <th></th>
          <th></th>
        </tr>
      </table>
    </div>


    <div id="list" class="content" style="display:none; text-align: center;">
        <h1 id="list_title"> LIST </h1>
        <!--<button onclick="showLocationStatistics()">Show statistics</button>-->

        <div id="rating_distribution" style="width:99%;">
        </div>

        <table id="acc-table">
          <tr>
            <th>Rating/10</th>
            <th>Name</th>
            <th>Price/night</th>
            <th>Distance</th>
            <th>Time</th>
            <th></th>
            <th></th>
          </tr>
        </table>

    </div>

    <div id="description" class="content" style="display:none; text-align: center;">
        <h1> DESCRIPTION </h1>
        <h3 id="description_name"> Name: </h3> 
        <h3 id="description_score"> Score: </h3> 
        <h3 id="description_price"> Price/night: </h3> 
        <h3 id="description_distance"> Distance: </h3> 
        <h3 id="description_time"> Travel time: </h3> 

        <!--<button onclick="showAccomondationStatistics()">Show statistics</button><br>-->
        <div id="rating_over_time" style="width:99%;">
        </div>

        <img id="acc_img" src="https://s-media-cache-ak0.pinimg.com/736x/39/f6/e0/39f6e0639465c7e01eaa79e26ada2a48.jpg" 
        width="90%" style="padding-top: 10px; height: 400px; width: auto;"/>

        <p id="acc_description" style="text-align: justify; width: 96%;">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sed mattis lorem, eu consectetur neque. Sed ut venenatis orci, vel ultricies mauris. Nunc nec diam quis risus ornare mattis. Cras euismod pharetra justo et vulputate. Nunc viverra faucibus tellus, quis lacinia odio bibendum in. Quisque imperdiet odio id odio gravida condimentum. Nullam posuere aliquet tellus iaculis tempus. Praesent auctor dolor nec mattis lacinia. Sed quam lorem, convallis id ultrices ac, elementum sed nunc.

            Morbi vestibulum lacinia porttitor. Etiam venenatis vel odio et sagittis. Ut hendrerit metus est, ac commodo lectus ullamcorper a. In nec egestas lectus, non placerat mauris. Nunc finibus at justo non convallis. Duis in eros eu massa finibus fringilla non a urna. Suspendisse egestas in metus eget mattis. Quisque id elementum tellus. Nunc mollis pretium justo, id congue felis rutrum at. Maecenas blandit maximus semper. Donec nisi purus, elementum a aliquam vitae, tristique nec libero. Nam ut tempor tellus. Nulla a ultrices velit. Aenean luctus semper commodo.

            Nulla nec sapien lacinia erat tempus laoreet. Nulla consequat sagittis enim, quis condimentum eros. Morbi accumsan dolor et ante dignissim, non finibus orci maximus. Integer fringilla eros quis massa lobortis iaculis. Nam sit amet leo ut massa molestie hendrerit ut quis arcu. Proin finibus, lectus quis auctor viverra, enim ex aliquet sapien, id aliquam purus odio ac ligula. Praesent vel pulvinar eros, eget ultrices lectus. Quisque feugiat commodo odio. Duis dictum tempus turpis et mollis. Etiam id vulputate ipsum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Etiam porttitor suscipit fermentum. Sed vehicula libero nunc, commodo porttitor orci molestie et. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Nunc consequat luctus dui, eget consectetur dolor eleifend vel. Quisque sit amet mollis nulla, ut finibus nibh.
        </p>

    </div>


    
  </div>

     <!-- ############################################# form display all travels in grid ######################################################-->

 <form id="form1" runat="server">
    <asp:GridView ID="GridViewFlights" runat="server" AutoGenerateColumns="False" DataSourceID="ObjectDataSource1">
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
    <asp:ObjectDataSource ID="ObjectDataSource1" runat="server" DataObjectTypeName="RESTService.TravelReceiveInfo" InsertMethod="AddTravel" SelectMethod="ReturnTravels" TypeName="RESTService.ServiceTravelData">
        <SelectParameters>
            <asp:SessionParameter DefaultValue="&quot;-1&quot;" Name="googleID" SessionField="googleID" Type="String" />
        </SelectParameters>
    </asp:ObjectDataSource>
    </form>

       <!-- ############################################# finish display travels ######################################################-->




  </aside>
</div>


<footer class="col-12">
  Matic Vrtačnik, 63150317<br>
  Jernej Habjan, 63150106
</footer>

    
<script>
    function initMap() {
        directionsService = new google.maps.DirectionsService;
        directionsDisplay = new google.maps.DirectionsRenderer;
        geocoder = new google.maps.Geocoder();
        map = new google.maps.Map(document.getElementById('map'),
            { zoom: 9, center: { lat: 46.0661123, lng: 14.4620601 } });

        directionsDisplay.setMap(map);
    }
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC7yGt8l4pdtPqap0Eby1soUByYKqzmZNo&callback=initMap&libraries=geometry"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<script src="globals.js"> </script>
<script src="query.js"> </script>
<script src="script.js"> </script>



    
    



</body>

</html>