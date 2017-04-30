
var markers = [];

function addAccomondationEntry(rating, address, sourceLat, sourceLng){
	var _table = document.getElementById("acc-table");
	var _row = _table.insertRow(_table.rows.length);	
	updateInsertId();

	_row.insertCell(0).innerHTML = rating;
	_row.insertCell(1).innerHTML = address;
	_row.insertCell(2).innerHTML = 0.0 + "km";
	_row.insertCell(3).innerHTML = "0m";
	_row.insertCell(4).innerHTML = "<button class='tabs'>Show</button>";

	_row.cells[4].addEventListener("click", function(){
		var _index = this.parentElement.rowIndex;
		setContent(null, 'description');

		document.getElementById("back_route").style.display = "none";
		document.getElementById("back_list").style.display = "inline";
	});
}

function clearList(){
	var _table = document.getElementById("acc-table");

	for(var i = _table.rows.length; i > 1; --i){
		_table.deleteRow(i - 1);
	}
}

function queryList(rowId){
	var _table = document.getElementById("point-table");
	var _row = _table.rows[rowId];
	var address = _row.cells[ADDRESS_ID].innerHTML;

	clearList();
	
	//console.log(_row.cells[ARRIVAL_ID].innerHTML);
	console.log(document.getElementById("arrival_" + rowId).value);


	geocoder.geocode({'address': address}, function(results, status) {
    	if (status === 'OK') {
    		map.setCenter(results[0].geometry.location);

            var _address = results[0].formatted_address;
    		var _lat = results[0].geometry.location.lat();
    		var _lng = results[0].geometry.location.lng();

    		//TODO get best 
    		//TODO use _lat/_lng

    		//placeholder
    		addAccomondationEntry(5.0, "Ljubljana", _lat, _lng);
    		addAccomondationEntry(1.2, "Kranj", _lat, _lng);
    		addAccomondationEntry(7.6, "Tržič", _lat, _lng);
    		addAccomondationEntry(6.66, "Zagreb", _lat, _lng);
    		addAccomondationEntry(5.8, "Celje", _lat, _lng);
 		
          } else {
            alert("Unable to find: " + "'" + address + "' (" + status + ")");
          }
    });

}