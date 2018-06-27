<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">

<body style="background-color:#e6ffff;">
<div class="main">

<title>Spremeni profil</title>

<?php
session_start();
require_once "../DB/DBUsers_Slivko.php";

if (@$_GET['action'] == "changeUserData") {
    ?>
    <center><br><br>
        <h3>Spremeni geslo:</h3><br>
        <form action='edit.php?action="changeUserData"' method="POST">
            <table align="center">
                <tr>
                    <td>
                        Vpišite vaše staro geslo:
                    </td>
                    <td>
                        <input type="password" name="oldpassword">
                    </td>
                </tr>
                <tr>
                    <td>
                        Vpišite novo geslo:
                    </td>
                    <td>
                        <input type="password" name="user_password">
                    </td>
                </tr>
                <tr>
                    <td>
                        Ponovite novo geslo:
                    </td>
                    <td>
                        <input type="password" name="repeatpassword">
                    </td>
                </tr>
            </table><br>
            <input type="submit" name="change_pass" value="Zamenjajte geslo">
        </form>
    </center>
    <br>
    <hr>
    <br>
    <center>
        <h3>Spremeni sliko:</h3><br>
        <form action="edit.php?action=changePicture" method="POST" enctype="multipart/form-data">
            <input type="file" name="image" accept="image/*"><br>
            <input type="submit" name="change_pic" value="Naloži sliko">
        </form>
    </center>
    <!-- OTHER FORMS FOR USERNAME.............  OR THE SAME FORM AND SAVE AT THE END .. pol prevešr pass-->

    <?php


}

if (isset($_POST["change_pass"]))
    DBUsers_Slivko::changePassword($_POST["oldpassword"], $_POST["user_password"], $_POST["repeatpassword"]);

if (isset($_POST["change_pic"]))
    DBUsers_Slivko::changePic();


?>
<hr>
<a href="member.php?id=<?= $_SESSION['user_id']; ?>">Vrni se na svoj profil</a>