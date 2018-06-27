
<title>Nova Tema</title>

<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<body style="background-color:#e6ffff;">
<div class="main">
<?php

require_once "../DB/DBTopics_Slivko.php";
session_start();

if (isset($_SESSION['loggedin']) && $_SESSION['loggedin'] === true) {
    ?>


    <form action="new_topic.php" method="POST" enctype="multipart/form-data">
        <center>
            <br>
            Ime teme: <br><input type="text" name="topic_name" style="width:400px;"><br>
            Vsebina: <br><textarea style="resize:none; width:400px; height:300px;" name="topic_content"></textarea><br>
            Izberi sliko: <input type="file" name="image" accept="image/*" value="izberi dodatno sliko"><br>
            <input type="submit" name="submit" value="Objavi" style="width:400px" ;>


        </center>


    </form>

    <?php

    if (isset($_SESSION["topic_id"]) && !empty($_SESSION["topic_id"]) && isset($_POST["submit"]) && !empty($_POST["submit"])) {

        DBTopics_Slivko::addTopic($_SESSION["topic_id"]);


    } else if (isset($_POST["submit"]) && !empty($_POST["submit"])) {

        DBTopics_Slivko::addTopic();

        header('Location: ../../index.php');
    }
} else {
    echo "Za ustvarjanje teme se morate prijaviti.";



}?>
<br><br><a href="../../index.php">Domov</a>

