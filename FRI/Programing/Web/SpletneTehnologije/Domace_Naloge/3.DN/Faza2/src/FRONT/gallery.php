<title>Galerija</title>

<script>
    arr = []; //za slike
</script>

<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<body style="background-color:#e6ffff;">
<div class="main">
    <center>


        <?php

        require_once "../DB/DBTopics_Slivko.php";
        require_once "../DB/DBUsers_Slivko.php";


        $result_topics = DBTopics_Slivko::getTopics(); //get also comments
        $result_users = DBUsers_Slivko::getUserData(); //get all users


        echo "<br><h2>Teme</h2><br><div>";

        foreach ($result_topics as $row) {

            $topic_id = $row['topic_id'];
            $topic_name = $row['topic_name'];
            $topic_date = $row['topic_date'];
            $content_text = $row['content_text'];
            $content_photo = $row['content_photo'];
            $is_comment = $row['is_comment'];

            $path = '../photos/topicPhotos/' . $topic_id . $content_photo;

            if (!$is_comment):
                ?>
                <div style="display: inline-block; border:2px solid black">

                    <div><a href='topic.php?id=<?= $topic_id ?>'><?= $topic_name ?></a></div>


                    <script>arr.push("<?=$topic_id . 'img'?>");</script>

                    <div><img id="<?= $topic_id . 'img' ?>" src=<?= $path ?> alt = '<?= $topic_name ?>' style
                        =
                        'max-width: 300px
                        ;
                            max-height: 300px
                        ;
                            border-radius: 5px
                        ;
                            cursor: pointer
                        ;
                            transition: 0.3s
                        ;'></div>


                </div>

                <?php
            endif;

        }

        echo "<br><hr><br><h2>Komentarji</h2><br><div>";

        foreach ($result_topics as $row) {

            $topic_id = $row['topic_id'];
            $topic_name = $row['topic_name'];
            $topic_date = $row['topic_date'];
            $content_text = $row['content_text'];
            $content_photo = $row['content_photo'];
            $is_comment = $row['is_comment'];

            $path = '../photos/topicPhotos/' . $topic_id . $content_photo;

            if ($is_comment):
                ?>
                <div style="display: inline-block; border:2px solid black">
                    <div><a href='topic.php?id=<?= $topic_id ?>'><?= $topic_name ?></a></div>


                    <script>arr.push("<?=$topic_id . 'img'?>");</script>

                    <div><img id="<?= $topic_id . 'img' ?>" src=<?= $path ?> alt = '<?= $topic_name ?>' style
                        =
                        'max-width: 300px
                        ;
                            max-height: 300px
                        ;
                            border-radius: 5px
                        ;
                            cursor: pointer
                        ;
                            transition: 0.3s
                        ;'></div>


                </div>

                <?php
            endif;

        }


        echo "</div><br><hr><br><h2>Uporabniki</h2><div>";
        foreach ($result_users as $row) {

            $user_id = $row['user_id'];
            $username = $row['username'];
            $topic_date = $row['fullname'];
            $pic_path = $row['pic_path'];


            $path = '../photos/userPhotos/' . $pic_path;

            ?>
            <div style="display:inline-block; border: 2px solid black;">

                <div><a href='member.php?id=<?= $user_id ?>'><?= $username ?></a></div>


                <script>arr.push("<?=$user_id . 'img'?>");</script>
                <div><img id="<?= $user_id . 'img' ?>" src=<?= $path ?> alt = '<?= $username ?>' style
                    =
                    'max-width: 300px
                    ;
                        max-height: 300px
                    ;
                        border-radius: 5px
                    ;
                        cursor: pointer
                    ;
                        transition: 0.3s
                    ;'></div>

            </div>

            <?php
        }
        ?>
</div>


</center>

</body>


<!-- MODAL PICTURE CONTAINER-->
<div id="myModal" class="modal">
    <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
    <img class="modal-content" id="img01" src="src/photos/jernej.jpg" alt="Nothing">
    <div id="caption"></div>
</div>
<!-- END MODAL -->


<script src="../scripts/modalPictures.js"></script>