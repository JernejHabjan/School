<?php

require_once("model/UserDB_11.php");
require_once("ViewHelper_11.php");

class UserController_11 {

    public static function showLoginForm() {
       ViewHelper_11::render("view/user-login-form.php");
    }

    public static function login() {
       if (UserDB_11::validLoginAttempt($_POST["username"], $_POST["password"])) {
            $vars = [
                "username" => $_POST["username"],
                "password" => $_POST["password"]
            ];

            ViewHelper_11::render("view/user-secret-page.php", $vars);
       } else {
            ViewHelper_11::render("view/user-login-form.php",
                ["errorMessage" => "Invalid username or password."]);
       }
    }
}