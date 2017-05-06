<?php

//TODO preferences from $_GET
//TODO python script use preferences from args
//TOOD pass arguments to script ??
$command = escapeshellcmd('04_query_entries.py');
$output = shell_exec($command);
echo $output;


#$myfile = fopen("../test_script.py", "r") or die("Unable to open file!");
#echo fread($myfile,filesize("../test_script.py"));
#fclose($myfile);

?>