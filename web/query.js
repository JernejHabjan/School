
var markers = [];
var selectedLocation = 0;

function calcDistance(lat1, lng1, lat2, lng2){
	var _point1 = new google.maps.LatLng(lat1, lng1);
	var _point2 = new google.maps.LatLng(lat2, lng2);

	var _distanceInM = google.maps.geometry.spherical.
		computeDistanceBetween(_point1, _point2);

	return parseFloat((_distanceInM / 1000.0).toFixed(1));
}

function addMarker(title, latitude, longitude, scoreChange, showButton){
	icon = scoreChange == null ? 
		'https://mt.googleapis.com/vt/icon/name=icons/spotlight/spotlight-poi.png' : 
		"https://mt.google.com/vt/icon?psize=30&font=fonts/arialuni_t.ttf&color=ff304C13&name=icons/spotlight/spotlight-waypoint-a.png&ax=43&ay=48&text=%E2%80%A2";

	if(scoreChange == 0.0){
		icon = 'https://mt.google.com/vt/icon?color=ff004C13&name=icons/spotlight/spotlight-waypoint-blue.png';
	}else if(scoreChange < 0.0){
		icon = 'http://mt.googleapis.com/vt/icon/name=icons/spotlight/spotlight-poi.png';
	}

	var marker = new google.maps.Marker({
		position: {lat: latitude, lng: longitude},
		map: map,
		title: title,
		icon: icon
	});

	marker.addListener("click", function(){
		showButton.click();
	});

	markers.push(marker);
}

function addAccomondations(cityName, cityLat, cityLng){
	//PREFERENCES
	var _allowPets = document.getElementById("allow_pets").checked;
	var _requireHeating = document.getElementById("require_heating").checked;
	var _requireHouse = document.getElementById("require_house").checked;
	var _requireBreakfast = document.getElementById("require_breakfast").checked;
	var _requireFamilyFriendly = document.getElementById("require_family").checked;
	var _requireCableTV = document.getElementById("require_cable_tv").checked;
	var _requireWireless = document.getElementById("require_wireless").checked;
	var _requireAirConditioning = document.getElementById("require_air_conditioning").checked;
	var _requireFreeParking = document.getElementById("require_free_parking").checked;
	var _requireKitchen = document.getElementById("require_kitchen").checked;
	var _minPrice = document.getElementById("minPrice").value;
	var _maxPrice = document.getElementById("maxPrice").value;
	var _top10 = document.getElementById("top10").checked;

	var _args = 
		"city=" + cityName + "&" +
		"pets=" + _allowPets + "&" +
		"heating=" + _requireHeating + "&" +
		"house=" + _requireHouse + "&" +
		"breakfast=" + _requireBreakfast + "&" +
		"family=" + _requireFamilyFriendly + "&" +
		"cableTV=" + _requireCableTV + "&" +
		"wireless=" + _requireWireless + "&" +
		"airConditioning=" + _requireAirConditioning + "&" +
		"freeParking=" + _requireFreeParking + "&" +
		"kitchen=" + _requireKitchen + "&" +
		"minPrice=" + _minPrice + "&" +
		"maxPrice=" + _maxPrice + "&" +
		"top=" + _top10;
	
	$.ajax({
		type: "GET",
		url: "../python/query.php",
		data: _args,
		success: function(data){
			//console.log(data);
			var rows = data.split("\n");
			//console.log(rows.length);

			//var maxPrice = 0.0;
			var _validEntries = 0;	
			var hashmap = {};

			for(var i = 0; i < rows.length - 1; ++i){
				var rowData = rows[i].split("$$");
				if(rowData.length < 10)continue;
				//console.log(rowData);
				
				var score = parseFloat(rowData[0]);
				var price = parseFloat(rowData[1]);
				var type = rowData[2];
				var lat = parseFloat(rowData[3]);
				var lng = parseFloat(rowData[4]);
				var desc = rowData[5];		
				var image = rowData[6];
				var thumbnail = rowData[7];
				var scoreOverTime = rowData[8];
				var scoreChange = parseFloat(rowData[9]);

				//console.log(score);

				scoreOverTime =  scoreOverTime.length < 2 ? "" : 
					scoreOverTime.substring(1, scoreOverTime.length - 1);

				valuePairs = scoreOverTime.split(", ");
				var times = [];
				var scores = [];

				for(var j = 0; j < valuePairs.length; ++j){
					var pair = valuePairs[j].split(": ");
					times.push(pair[0]);
					scores.push(pair[1]);
				}

				//TODO change to scoreChange attribute?
				scoreChange = scores[scores.length - 1] - scores[scores.length - 2];

				var distance = calcDistance(cityLat, cityLng, lat, lng);
				
				addAccomondationEntry(cityName, lat, lng, distance, type, score, desc, image, thumbnail,
					times, scores, scoreChange, price);

				if(score in hashmap){
					hashmap[score] += 1;
				}else{ 
					hashmap[score] = 1;
				}				

				_validEntries += 1;
			}

			document.getElementById("list_title").innerHTML = cityName + " - " + _validEntries + " entries";

			var scoreKeys = [];
			var scoreValues = [];
			for(var key in hashmap){
				scoreKeys.push(key);
				scoreValues.push(hashmap[key]);
			}

			var data = {x: scoreKeys, y: scoreValues, type: 'bar'};
			var layout = { title: "Rating Distribution", xaxis: { title: "Score" }, yaxis: { title: "Count" } };
			Plotly.newPlot('rating_distribution', [data], layout);
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
        	_row.cells[3].innerHTML = _km + "km";

            var _min = Math.round(_route.legs[0].duration.value / 60);
            _row.cells[4].innerHTML = convertTime(_min).string;

          } else {
            alert("Directions error: " + status);
          }
    });
}

function addAccomondationEntry(
	cityName, lat, lng, distance, type, score, description, image, thumbnail,
	times, scores, scoreChange, price)
{
	var _table = document.getElementById("acc-table");
	var _rowId = _table.rows.length;
	var _row = _table.insertRow(_rowId);

	var _travelMode = document.getElementById("travelType").value; 
	var _min = Math.round(_travelMode == "WALKING" ? 
		(distance * 15.0) : (distance * 2.5));

	_row.insertCell(0).innerHTML = score;
	_row.insertCell(1).innerHTML = cityName + " " + type;
	_row.insertCell(2).innerHTML = price + "$";
	_row.insertCell(3).innerHTML = distance + "km";
	_row.insertCell(4).innerHTML = convertTime(_min).string;
	_row.insertCell(5).innerHTML = "<img src='" + thumbnail + "' style='height:50px;'/>";
	_row.insertCell(6).innerHTML = "<button class='tabs'>Show</button>";

	_row.cells[6].addEventListener("click", function(){
		var _index = this.parentElement.rowIndex;
		setContent(null, 'description');

		document.getElementById("back_route").style.display = "none";
		document.getElementById("back_list").style.display = "inline";

		document.getElementById("acc_description").innerHTML = description;

		document.getElementById("acc_img").src = "";
		document.getElementById("acc_img").src = image;

		setRouteInfo(cityName, lat, lng, _rowId, true);

		var _score = { x: times, y: scores, type: 'scatter' };
		var layout = {title: "Score Over Time", xaxis: { title: "Time" }, yaxis: { title: "Score" } };
		Plotly.newPlot('rating_over_time', [_score], layout);

		var rowCells = document.getElementById("acc-table").rows[_index].cells;
		document.getElementById("description_name").innerHTML = "Name: " + rowCells[1].innerHTML;
		document.getElementById("description_score").innerHTML = "Score: " + rowCells[0].innerHTML;
		document.getElementById("description_price").innerHTML = "Price/night: " + rowCells[2].innerHTML;
		document.getElementById("description_distance").innerHTML = "Distance: " + rowCells[3].innerHTML;
		document.getElementById("description_time").innerHTML = "Travel time: " + rowCells[4].innerHTML;

		//not working async
		//map.setCenter(new google.maps.LatLng(lat, lng));
        //map.setZoom(25);
	});

	addMarker(cityName + " " + type, lat, lng, scoreChange, _row.cells[6]);
	//setRouteInfo(cityName, lat, lng, _rowId);
}

function removeRoute(){
	if(directionsDisplay != null){
		directionsDisplay.setMap(null);
		directionsDisplay = null;
	}

    directionsDisplay = new google.maps.DirectionsRenderer;
    directionsDisplay.setMap(map);
}

function clearList(){
	var _table = document.getElementById("acc-table");

	for(var i = _table.rows.length; i > 1; --i){
		_table.deleteRow(i - 1);
	}

	for(var i = 0; i < markers.length; ++i){
		markers[i].setMap(null);
	}markers = [];


	selectedLocation = 0;
	//remove route
	//removeRoute();
}

function getClosestCity(lat, lng){
	var cities = [
		{	name: "Asheville", 			lat: 35.5388506, 	lng: -82.7058334	},
		{	name: "Austin", 			lat: 30.3076859, 	lng: -97.8938299	},
		{	name: "Boston", 			lat: 42.3134791, 	lng: -71.1271964	},
		{	name: "Chicago", 			lat: 41.8339037, 	lng: -87.8722374	},
		{	name: "Denver", 			lat: 39.739167, 	lng: -104.985278	},
		{	name: "Los_Angeles", 		lat: 34.0207504, 	lng: -118.6919112	},
		{	name: "Nashville", 			lat: 36.186836, 	lng: -86.9253278	},
		{	name: "New_Orleans", 		lat: 30.0217736, 	lng: -89.9525101	},
		{	name: "New_York", 			lat: 40.7058253, 	lng: -74.1180855	},
		{	name: "Oakland", 			lat: 37.7920764, 	lng: -122.2988336	},
		{	name: "Portland", 			lat: 45.5425353, 	lng: -122.7244614	},
		{	name: "San_Diego", 			lat: 32.824763, 	lng: -117.2352459	},
		{	name: "San_Francisco", 		lat: 37.7577627, 	lng: -122.4726193	},
		{	name: "Santa_Cruz_County", 	lat: 37.0686171, 	lng: -122.0895003	},
		{	name: "Seattle", 			lat: 47.6149425, 	lng: -122.4059452	},
		{	name: "Washington", 		lat: 38.8994613, 	lng: -77.084606		}
	];

	var closestCity = cities[0].name;
	var closestDistance = calcDistance(lat, lng, cities[0].lat, cities[0].lng);

	for(var i = 1; i < cities.length; ++i){
		var city = cities[i];
		var cityDistance = calcDistance(lat, lng, city.lat, city.lng);
		if(cityDistance < closestDistance){
			closestDistance = cityDistance;
			closestCity = city.name;
		}
	}

	return closestCity;
}

function queryList(rowId){
	var _table = document.getElementById("point-table");
	var _row = _table.rows[rowId];
	var address = _row.cells[ADDRESS_ID].innerHTML;
	//console.log(address);

	clearList();
	selectedLocation = rowId;

	//console.log(_row.cells[ARRIVAL_ID].innerHTML);
	//console.log(document.getElementById("arrival_" + rowId).value);
	
	geocoder.geocode({'address': address}, function(results, status) {
    	if (status === 'OK') {
    		map.setCenter(results[0].geometry.location);

            var _address = results[0].formatted_address;
    		var _lat = results[0].geometry.location.lat();
    		var _lng = results[0].geometry.location.lng();


			var closestCity = getClosestCity(_lat, _lng);
			//console.log("CLOSEST CITY: " + closestCity);
			addAccomondations(closestCity, _lat, _lng);
 		
			markers.push(new google.maps.Marker({
 				position: {lat: _lat, lng: _lng},
 				map: map,
 				title: _address,
 				icon: 'http://maps.google.com/mapfiles/ms/icons/green-dot.png'
 			}));

 			map.setZoom(13);

          } else {
            alert("Unable to find: " + "'" + address + "' (" + status + ")");
          }
    });
}

function reloadQueryList(){
	if(selectedLocation > 0){
		queryList(selectedLocation);
	}	
}
