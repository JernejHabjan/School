<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>arr = []</script>
<script src="../scripts/manage_comments.js"></script>


<body style="background-color:#e6ffff;">
<div class="main">
    <link href="../css/style.css" rel="stylesheet">


    <h1>Tema</h1>

    <?php
    require_once "../DB/DBTopics_Slivko.php";
    require_once "../DB/Render.php";
    session_start();

    if (isset($_GET["id"]) && !empty($_GET["id"])) {

        $results = DBTopics_Slivko::getTopics(0, "", $_GET["id"]);//storamo 1. rezultat saj je edini rezultat

        $topic_id = $results[0]['topic_id'];

        Render::displayTopic($results);

        ?>
        <br>
        <center>
            <form method="get" action="new_topic.php?action=comment&id='<?= $topic_id ?>'">
                <input class="custom" type="submit" name="add_comment" value="Dodaj komentar"/>
            </form>
        </center>

        <hr style="color:black">
        <h2>Komentarji</h2>

        <?php
        $comments = DBTopics_Slivko::getComment($topic_id);
        Render::displayTopic($comments);
    } else {
        echo "topic not found";
    }
    ?>
    <br><a href="../../index.php">Domov</a>

</body>


<!--- CONTAINER ZA RATING -->
<form name="EventConfirmRedirection" method="post" action="rating_page.php">
    <input type="hidden" value="" id="seanceId" name="seanceId">
</form>


<script>

    $('.vote').click(function (event) {
        let targetId = event.target.id.split(".")[0];


        if ($(this).hasClass('voteon')) {
            $(this).removeClass('voteon');

        } else {


            $(this).addClass('voteon');
            targetId = targetId.substring(0, targetId.length - "vote".length);
            const target = "#" + targetId + "downvote"; //tega zbrises

            $(target).removeClass('down_voteon');

            //update score
            post('rating_page.php', {name: targetId + "$vote$" + <?=$topic_id?>});
        }
    });
    $('.down_vote').click(function (event) {
        let targetId = event.target.id.split(".")[0];
        if ($(this).hasClass('down_voteon')) {
            $(this).removeClass('down_voteon');
        } else {

            $(this).addClass('down_voteon');
            targetId = targetId.substring(0, targetId.length - "down_vote".length + 1);
            const target = "#" + targetId + "vote"; //tega zbrises
            $(target).removeClass('voteon');

            //update score
            post('rating_page.php', {name: targetId + "$down_vote$" + <?=$topic_id?>});
        }

    });

</script>


