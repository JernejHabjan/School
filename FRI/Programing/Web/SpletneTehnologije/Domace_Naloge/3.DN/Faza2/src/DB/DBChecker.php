<?php
require_once "DBInit_Slivko.php";

class DBChecker
{


    public static function checkIfPhoto()
    {
        $errors = array();
        $allowed_end = array("png", "jpg", "jpeg");

        $filename = $_FILES["image"]["name"];
        $file_end = strtolower(pathinfo($filename, PATHINFO_EXTENSION));
        $file_size = $_FILES["image"]["size"];


        if (in_array($file_end, $allowed_end) === false) {
            $errors[] = "this file extension is not allowed";
        }
        if ($file_size > 5000000) {
            $errors[] = "File must be under 5Mb";
        }
        if (empty($errors)) {


            return true;
        } else {
            foreach ($errors as $error) {
                echo $error, "<br>";
            }
            return false;
        }
    }




}