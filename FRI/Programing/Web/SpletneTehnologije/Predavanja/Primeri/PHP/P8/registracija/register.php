<!DOCTYPE html>
<?php session_start(); ?>

<html>
	<head>
		<title>Register</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <script src="jquery-2.1.0.min.js"></script>
    <script src="script.js"></script>
	</head>

	<body>
		<?php
      $userExists = false;
      
			if (isset($_POST['register']))
      {
        // connect to db
        $dbConn = mysqli_connect("localhost");
        $dbConn->select_db("test");
        // čćšž
				mysqli_query($dbConn, "SET NAMES 'utf8' COLLATE 'utf8_slovenian_ci';");
				mysqli_query($dbConn, "SET CHARACTER SET 'utf8_slovenian_ci';");	
        
        $username = $_POST['username'];
        $sqlSelect = "SELECT * FROM user WHERE username = '$username'";
        $query = mysqli_query($dbConn, $sqlSelect);
        if (mysqli_num_rows($query) > 0)
          $userExists = true;
        else
        { 
          $email = $_POST['email'];
          $password = $_POST['password'];
          $password = hash('sha512', $password);
          
          $sqlInsert = "INSERT INTO user (username, email, password)
                        VALUES ('$username', '$email', '$password ');";

          // execute sqlInsert
          mysqli_query($dbConn, $sqlInsert);
          echo "Uporabnik $username uspešno registriran!";
        }
			}
      
			if (!isset($_POST['register']) || $userExists)
			{
				echo "<form id='registerForm' name='registerForm' action='register.php' method='POST'>";
				echo "<span class='label'>Uporabniško ime:</span>";
        echo "<input type='text' name='username' id='txtUsername'>";
        echo "<span class='validate hide' id='usernameValidate'>*</span>";
        if ($userExists)
          echo "<span class='validate' id='usernameExists'>Already taken!</span>";
				echo "</br>";
        echo "<span class='label'>E-mail:</span>";
        echo "<input type='text' name='email' id='txtEmail'>";
        echo "<span class='validate hide' id='emailValidate'>*</span>";
				echo "</br>";
        echo "<span class='label'>Geslo:</span>";
        echo "<input type='password' name='password' id='txtPassword'>";
        echo "<span class='validate hide' id='passwordValidate'>*</span>";
				echo "</br>";
				echo "<div id='btnHolder'>";
        echo "<input type='submit' value='Register' name='register' id='btnRegister'>";
        echo "</div>";
				echo "</form>";
			}
		?>
	</body>
</html>