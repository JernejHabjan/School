<title>Prijava</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<?php
require_once "../DB/DBUsers_Slivko.php";
session_start();
?>


<meta http-equiv="refresh" content="5;URL=../../index.php"/>


<body style="background-color:#e6ffff;">

<?php

DBUsers_Slivko::login();

?>

</body>