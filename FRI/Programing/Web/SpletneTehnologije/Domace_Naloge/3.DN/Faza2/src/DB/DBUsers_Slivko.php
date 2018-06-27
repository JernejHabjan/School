<?php

require_once "DBInit_Slivko.php";
require_once "DBChecker.php";


class DBUsers_Slivko
{

    public static $fullname = '';
    public static $username = '';


    public static function register()
    {

        echo "<h1>Registracija</h1>";

        $submit = "";
        $dateRegistered = "";

        $got_new = isset($_POST["submit"]) && !empty($_POST["submit"]) &&
            isset($_POST["fullname"]) && !empty($_POST["fullname"]) &&
            isset($_POST["username"]) && !empty($_POST["username"]) &&
            isset($_POST["email"]) && !empty($_POST["email"]) &&
            isset($_POST["password"]) && !empty($_POST["password"]) &&
            isset($_POST["repeatpassword"]) && !empty($_POST["repeatpassword"]);

        if ($got_new) {
            $submit = strip_tags($_POST['submit']);
            DBUsers_Slivko::$fullname = strip_tags($_POST["fullname"]);
            DBUsers_Slivko::$username = $_POST["username"];
            $email = strip_tags($_POST["email"]);
            $user_password = strip_tags($_POST["password"]);
            $repeatuser_password = strip_tags($_POST["repeatpassword"]);
            $dateRegistered = date("Y-m-d");
        }


        if ($submit) {//će prtisne submit button
            //open database for check if username exists
            $mysqli = DBInit_Slivko::getInstance();


            $username = DBUsers_Slivko::$username;
            $fullname = DBUsers_Slivko::$fullname;

            $name_mail_check = "SELECT username, email FROM users WHERE username = '$username' OR email = '$email'";
            $statement = $mysqli->prepare($name_mail_check);
            $statement->execute();
            $result = $statement->fetchAll();


            if (count($result) == 0) {//će še ni usernama ALI emaila not

                //check for existance
                if ($username && $user_password && $fullname && $repeatuser_password) { //tukej še ni encryptan pass ker če je field prazn sha256 encrypta u random shit
                    if ($user_password == $repeatuser_password) {
                        //check char lenth of username and fullname
                        if (strlen($username) > 25 || strlen($username) < 3 || strlen($fullname) > 25 || strlen($fullname) < 3) {
                            echo "Uporabniško ime mora vsebovati od 2 do 25 znakov.";
                        } else {
                            //checkpassword
                            if (strlen($user_password > 25) || strlen($user_password) < 6) {
                                echo "Geslo mora vsebovati od 6 do 25 znakov";

                            } else {
                                $pic_path = "default.png";

                                $salt = uniqid(mt_rand(), true);
                                //encrypt password
                                $user_password = DBUsers_Slivko::encryptPassword($user_password, $salt);

                                // Perform an SQL query
                                $statement = $mysqli->prepare("INSERT INTO users (user_id, username, email, user_password, fullname, dateRegistered, salt, pic_path) 
                                                          VALUES (:user_id, :username, :email, :user_password, :fullname, :dateRegistered, :salt, :pic_path)");

                                $user_id = NULL;
                                $statement->bindParam(":user_id", $user_id);
                                $statement->bindParam(":username", $username);
                                $statement->bindParam(":email", $email);
                                $statement->bindParam(":user_password", $user_password);
                                $statement->bindParam(":fullname", $fullname);
                                $statement->bindParam(":dateRegistered", $dateRegistered);
                                $statement->bindParam(":salt", $salt);
                                $statement->bindParam(":pic_path", $pic_path);
                                $statement->execute();

                                echo "you have been registerered <a href='../../index.php'>return to login page</a>";
                                $mysqli = null;
                                return 1;

                            }
                        }
                    } else {
                        echo "Gesla se ne ujemata";
                    }
                } else {
                    echo "Vnesite <b>vsa</b> polja";
                }
            } else {
                echo "Uporabnik s tem uporabniškim imenom ali elektronsko pošto že obstaja";
            }
        }
        $mysqli = null;
        return 0;
    }

    private static function encryptPassword($user_password, $salt)
    {
        for ($i = 0; $i < 10000; $i++) {
            $user_password = hash('sha256', $user_password . $salt);

        }
        return $user_password;
    }

    public static function login()
    {


        if (!isset($_POST["username"]) || empty($_POST["username"]) || !isset($_POST["password"]) || empty($_POST["password"])) {
            //if pass or username are incorrently sent
            echo "Vpisali ste napačen format uporabniškega imena ali gesla. Vsebovati morata po vsaj en znak";
            exit;
        }

        $username = $_POST['username'];
        $user_password = $_POST['password'];

        $mysqli = DBInit_Slivko::getInstance();


        // Perform an SQL query

        $usernamecheck = "SELECT * FROM users WHERE username = '$username'";
        $statement = $mysqli->prepare($usernamecheck);
        $statement->execute();
        $result_username = $statement->fetchAll();

        $email_check = "SELECT * FROM users WHERE email = '$username'";
        $statement = $mysqli->prepare($email_check);
        $statement->execute();
        $result_email = $statement->fetchAll();


        if (count($result_username) == 0 && count($result_email) == 0) {

            echo "Nismo našli ujemanja uporabniškega imena > $username <. Prosimo poskusite ponovno.";
            exit;
        }

        $result = (count($result_username) == 0 ? $result_email : $result_username); //rezultat je tisto kar uporabnik poda


        echo "<b><center>Database Output</center></b><br><br>";

        $dbuser_id = $result[0]['user_id'];
        $dbfullname = $result[0]['fullname'];
        $dbusername = $result[0]['username'];
        $dbuser_password = $result[0]['user_password'];
        $dbpic_path = $result[0]['pic_path'];
        $dbsalt = $result[0]['salt'];




        //HASH user_password
        $user_password = DBUsers_Slivko::encryptPassword($user_password, $dbsalt);


        if ($username = $dbusername && $user_password == $dbuser_password) {


            //posodobi še datum zadnje prijave
            $datumZadnjePrijave = date("Y-m-d");
            $update_zadnjePrijave = "UPDATE users SET datumZadnjePrijave = $datumZadnjePrijave WHERE user_id = $dbuser_id";
            $statement = $mysqli->prepare($update_zadnjePrijave);
            $statement->execute();


            $_SESSION['user_id'] = $dbuser_id;
            $_SESSION['username'] = $dbusername;
            $_SESSION['fullname'] = $dbfullname;
            $_SESSION['pic_path'] = ( $dbpic_path!="default.png" ? $dbpic_path : $dbpic_path);
            $_SESSION['loggedin'] = true;

            header('Location: ../../index.php');


        } else {
            echo "Napačno geslo";
        }
    }

    public static function getUserData($user_id = 0)
    {
        $mysqli = DBInit_Slivko::getInstance();



        $namecheck = "SELECT * FROM users";
        if($user_id > 0){
            $namecheck= $namecheck." WHERE user_id = '$user_id'";
        }

        $statement = $mysqli->prepare($namecheck);
        $statement->execute();
        return $statement->fetchAll();

    }

    public static function changePassword($olduser_password, $user_password, $repeatuser_password)
    {

        $user_id = $_SESSION['user_id'][0];
        $mysqli = DBInit_Slivko::getInstance();
        $passcheck = "SELECT user_password, salt FROM users WHERE user_id = $user_id";
        $statement = $mysqli->prepare($passcheck);
        $statement->execute();
        $a = $statement->fetchAll();

        $dbuser_password = $a[0]["user_password"];
        $dbsalt = $a[0]["salt"];


        //NASTAVT MORM NOV SALT PA NOV PASSWORD
        if (DBUsers_Slivko::encryptPassword($olduser_password, $dbsalt) == $dbuser_password) {
            if ($user_password == $repeatuser_password) {

                //checkpassword
                if (strlen($user_password > 25) || strlen($user_password) < 6) {
                    echo "password must be between 25 and 6 characters";

                } else {


                    $new_salt = uniqid(mt_rand(), true);
                    //encrypt password
                    $new_user_password = DBUsers_Slivko::encryptPassword($user_password, $new_salt);

                    $passcheck = "UPDATE users SET user_password = '$new_user_password', salt = '$new_salt' WHERE user_id = $user_id";
                    $statement = $mysqli->prepare($passcheck);
                    $statement->execute();

                    echo "Geslo uspešno zamenjano!";

                }

            } else {
                echo "novo geslo in ponovljeno novo geslo se ne ujemata";
            }
        } else {
            echo "Vaše geslo in geslo v bazi se ne ujemata";
        }
    }


    public static function changePic()
    {

        if (DBChecker::checkIfPhoto()) {
            $filename = $_FILES["image"]["name"];
            $file_tmp = $_FILES["image"]["tmp_name"];
            $filename = $_SESSION["user_id"] . $filename;

            $_SESSION["pic_path"] = $filename;
            $image_up = "../photos/userPhotos/" . $filename; //se user_id da se slike ne overrwitajo
            move_uploaded_file($file_tmp, $image_up);

            $user_id = $_SESSION['user_id'];

            $mysqli = DBInit_Slivko::getInstance();
            $passcheck = "UPDATE users SET pic_path = '$filename' WHERE user_id = $user_id";
            $statement = $mysqli->prepare($passcheck);
            $statement->execute();

            echo "Slika uspešno zamenjana!";


        }


    }

}

