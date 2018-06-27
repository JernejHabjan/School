<?php
/**
 * Created by PhpStorm.
 * User: Jernej Habjan
 * Date: 4. 04. 2017
 * Time: 08:55
 */
echo "HELLO WORLD!\n";

///////
$a = 10;
$b = 3;

$c = $a + $b;
echo ($c."\n");

/////PRIMER BUGGA

$a = "10gsfs";
$b = 3;

$c = $a + $b;
echo ($c." sešteva inte kokr časa gre\n");

//////

$age = 20;
echo ("The age is $age");//interpolira nize
echo ('The age is $age');//faster ampak ne interpolira


///////

$todos = [
  "ena", "dva"
];

$todos[] = "debug"; //push
//ali
array_push($todos, "tri"); //push

var_dump($todos); //dobimo izpis
for ($i = 0; $i < count($todos); $i++){
    echo ($todos[$i] . "\n");
}

/////////
$capitals = [
    "slovenija" =>"Ljubljana",
    "Korea" =>"Neki"
];

//add
$capitals["Germany"] = "Berlin";

foreach ($capitals as $states => $city){ //ce slovar -> foreach
    echo "The capital of $states is $city \n";
}

/////////delete
unset($capitals["Korea"]);

