<?php

require_once "DBInit_11.php";

class UserDB {

    // Returns true if a valid combination of a username and a password are provided.
    public static function validLoginAttempt($username, $password) {
        $dbh = DBInit_11::getInstance();

        $query = "SELECT COUNT(id) FROM user WHERE username = :username AND password = :password";
        $stmt = $dbh->prepare($query);
        $stmt->bindValue(":username", $username);
        $stmt->bindValue(":password", $password);
        $stmt->execute();

        return $stmt->fetchColumn(0) == 1;
    }
}
