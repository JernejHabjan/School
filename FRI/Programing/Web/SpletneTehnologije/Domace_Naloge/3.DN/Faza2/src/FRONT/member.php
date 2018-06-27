

<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">


<body style="background-color:#e6ffff;">
<div class="main">
    <?php

    session_start();

    require_once "../DB/DBUsers_Slivko.php";

    //getUserData

    if (@$_GET['id']) {

        $user_id = $_GET['id'];
        $userdata = DBUsers_Slivko::getUserData($user_id);
        $rows = count($userdata);
        if ($rows != 0) {


            $username = $userdata[0]["username"];
            $fullname = $userdata[0]['fullname'];
            $dateRegistered = $userdata[0]['dateRegistered'];
            $datumZadnjePrijave = $userdata[0]['datumZadnjePrijave'];
            $num_replies = $userdata[0]['num_replies'];
            $score = $userdata[0]['score'];
            $num_topics = $userdata[0]['num_topics'];
            $email = $userdata[0]['email'];
            $pic_path = $userdata[0]['pic_path'];
            $pic_path = ($pic_path == "" ? 'default.png' : $pic_path);

            echo "<title>$fullname</title>";
            if (isset($_SESSION["user_id"]) && !empty($_SESSION["user_id"]) && $user_id == $_SESSION['user_id']) {



                echo "<h4>Dobrodošli na svojem profilu, " . $fullname . "!</h4><br><br>";

            } else {
                echo "<h4>Dobrodošli na profilu od " . $fullname . "!</h4><br><br>";

            }

            ?>

            <script>arr.push("<?=$user_id . 'img'?>");</script>
            <center><img alt="User Pic" style="width:200px" src="../photos/userPhotos/<?= $pic_path ?>"
                         class="img-circle img-responsive"></center>
            <br>
        <?php

        if (isset($_SESSION["user_id"]) && !empty($_SESSION["user_id"]) && $user_id == $_SESSION['user_id']) :
        ?>


            <center>
                <form action="edit.php?action=changeUserData" method="POST">
                    <button style="width: 20%;" type="submit">Spremeni profil</button>
                </form>
            </center>

        <?php endif; ?>
            <br>
            <br>
            <table class="table table-user-information">
                <tbody>
                <tr>
                    <td style="width: 50%">Uporabniško ime:</td>
                    <td><?= $username ?></td>
                </tr>
                <tr>
                    <td>Datum registracije:</td>
                    <td><?= $dateRegistered ?></td>
                </tr>
                <tr>
                    <td>datum zadnje prijave:</td>
                    <td><?= $datumZadnjePrijave ?></td>
                </tr>
                <tr>
                    <td>Število odgovorov:</td>
                    <td><?= $num_replies ?></td>
                </tr>

                <tr>
                    <td>Točke:</td>
                    <td><?= $score ?></td>
                </tr>

                <tr>
                    <td>Število tem:</td>
                    <td><?= $num_topics ?></td>
                </tr>


                <tr>
                    <td>Email:</td>
                    <td><a href="mailto:<?= $email ?>"><?= $email ?></a></td>
                </tr>

                </tbody>
            </table>
            <br><br>

            <a href='../../index.php'> Domov </a>
            <br>

        <?php
        if (isset($_SESSION["user_id"]) && !empty($_SESSION["user_id"]) && $user_id == $_SESSION['user_id']) :

        ?>
            <a href='logout.php'>Odjava</a><br>

            <?php

        endif;

        } else {
            echo "Nismo našli IDja v bazi. Nemoremo nadaljevati.<br><a href='../../index.php'>Domov</a>\";";
        }

    } else {
        echo "Ni bilo prejetega IDja. Nemoremo nadaljevati.<br><a href='../../index.php'>Domov</a>";

    }
    ?>
</div>
</body>


<!-- MODAL PICTURE CONTAINER-->
<div id="myModal" class="modal">
    <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
    <img class="modal-content" id="img01" src="../photos/jernej.jpg" alt="Nothing">
    <div id="caption"></div>
</div>
<!-- END MODAL -->

<script>
    console.log(arr.length);
</script>

