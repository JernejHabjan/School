
function updateInsertId(){
	var _table = document.getElementById("point-table");
	document.getElementById("insert-id").max = _table.rows.length - 1;
	document.getElementById("insert-id").value = _table.rows.length - 1;
}

function calcTotalDistance(){
	var table = document.getElementById("point-table");

	var _distance = 0.0;
	for(var i = 1; i < table.rows.length; ++i){
		var _dv = table.rows[i].cells[5].innerHTML;
		var _d = parseFloat(_dv.substring(0, _dv.length - 2));
		_distance += _d;
	}

	return _distance;
}

function swapLocalStorageValues(id1, id2){
	if(id1 >= localStorage.length || id2 >= localStorage.length)return;

	var _tmp = localStorage.getItem(localStorage.key(id1));
	localStorage.setItem(localStorage.key(id1), localStorage.getItem(localStorage.key(id2)));
	localStorage.setItem(localStorage.key(id2), _tmp);

	//console.log(localStorage);
}

function swapTableValues(id1, id2){
	var _table = document.getElementById("point-table");

	var _tmp = _table.rows[id1].innerHTML;
	_table.rows[id1].innerHTML = _table.rows[id2].innerHTML;
	_table.rows[id2].innerHTML = _tmp;

	setRowListeners(_table.rows[id1]);
	setRowListeners(_table.rows[id2]);
}

function setRowListeners(row){
	if(row == null)return;

	//delete entry
	row.cells[7].addEventListener("click", function(){
		var _index = this.parentElement.rowIndex;
		localStorage.removeItem(localStorage.key(_index - 1));
		document.getElementById("point-table").deleteRow(_index);
		displayRoute();
		updateInsertId();
	});

	//move up
	row.cells[0].addEventListener("click", function(){
		var _index = this.parentElement.rowIndex;
		if(_index < 2)return;

		swapTableValues(_index, _index - 1);
		swapLocalStorageValues(_index - 1, _index - 2);
		displayRoute();
	});

	//move down
	row.cells[1].addEventListener("click", function(){
		var _table = document.getElementById("point-table");
		var _index = this.parentElement.rowIndex;
		if(_index >= _table.rows.length - 1)return;

		swapTableValues(_index, _index + 1);
		swapLocalStorageValues(_index - 1, _index);
		displayRoute();
	});


}


function addTableEntry(address, lat, lng, id){
	if(address === "")return;

	var _table = document.getElementById("point-table");
	var _after = document.getElementById("insert-id");
	var _row = _table.insertRow(id == null ? _table.rows.length : id);	
	updateInsertId();
	
	_row.insertCell(0).innerHTML = "<button><</button>";
	_row.insertCell(1).innerHTML = "<button>></button>";
	_row.insertCell(2).innerHTML = address;
	_row.insertCell(3).innerHTML = lat;
	_row.insertCell(4).innerHTML = lng;
	_row.insertCell(5).innerHTML = 0.0 + "km";
	_row.insertCell(6).innerHTML = "0m";
	_row.insertCell(7).innerHTML = "<button>Remove</button>";

	setRowListeners(_row);

	_row.draggable = true;

	_row.ondragover = function(event) {
    	event.preventDefault();
	};

	_row.ondrop = function(event) {
	    event.preventDefault();
	    var _sourceId = event.dataTransfer.getData("id");
	    var _destId = event.target.parentElement.rowIndex;
	    if(_sourceId != _destId && _sourceId != null && _destId != null){
	    	swapTableValues(_sourceId, _destId);
			swapLocalStorageValues(_sourceId - 1, _destId - 1);
			displayRoute();
	    }	    
	};
	
	_row.ondragstart = function(event) {
    	event.dataTransfer.setData("id", event.target.rowIndex);
	};

}

function convertTime(minutes){
	if(minutes == null)return null;

	var _hour = 60;
	var _day = _hour * 24;

	var _durationString = "";
	if(minutes > _day){
		var _days = Math.floor(minutes / _day);
		_durationString += _days + "d";
		minutes -= _days * _day;
	}if(minutes > _hour){
		var _hours = Math.floor(minutes / _hour);
		_durationString += _hours + "h";
		minutes -= _hours * _hour;
	}_durationString += minutes + "min";

	return _durationString;
}

function getTableEntry(id){
	var table = document.getElementById("point-table"); 
	if(table.rows.length < 2)return null;

	if(id < 0)id = table.rows.length - id;
	if(id < 0 || id >= table.rows.length)return null;

	var _row = table.rows[id];

	var _distanceHTML = _row.cells[5].innerHTML;
	var _distance = parseFloat(_distanceHTML.substring(0, _distanceHTML.length - 2));

	var _timeHTML = _row.cells[6].innerHTML;
	var _time = parseInt(_timeHTML.substring(0, _timeHTML.length - 3));

	return {
		address: _row.cells[2].innerHTML,
		lat: _row.cells[3].innerHTML,
		lng: _row.cells[4].innerHTML,
		distance: _distance,
		time: _time,
	}
}

function calcDistance(lat1, lng1, lat2, lng2){
	var _point1 = new google.maps.LatLng(lat1, lng1);
	var _point2 = new google.maps.LatLng(lat2, lng2);

	var _distanceInM = google.maps.geometry.spherical.
		computeDistanceBetween(_point1, _point2);

	return (_distanceInM / 1000.0).toFixed(1);
}

function updateDistanceAndTime(response){
	var _table = document.getElementById("point-table");
	var route = response.routes[0];

	var _totalDistance = 0.0;
	var _totalDuration = 0;
	for (var i = 0; i < route.legs.length; i++){
        var _km = (route.legs[i].distance.value / 1000.0).toFixed(1);
        _table.rows[i + 1].cells[5].innerHTML = _km + "km";

        var _min = Math.round(route.legs[i].duration.value / 60);
		_table.rows[i + 1].cells[6].innerHTML = convertTime(_min);

		_totalDistance += parseFloat(_km);
		_totalDuration += parseInt(_min);
    }

    document.getElementById("total-distance").innerHTML = 
		"Total Distance: " + _totalDistance.toFixed(1) + "km";

	document.getElementById("total-duration").innerHTML = 
		"Total Duration: " + convertTime(_totalDuration);
}

function addAddressPoint(address){
	geocoder.geocode({'address': address}, function(results, status) {
    	if (status === 'OK') {
    		map.setCenter(results[0].geometry.location);

            var _address = results[0].formatted_address;
    		var _lat = results[0].geometry.location.lat().toFixed(5);
    		var _lng = results[0].geometry.location.lng().toFixed(5);
 		
 			var _date = new Date();
 			var _insertId = parseInt(document.getElementById("insert-id").value) + 1;
    		localStorage.setItem(_date.getTime(), JSON.stringify([_address, _lat, _lng]));

    		//if not last then swap items
    		if(_insertId < localStorage.length){
    			swapLocalStorageValues(_insertId - 1, localStorage.length - 1);
    		}
 		
            addTableEntry(_address, _lat, _lng, _insertId);
            displayRoute();	

          } else {
            alert("Unable to find: " + "'" + address + "' (" + status + ")");
          }
        });
}

function displayRoute(){
	var table = document.getElementById("point-table"); 
	var rows = table.rows;

	if(rows.length < 3)return;

	//add points to stop at between start end finish
	var _waypoints = [];
	for(var i = 1; i < rows.length - 1; ++i){
		_waypoints.push({
			location: rows[i].cells[2].innerHTML,
			stopover: true
		});	
	}

	/*console.log("START: " + rows[1].cells[1].innerHTML + " FINISH: " + 
		rows[rows.length - 1].cells[1].innerHTML);*/

	//display route
	directionsService.route({
          origin: rows[1].cells[2].innerHTML,
          destination: rows[rows.length - 1].cells[2].innerHTML,
          waypoints: _waypoints,
          optimizeWaypoints: false,
          travelMode: google.maps.DirectionsTravelMode.DRIVING
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
            updateDistanceAndTime(response);
          } else {
            alert("Directions error: " + status);

            //remove last element if no route to it found (overseas)
            if(status === "ZERO_RESULTS"){
            	localStorage.removeItem(localStorage.key(rows.length - 2));
				document.getElementById("point-table").deleteRow(rows.length - 1);
				displayRoute();
				updateInsertId();
            }
          }
    });

}

function sortEntries(){
	var _table = document.getElementById("point-table");
	var _rows = _table.rows;

	if(_rows.length < 5)return;

	for(var i = 1; i < _rows.length - 2; ++i){
		var _lat1 = _rows[i].cells[3].innerHTML;
		var _lng1 = _rows[i].cells[4].innerHTML;
		var _distance = null;
		var _shortestIndex = i + 1;

		for(var j = i + 1; j < _rows.length - 1; ++j){	
			var _lat2 = _rows[j].cells[3].innerHTML;
			var _lng2 = _rows[j].cells[4].innerHTML;
			var _newDistance = calcDistance(_lat1, _lng1, _lat2, _lng2);

			//console.log(_newDistance);

			if(_distance == null || _newDistance < _distance){
				_shortestIndex = j;
				_distance = _newDistance;
			}
		}

		if(_shortestIndex != (i + 1)){
			swapLocalStorageValues(_shortestIndex - 1, i);
			swapTableValues(_shortestIndex, i + 1);
		}
	}

	//console.log(localStorage);
	displayRoute();
}

function handleAddressInput(){
	var _address = document.getElementById("address").value;

	if(_address === "")return;
	else document.getElementById("address").value = "";

	addAddressPoint(_address);
}

$(document).ready(() => {
	//localStorage.clear();

	var table = document.getElementById("point-table");
	var _keys = Object.keys(localStorage);

	for(var i = 0; i < localStorage.length; ++i){
		var data = localStorage.getItem(localStorage.key(i));
		data = JSON.parse(data);

		addTableEntry(data[0], data[1], data[2]);
	}

	 displayRoute();
});
