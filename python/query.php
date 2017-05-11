<?php

function findIndexOfKey($key_to_index,$array){
  return array_search($key_to_index,array_keys($array));
}



usort($array, "cmp");

$city_name = isset($_GET["city"]) ? $_GET["city"] : "Asheville";
$allow_pets = isset($_GET["pets"]) ? $_GET["pets"] : true;
$require_heating = isset($_GET["heating"]) ? $_GET["heating"] : true;
$require_house = isset($_GET["house"]) ? $_GET["house"] : true;
$require_breakfast = isset($_GET["breakfast"]) ? $_GET["breakfast"] : true;
$require_family_friendly = isset($_GET["family"]) ? $_GET["family"] : true;

/*echo $allow_pets;
echo $require_heating;
echo $require_house;
echo $require_breakfast;
echo $require_large_room;*/


$path = "../src/City_Data_Attributes/".$city_name."/listings.csv";
$rows = file($path);

if($rows){
	$columns = array_shift($rows);
	
	$type_id = findIndexOfKey("property_type", $columns);
	$lat_id = findIndexOfKey("latitude", $columns);
	$lng_id = findIndexOfKey("longitude", $columns);
	$description_id = findIndexOfKey("description", $columns);
	$score_id = findIndexOfKey("SCORE", $columns);
	$amenities_id = findIndexOfKey("amenities", $columns);
	$picture_id = findIndexOfKey("picture_url", $columns);
	$thumbnail_id = findIndexOfKey("thumbnail_url", $columns);
	$score_over_time_id = findIndexOfKey("comments_scores_5", $columns);
	$score_change_id = findIndexOfKey("comments_scores_though_time", $columns);
	
	function cmp($a, $b){ //TODO convert score to float
		return strcmp($a[$score_id], $b[$score_id]);
	}
	
	foreach($rows as $row){
		//TODO change file to csvreader type
	}

}else{
	echo("Failed to open file '".$path."'");
}

?>















