<?php

//TODO 
//$command = escapeshellcmd('../python/03_send_files.py');

$command = escapeshellcmd('test_script.py');
$output = shell_exec($command);
echo $output;

?>