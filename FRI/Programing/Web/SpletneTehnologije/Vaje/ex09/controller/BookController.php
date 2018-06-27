<?php

require_once("model/BookDB.php");
require_once("ViewHelper.php");

class BookController {

    public static function index() {
        if (isset($_GET["id"])) {
            ViewHelper8::render("view/book-detail.php", ["book" => BookDB11::get($_GET["id"])]);
        } else {
            ViewHelper8::render("view/book-list.php", ["books" => BookDB11::getAll()]);
        }
    }

    public static function search() {
        if (isset($_GET["query"])) {
            $query = $_GET["query"];
            $hits = BookDB11::search($query);
        } else {
            $query = "";
            $hits = [];
        }

        ViewHelper8::render("view/book-search.php", ["hits" => $hits, "query" => $query]);
    }

    public static function showAddForm($values = ["author" => "", "title" => "", 
        "price" => "", "year" => ""]) {
        ViewHelper8::render("view/book-add.php", $values);
    }

    public static function add() {
        $validData = isset($_POST["author"]) && !empty($_POST["author"]) && 
                isset($_POST["title"]) && !empty($_POST["title"]) &&
                isset($_POST["year"]) && !empty($_POST["year"]) &&
                isset($_POST["price"]) && !empty($_POST["price"]);

        if ($validData) {
            BookDB11::insert($_POST["author"], $_POST["title"], $_POST["price"], $_POST["year"]);
            ViewHelper8::redirect(BASE_URL . "book");
        } else {
            self::showAddForm($_POST);
        }
    }

    public static function showEditForm($book = []) {
        if (empty($book)) {
            $book = BookDB11::get($_GET["id"]);
        }
        
        ViewHelper8::render("view/book-edit.php", ["book" => $book]);
    }    

    public static function edit() {
        $validData = isset($_POST["author"]) && !empty($_POST["author"]) && 
            isset($_POST["title"]) && !empty($_POST["title"]) &&
            isset($_POST["price"]) && !empty($_POST["price"]) &&
            isset($_POST["year"]) && !empty($_POST["year"]) &&
            isset($_POST["id"]) && !empty($_POST["id"]);

        if ($validData) {
            BookDB11::update($_POST["id"], $_POST["author"], $_POST["title"], $_POST["price"], $_POST["year"]);
            ViewHelper8::redirect(BASE_URL . "book?id=" . $_POST["id"]);
        } else {
            self::showEditForm($_POST);
        }
    }

    public static function delete() {
        $validDelete = isset($_POST["delete_confirmation"]) && isset($_POST["id"]) && !empty($_POST["id"]);

        if ($validDelete) {
            BookDB11::delete($_POST["id"]);
            $url = BASE_URL . "book";
        } else {
            if (isset($_POST["id"])) {
                $url =  BASE_URL . "book/edit?id=" . $_POST["id"];
            } else {
                $url =  BASE_URL . "book";
            }
        }

        ViewHelper8::redirect($url);
    }

    public static function searchAjax() {
        if (isset($_GET["query"]))  {
            if (!empty($_GET["query"])) {
                ViewHelper8::render("view/book-search-ajax-results.php",
                    ["hits" => BookDB11::search($_GET["query"])]);
            }
        } else {
            ViewHelper8::render("view/book-search-ajax.php");
        }
    }
}