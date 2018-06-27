<!DOCTYPE html>
<?php
require_once "src/DB/DBInit_Slivko.php";
require_once "src/DB/DBTopics_Slivko.php";
require_once "src/DB/DBUsers_Slivko.php";
require_once "src/DB/Render.php";

session_start();
unset($_SESSION["topic_id"]); //da restartamo za topicse --- TKO PREVERJAM A

?>
<script>
    arr = []; //za slike
</script>


<head>
    <title>Forum Slivko</title>


    <link rel="stylesheet" type="text/css" href="src/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="src/css/custom.css"/>
    <link rel="stylesheet" type="text/css" href="src/css/modalPictures.css"/>
    <link href="src/css/responsive.css" rel="stylesheet">
    <link href="src/css/memberPics.css" rel="stylesheet">
    <link href="src/css/style.css" rel="stylesheet">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

    <!-- Return to Top button-->
    <a href="javascript:" id="return-to-top"><i class="icon-chevron-up" style="left: 5%; top:12%"></i></a>
    <!-- ikona za gumb up -->
    <link href="http://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css" rel="stylesheet">
    <!--ARROW BUTTONS-->
    <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

</head>
<body>

<div id="everything">

    <div class="headercontainer">
        <div class="container"
             style="background-color: #f0fff0; ">

            <!--LOGO AND LOGIN-->
            <div class="header-logo">
                <div style="float:left;">
                    <a href="index.php" style="background:none"><img src="src/photos/logo.png" draggable="false"
                                                                     alt="Roglan"
                                                                     width="200px"/></a>
                </div>

                <!--SEARCH BAR-->
                <div style="float:right;">

                    <form id="frmSearch" class="search2" method="get" action="src/FRONT/search.php">
                        <div style="float:right">
                            <input class="search2" id="txtSearch" type="text" name="search"
                                   placeholder="Vpiši iskani niz..."
                                   maxlength="50"
                                   value=""/>
                            <input class="dropbtn" type="submit" name="submit" value="Išči"/>
                        </div>
                    </form>
                </div>


            </div>
            <!--END LOGO AND LOGIN-->
        </div>

        <div id="navbar_for_hide" class="navbar" style="width:100%; padding:0;border:0;">
            <div id="navBarID">
                <ul class="topnav" id="myTopnav">
                    <li style="float:left;"><a class="dropbtn active custom" href="src/FRONT/new_topic.php">+ Začni
                            temo</a></li>

                    <li style="float:left;">
                        <form action="index.php" method="GET">
                            <input class="dropbtn active custom " type="submit" name="submit_najnovejse"
                                   value="Najnovejše teme" style="display: inline-block;
                            color: #f2f2f2;
                            text-align: center;
                            padding: 14px 16px;
                            text-decoration: none;
                            transition: 0.3s;
                            font-size: 17px;
                            margin: 5px;
                            background-color: #333;">
                        </form>
                    </li>

                    <li style="float:left;">
                        <form action="index.php" method="GET">

                            <input class="dropbtn active custom" type="submit" name="submit_najbolj_popularne"
                                   value="Najbolj popularne" style="display: inline-block;
                            color: #f2f2f2;
                            text-align: center;
                            padding: 14px 16px;
                            text-decoration: none;
                            transition: 0.3s;
                            font-size: 17px;
                            margin: 5px;
                            background-color: #333;"


                            >
                        </form>
                    </li>


                    <li style="float:left;"><a class="dropbtn" onclick="mydropdownFunction('vec_dropdown')">Več
                            <span class="caret"></span></a>
                        <div class="dropdown-content" id="vec_dropdown"
                             style="left:0;margin-left:10%;margin-right:10%;width:80%;">
                            <div style="display:inline-flex;">

                                <ul id="navDrop">
                                    <li style="margin-bottom:20px"><a href="src/FRONT/gallery.php"><b>Galerija</b></a>
                                    </li>
                                    <li><a href="src/FRONT/avtor.html">Avtor</a></li>

                                </ul>

                            </div>
                        </div>
                    </li>


                    <?php if (isset($_SESSION['username'])): ?>
                        <li style="float:right;"><a class="dropbtn"
                                                    onclick="mydropdownFunction('profilQuickShow')"><?= $_SESSION["fullname"] ?>
                                <span class="caret"></span></a>
                            <div class="dropdown-content" id="profilQuickShow"
                                 style="left:0;margin-left:50%;margin-right:10%;width:40%;">
                                <div id="navDrop">
                                    <p>Pozdravljen <?= $_SESSION["fullname"] ?>!</p>

                                    <div style="display:inline-block">
                                        <figure>

                                            <a href='src/FRONT/member.php?id=<?= $user_id ?>'> <img
                                                        style="max-width: 100px"
                                                        src="src/photos/userPhotos/<?= $_SESSION["pic_path"] ?>"> </a>

                                        </figure>
                                    </div>
                                    <div style="display:inline-block; float: right">
                                        <span style="font-size:large; color: white"><?= $_SESSION["fullname"] ?></span><br>
                                        <span style="color:whitesmoke"><?= $_SESSION["username"] ?></span><br>
                                    </div>


                                    <a href="src/FRONT/member.php?id=<?= $_SESSION['user_id'] ?>"
                                       class="dropbtn"
                                       style="padding: 10px;background-color:gray">Obišči svoj profil</a>


                                    <form action="src/FRONT/edit.php?action=changeUserData" method="POST">
                                        <button class="dropbtn" style="padding: 10px;background-color:gray; width: 100%"
                                                type="submit">Nastavitve profila
                                        </button>
                                    </form>

                                    <a href="src/FRONT/logout.php" class="dropbtn"
                                       style="padding: 10px;background-color:grey">Odjava</a>
                                </div>
                            </div>
                        </li>


                    <?php else: ?>
                        <li style="float:right;"><a class="dropbtn" onclick="mydropdownFunction('loginDropdown')">Prijavite
                                se
                                <span class="caret"></span></a>
                            <div class="dropdown-content" id="loginDropdown"
                                 style="left:0;margin-left:50%;margin-right:10%;width:40%;">
                                <center>
                                    <form style="margin: 0; padding: 0;" onsubmit="return validateForm()"
                                          name="myForm"
                                          action="src/FRONT/login.php" method='POST'>
                                        Uporabniško ime:<input type="text" name="username"
                                                               placeholder="Vpišite uporabniško ime ali email"/><br>
                                        <mark class="geslo">Geslo:</mark>
                                        <input type="password" name="password" placeholder="Vpišite geslo"/><br>
                                        <input class="dropbtn" style="width:100%;" type="submit" value="Prijavite se">
                                    </form>
                                    <br>
                                    <button class="dropbtn" style="width:80%"
                                            onclick="location.href='src/FRONT/register.php'"
                                            href="register.php">Registrirajte se
                                    </button>
                                </center>
                            </div>
                        </li>


                    <?php endif; ?>


                    <li class="icon">
                        <a href="javascript:void(0);" style="font-size:15px;" onclick="myFunction()">☰</a>
                    </li>
                </ul>
            </div>
        </div>


        <div class="main" id="mainContent">


            <?php Render::display_Page(); ?>


            <br>
            <br>
            <h2 style="margin-bottom:0">Člani</h2>


        </div>

        <div class="selector " style="position:relative;">
            <button id="buttonleft" class="button5" style="left:0;"><i style="font-size:24px"
                                                                       class="fa">&#xf053;</i></button>
            <button id="buttonright" class="button5" style="right:0;"><i style="font-size:24px"
                                                                         class="fa">&#xf054;</i></button>

            <div id="leftRightMover">
                <div class="container" style="padding:0;margin-left:10%;margin-right:10%;">
                    <div id="divContainer">

                        <?php

                        $users = DBUsers_Slivko::getUserData();

                        foreach ($users as $user) {
                            $user_id = $user['user_id'];
                            $fullname = $user['fullname'];
                            $username = $user['username'];
                            $pic_path = $user['pic_path'];

                            echo "<div class='round'>
                                        <a href='src/FRONT/member.php?id=$user_id'>
                                        <img src='src/photos/userPhotos/$pic_path'/>
                                        <p>$fullname</p>
                                      
                                        </a>
                                    </div>";
                        }


                        ?>


                    </div>
                </div>
            </div>
        </div>


        <footer id="footer" class="col-12 col-m-12">

            <p>Jernej Habjan</p>
            <p>63150106</p>
            <p>Fakulteta za Računalništvo in Informatiko Ljubljana - Spletne Tehnologije 3. domača naloga</p>
        </footer>


    </div>
</div>

<!-- MODAL PICTURE CONTAINER-->
<div id="myModal" class="modal">
    <span class="close" onclick="document.getElementById('myModal').style.display='none'">&times;</span>
    <img class="modal-content" id="img01" src="src/photos/jernej.jpg" alt="Nothing">
    <div id="caption"></div>
</div>
<!-- END MODAL -->


<script src="src/scripts/javascript.js"></script>
<script src="src/scripts/modalPictures.js"></script>
<script src="src/scripts/members.js"></script>

</body>
