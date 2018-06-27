<?php

require_once("model/BookDB_8.php");
require_once("ViewHelper_8.php");

class BookController_8 {

    public static function getAll() {
        ViewHelper_8::render("view/book-list.php", ["books" => BookDB_8::getAll()]);
    }

    public static function get() {
        ViewHelper_8::render("view/book-detail.php", ["book" => BookDB_8::get($_GET["id"])]);
    }

    public static function search() {

        if (isset($_GET["query"])) {
            $variables = [
                "query" => $_GET["query"],
                "hits" => BookDB_8::search($_GET["query"])
            ];

        } else {
            $variables = [
                "query" => "",
                "hits" => ""
            ];

        }

        ViewHelper_8::render("view/book-search.php", $variables);
    }

    public static function showAddForm($values = ["author" => "", "title" => "", 
        "price" => "", "year" => ""]) {
        ViewHelper_8::render("view/book-add.php", $values);
    }

    public static function add() {
        $validData = isset($_POST["author"]) && !empty($_POST["author"]) && 
                isset($_POST["title"]) && !empty($_POST["title"]) &&
                isset($_POST["year"]) && !empty($_POST["year"]) &&
                isset($_POST["price"]) && !empty($_POST["price"]);

        if ($validData) {
            BookDB_8::insert($_POST["author"], $_POST["title"], $_POST["price"], $_POST["year"]);
            ViewHelper_8::redirect(BASE_URL . "book");
        } else {
            self::showAddForm($_POST); //.. še enkrat pošljemo s tem kar je vpisu uproabnik
        }
    }

    public static function showEditForm($book = []) {
        if (empty($book)) {
            $book = BookDB_8::get($_GET["id"]);
        }

        ViewHelper_8::render("view/book-edit.php", ["book" => $book]);
    }    

    public static function edit() {
        $validData = isset($_POST["author"]) && !empty($_POST["author"]) && 
            isset($_POST["title"]) && !empty($_POST["title"]) &&
            isset($_POST["price"]) && !empty($_POST["price"]) &&
            isset($_POST["year"]) && !empty($_POST["year"]) &&
            isset($_POST["id"]) && !empty($_POST["id"]);

        if ($validData) {
            BookDB_8::update($_POST["id"], $_POST["author"], $_POST["title"], $_POST["price"], $_POST["year"]);
            ViewHelper_8::redirect(BASE_URL . "book?id=" . $_POST["id"]);
        } else {
            self::showEditForm($_POST);
        }
    }

    public static function delete() {
        $validDelete = isset($_POST["delete_confirmation"]) && isset($_POST["id"]) && !empty($_POST["id"]);

        if ($validDelete) {
            BookDB_8::delete($_POST["id"]);
            $url = BASE_URL . "book";
        } else {
            if (isset($_POST["id"])) {
                $url =  BASE_URL . "book/edit?id=" . $_POST["id"];
            } else {
                $url =  BASE_URL . "book";
            }
        }

        ViewHelper_8::redirect($url);
    }
}