<?php
/**
 * Created by PhpStorm.
 * User: Jernej Habjan
 * Date: 25. 04. 2017
 * Time: 13:03
 */
require_once "../DB/DBTopics_Slivko.php";

$TEMPTARGETID = $_POST["name"];

echo $TEMPTARGETID;

$arr =  explode ("$", $TEMPTARGETID);



if($arr[1] == "vote") { //handle upvote
    DBTopics_Slivko::changeScore($TEMPTARGETID, true);
}
else{
    DBTopics_Slivko::changeScore($TEMPTARGETID, false);
}
header('Location: topic.php?id='.$arr[2]);
