<?php
// function to connect to database
function doDB() {
	global $mysqli;

	//connect to server and select database
	$mysqli = mysqli_connect("localhost", "root", "", "testDB");

	//if connection fails, stop script execution
	if (mysqli_connect_errno()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
	} else {
		$sql="SELECT * FROM testTable";
		$res = mysqli_query($mysqli, $sql);
		if ($res === TRUE) {
			echo "Table subscribers exists.";	
		} else {
			$sql = "CREATE TABLE subscribers 
	       (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
		    email VARCHAR (150) UNIQUE NOT NULL)";
			#printf("Table created-?: %s\n", mysqli_error($mysqli));
		}				
	}
}
// function to check e-mail address
function emailChecker($email) {
	global $mysqli, $safe_email, $check_res;

	//check that email is not already in list
	$safe_email = mysqli_real_escape_string($mysqli, $email);
	$check_sql = "SELECT id FROM subscribers WHERE email = '".$safe_email."'";
	$check_res = mysqli_query($mysqli, $check_sql) or die(mysqli_error($mysqli));
}
?>