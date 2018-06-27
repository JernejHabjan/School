<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">


<body style="background-color:#e6ffff;">
<div class="main">
    <br><br><center><h2>Rezultati:</h2></center><br><br>
    <?php

    require_once "../DB/DBInit_Slivko.php";
    require_once "../DB/Render.php";


    if (isset($_GET["submit"]) && !empty($_GET["submit"]) && isset($_GET["search"]) && !empty($_GET["search"])) {
        $w = $_GET["search"];

        //GET EVERYTHING:
        $mysqli = DBInit_Slivko::getInstance();


        $query_everything_like = "SELECT * FROM topics 
        JOIN topic_content ON(topics.topic_content_id = topic_content.topic_content_id) 
        JOIN users ON(users.user_id = topics.user_id) 
        WHERE topic_id like '%$w%' OR topic_name like '%$w%' OR topic_date like '%$w%' OR content_text like '%$w%' 
        OR users.user_id like '%$w%' OR username like '%$w%' OR fullname like '%$w%' OR email like '%$w%' ";

        $statement = $mysqli->prepare($query_everything_like);
        $statement->execute();
        $results = $statement->fetchAll();

        Render::displayTopic($results, false, false);

    }else{
        echo "Nismo dobili nobenega rezultata.";
    }
    ?>
    <br><hr><br>

    <a href='../../index.php'> Domov </a>
</div>
</body>