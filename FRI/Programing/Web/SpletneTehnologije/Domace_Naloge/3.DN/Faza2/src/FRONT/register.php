<title>Registracija</title>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../css/custom.css"/>
<link rel="stylesheet" type="text/css" href="../css/modalPictures.css"/>
<link href="../css/responsive.css" rel="stylesheet">
<link href="../css/memberPics.css" rel="stylesheet">
<link href="../css/style.css" rel="stylesheet">
<body style="background-color:#e6ffff;">
<div class="main">

<?php
require_once "../DB/DBUsers_Slivko.php";
session_start();

$registered = DBUsers_Slivko::register();

?>

<?php if (!$registered): ?>
    <br><br>
    <center>
        <form action='register.php' method="POST">
            <table>
                <tr>
                    <td>
                        Vaše polno ime:
                    </td>
                    <td>
                        <input type="text" name="fullname" value='<?php echo DBUsers_Slivko::$fullname ?>'>
                    </td>
                </tr>
                <tr>
                    <td>
                        Uporabniško ime:
                    </td>
                    <td>
                        <input type="text" name="username" value='<?php echo DBUsers_Slivko::$username ?>'>
                    </td>
                </tr>
                <tr>
                    <td>
                        Email:
                    </td>
                    <td>
                        <input type="email" name="email">
                    </td>
                </tr>
                <tr>
                    <td>
                        Geslo:
                    </td>
                    <td>
                        <input type="password" name="password">
                    </td>
                </tr>
                <tr>
                    <td>
                        Ponovite geslo:
                    </td>
                    <td>
                        <input type="password" name="repeatpassword">
                    </td>
                </tr>

            </table>
            <br><br>
            <input type="submit" name="submit" value="Registrirajte" class="custom">
        </form>
    </center>
<?php endif; ?>
<br><a href="../../index.php">Domov</a>