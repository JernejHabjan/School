<?php

function findIndexOfKey($key_to_index,$array){
  return array_search($key_to_index, $array);
}

$city_name = isset($_GET["city"]) ? $_GET["city"] : "Asheville";
$allow_pets = isset($_GET["pets"]) ? ($_GET["pets"] == "true" ? true : false) : true;
$require_heating = isset($_GET["heating"]) ? ($_GET["heating"] == "true" ? true : false) : false;
$require_house = isset($_GET["house"]) ? ($_GET["house"] == "true" ? true : false) : false;
$require_breakfast = isset($_GET["breakfast"]) ? ($_GET["breakfast"] == "true" ? true : false) : false;
$require_family_friendly = isset($_GET["family"]) ? ($_GET["family"] == "true" ? true : false) : false;
$require_cable_tv = isset($_GET["cableTV"]) ? ($_GET["cableTV"] == "true" ? true : false) : false;
$require_wireless = isset($_GET["wireless"]) ? ($_GET["wireless"] == "true" ? true : false) : false;
$require_air_conditioning = isset($_GET["airConditioning"]) ? ($_GET["airConditioning"] == "true" ? true : false) : false;
$require_free_parking = isset($_GET["freeParking"]) ? ($_GET["freeParking"] == "true" ? true : false) : false;
$require_kitchen = isset($_GET["kitchen"]) ? ($_GET["kitchen"] == "true" ? true : false) : false;
$min_price = isset($_GET["minPrice"]) ? intval($_GET["minPrice"]) : 0;
$max_price = isset($_GET["maxPrice"]) ? intval($_GET["maxPrice"]) : 1000;

$top_10 = isset($_GET["top"]) ? ($_GET["top"] == "true" ? true : false) : false;


/*echo($require_cable_tv." ".$require_wireless." ". $require_air_conditioning." ".
	 $require_free_parking." ".$require_kitchen." ".$min_price." ".$max_price);*/

//echo $allow_pets;
//echo $require_heating;
//echo $require_house;
//echo $require_breakfast;
//echo $require_family_friendly;


$path = "../src/City_Data_Attributes/".$city_name."/listings.csv";
$rows = file($path);

if($rows){
	$columns = str_getcsv(array_shift($rows));
	//print_r($columns);

	$score_id = findIndexOfKey("SCORE", $columns);
	$price_id = findIndexOfKey("price", $columns);
	$amenities_id = findIndexOfKey("amenities", $columns);
	$type_id = findIndexOfKey("property_type", $columns); 
	//$description_id = findIndexOfKey("description", $columns); 
	$used_columns = array
	(
		"latitude", 
		"longitude", 
		"description", 
		"picture_url", 
		"thumbnail_url", 
		"comments_scores_5", 
		"comments_scores_though_time"
	);

	$used_columns_ids = array();
	foreach($used_columns as $column_name){
		array_push($used_columns_ids, findIndexOfKey($column_name, $columns));
	}


	//convert to csv array
	$CSV_array = array();
	foreach($rows as $row){
		array_push($CSV_array, str_getcsv($row));
	}

	//SORT
	function cmp($a, $b){
		global $score_id;
		$scoreA = floatval($a[$score_id]);
		$scoreB = floatval($b[$score_id]);

		$result = 0;
        if ($scoreA < $scoreB) $result = 1;
        else if ($scoreA > $scoreB) $result = -1;

		return $result;
	}

	usort($CSV_array, "cmp");
		
	$counter = 0;
	foreach($CSV_array as $ra){
		$amenities = strtoupper($ra[$amenities_id]);

		$has_pets = strpos($amenities, "PETS LIVE") !== false || strpos($amenities, "PETS ALLOWED") !== false;
		$has_breakfast = strpos($amenities, "BREAKFAST") !== false;
		$has_heating = strpos($amenities, "HEATING") !== false;
		$is_family_friendly = strpos($amenities, "FAMILY") !== false;
		$has_cable_tv = strpos($amenities, "CABLE TV") !== false;
		$has_wireless = strpos($amenities, "WIRELESS INTERNET") !== false;
		$has_air_conditioning = strpos($amenities, "AIR CONDITIONING") !== false;
		$has_free_parking = strpos($amenities, "FREE PARKING") !== false;
		$has_kitchen = strpos($amenities, "KITCHEN") !== false;

		$valid_pets = $allow_pets || (!$has_pets);
		$valid_heating = (!$require_heating) || $has_heating;			
		$valid_house = (!$require_house) || strtoupper($ra[$type_id]) == "HOUSE";
		$valid_breakfast = (!$require_breakfast) || $has_breakfast;
		$valid_family = (!$require_family_friendly) || $is_family_friendly;
		$valid_cable_tv = (!$require_cable_tv) || $has_cable_tv;
		$valid_wireless = (!$require_wireless) || $has_wireless;
		$valid_air_conditioning = (!$require_air_conditioning) || $has_air_conditioning;
		$valid_free_parking = (!$require_free_parking) || $has_free_parking;
		$valid_kitchen = (!$require_kitchen) || $has_kitchen;

		$valid = $valid_pets && $valid_heating && $valid_breakfast && $valid_house && $valid_family &&
			$valid_cable_tv && $valid_wireless && $valid_air_conditioning && $valid_free_parking &&
			$valid_kitchen;
		$score = round(floatval($ra[$score_id]), 1);

		$price = round(floatval($ra[$price_id]), 2);
		$validPrice = $price >= $min_price && $price <= $max_price;


		if($valid && $validPrice && $score != 0.0){
			//echo($counter."\n");
			$counter += 1;
			if($top_10 && $counter > 10)break;

			$output = array($score, $price, $ra[$type_id]);
			foreach($used_columns_ids as $column_id){
				array_push($output, $ra[$column_id]);
			}

			echo(implode("$$", $output)."\n");
		}

		//$description = row[description_id]
		//$description = (description[:47] + "...") if len(description) > 50 else description
	}

}else{
	//echo("Failed to open file '".$path."'");
}

//echo("test");

?>















