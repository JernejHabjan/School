
var markers = [];

function addMarker(address){
	geocoder.geocode({'address': address}, function(results, status) {
    	if (status === 'OK') {
            var _address = results[0].formatted_address;
    		var _lat = results[0].geometry.location.lat();
    		var _lng = results[0].geometry.location.lng();	
 			markers.push(new google.maps.Marker({
 				position: {lat: _lat, lng: _lng},
 				map: map,
 				title: _address
 			}));

          } else {
            alert("Unable to find: " + "'" + address + "' (" + status + ")");
          }
    });
}

function addLatLngMarker(title, latitude, longitude){
	markers.push(new google.maps.Marker({
		position: {lat: latitude, lng: longitude},
		map: map,
		title: title
	}));
}

function addAccomondations(cityName){
	//PREFERENCES
	var _allowPets = document.getElementById("allow_pets").checked;
	var _requireShower = document.getElementById("require_shower").checked;
	var _allowCamping = document.getElementById("allow_camping").checked;
	var _requireBreakfast = document.getElementById("require_breakfast").checked;
	var _requireLargerRoom = document.getElementById("require_larger_room").checked;
	
	$.ajax({
		type: "GET",
		url: "../python/query.php",
		data: "pets=" + _allowPets, //TODO all preferences
		success: function(data){
			var rows = data.split("\n")
			for(var i = 0; i < rows.length; ++i){
				var rowData = rows[i].split(",");
				console.log(rowData);
				
				var type = rowData[0];
				var lat = parseFloat(rowData[1]);
				var lng = parseFloat(rowData[2]);
				
				addAccomondationEntry(cityName, lat, lng, type, 5.0);
				
				//TODO remove -> PROBLEM: location service OVER_QUERY_LIMIT
				if(i > 5)break;
			}                        
		}
	})
	
}

function setRouteInfo(origin, latTo, lngTo, id, display){
	var _travelMode = document.getElementById("travelType").value; 
		directionsService.route({
          origin: origin, 
		  destination: new google.maps.LatLng(latTo, lngTo),
		  optimizeWaypoints: false,
          travelMode: google.maps.DirectionsTravelMode[_travelMode]
    	}, function(response, status) {
          if (status === 'OK') {

          	//display
            if(display != null && display){
            	directionsDisplay.setDirections(response);
            }

            var _table = document.getElementById("acc-table");
            var _row = _table.rows[id];
            var _route = response.routes[0];

            var _km = (_route.legs[0].distance.value / 1000.0).toFixed(1);
        	_row.cells[2].innerHTML = _km + "km";

            var _min = Math.round(_route.legs[0].duration.value / 60);
            _row.cells[3].innerHTML = convertTime(_min).string;

          } else {
            alert("Directions error: " + status);
          }
    });
}

function addAccomondationEntry(cityName, lat, lng, type, rating){
	var _table = document.getElementById("acc-table");
	var _rowId = _table.rows.length;
	var _row = _table.insertRow(_rowId);

	_row.insertCell(0).innerHTML = rating;
	_row.insertCell(1).innerHTML = cityName + " " + type;
	_row.insertCell(2).innerHTML = 0.0 + "km";
	_row.insertCell(3).innerHTML = "0m";
	_row.insertCell(4).innerHTML = "<button class='tabs'>Show</button>";

	_row.cells[4].addEventListener("click", function(){
		var _index = this.parentElement.rowIndex;
		setContent(null, 'description');

		document.getElementById("back_route").style.display = "none";
		document.getElementById("back_list").style.display = "inline";

		setRouteInfo(cityName, lat, lng, _rowId, true);
	});

	addLatLngMarker(cityName + " " + type, lat, lng);
	setRouteInfo(cityName, lat, lng, _rowId);
}

function clearList(){
	var _table = document.getElementById("acc-table");

	for(var i = _table.rows.length; i > 1; --i){
		_table.deleteRow(i - 1);
	}

	for(var i = 0; i < markers.length; ++i){
		markers[i].setMap(null);
	}markers = [];

	//remove route
	/*if(directionsDisplay != null){
		directionsDisplay.setMap(null);
		directionsDisplay = null;
	}

    directionsDisplay = new google.maps.DirectionsRenderer;
    directionsDisplay.setMap(map);*/
}

function queryList(rowId){
	var _table = document.getElementById("point-table");
	var _row = _table.rows[rowId];
	var address = "Asheville"; //_row.cells[ADDRESS_ID].innerHTML;

	clearList();
	
	//console.log(_row.cells[ARRIVAL_ID].innerHTML);
	console.log(document.getElementById("arrival_" + rowId).value);
	
	geocoder.geocode({'address': address}, function(results, status) {
    	if (status === 'OK') {
    		map.setCenter(results[0].geometry.location);

            var _address = results[0].formatted_address;
    		var _lat = results[0].geometry.location.lat();
    		var _lng = results[0].geometry.location.lng();
    		markers.push(new google.maps.Marker({
 				position: {lat: _lat, lng: _lng},
 				map: map,
 				title: _address,
 				icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
 			}));
			
			//TODO check if city exists	
			addAccomondations("Asheville");
 		
          } else {
            alert("Unable to find: " + "'" + address + "' (" + status + ")");
          }
    });
}


//TODO groups?
function showLocationStatistics(){

}

//TODO graphs?
function showAccomondationStatistics(){

}