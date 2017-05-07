<?php

//TODO preferences from $_GET
//TODO python script use preferences from args
//TOOD pass arguments to script ??

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


$command = escapeshellcmd("04_query_entries.py ".
	$city_name." ".
	$allow_pets." ".
	$require_heating." ".
	$require_house." ".
	$require_breakfast." ".
	$require_family_friendly.""
);

$output = shell_exec($command);
echo $output;


#$myfile = fopen("../test_script.py", "r") or die("Unable to open file!");
#echo fread($myfile,filesize("../test_script.py"));
#fclose($myfile);

?>