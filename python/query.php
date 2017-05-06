<?php

//TODO preferences from $_GET
//TODO python script use preferences from args
//TOOD pass arguments to script ??

$city_name = isset($_GET["city"]) ? $_GET["city"] : "Asheville";
$allow_pets = isset($_GET["pets"]) ? $_GET["pets"] : true;
$require_shower = isset($_GET["shower"]) ? $_GET["shower"] : true;
$allow_camping = isset($_GET["camping"]) ? $_GET["camping"] : true;
$require_breakfast = isset($_GET["breakfast"]) ? $_GET["breakfast"] : true;
$require_large_room = isset($_GET["large_room"]) ? $_GET["large_room"] : true;

/*echo $allow_pets;
echo $require_shower;
echo $allow_camping;
echo $require_breakfast;
echo $require_large_room;*/


$command = escapeshellcmd("04_query_entries.py ".
	$city_name." ".
	$allow_pets." ".
	$require_shower." ".
	$allow_camping." ".
	$require_breakfast." ".
	$require_large_room.""
);

$output = shell_exec($command);
echo $output;


#$myfile = fopen("../test_script.py", "r") or die("Unable to open file!");
#echo fread($myfile,filesize("../test_script.py"));
#fclose($myfile);

?>