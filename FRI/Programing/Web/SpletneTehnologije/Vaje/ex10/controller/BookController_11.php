<?php

require_once("model/BookDB_11.phpp");
require_once("ViewHelper_11.php");

class BookController_11 {

    public static function index() {
        if (isset($_GET["id"])) {
            ViewHelper_11::render("view/book-detail.php", ["book" => BookDB_11::get($_GET["id"])]);
        } else {
            ViewHelper_11::render("view/book-list.php", ["books" => BookDB_11::getAll()]);
        }
    }

    public static function search() {
        if (isset($_GET["query"])) {
            $query = $_GET["query"];
            $hits = BookDB_11::search($query);
        } else {
            $query = "";
            $hits = [];
        }

        ViewHelper_11::render("view/book-search.php", ["hits" => $hits, "query" => $query]);
    }

    // Function can be called without providing arguments. In such case,
    // $data and $errors paramateres are initialized as empty arrays
    public static function showAddForm($data = [], $errors = []) {
        // If $data is an empty array, let's set some default values
        if (empty($data)) {
            $data = [
                "author" => "",
                "title" => "",
                "description" => "",
                "price" => 0,
                "year" => date("Y"),
                "quantity" => 10
            ];
        }

        // If $errors array is empty, let's make it contain the same keys as
        // $data array, but with empty values
        if (empty($errors)) {
            foreach ($data as $key => $value) {
                $errors[$key] = "";
            }
        }

        $vars = ["book" => $data, "errors" => $errors];
        ViewHelper_11::render("view/book-add.php", $vars);
    }

    public static function add() {
        $rules = [
            "author" => [
                "filter" => FILTER_VALIDATE_REGEXP,
                "options" => ["regexp" => "/^[ a-zA-ZšđčćžŠĐČĆŽ\.\-]+$/"]
            ],
            "title" => FILTER_SANITIZE_SPECIAL_CHARS,
            "description" => FILTER_SANITIZE_SPECIAL_CHARS,
            "year" => [
                "filter" => FILTER_VALIDATE_INT,
                "options" => ["min_range" => 1500, "max_range" => 2020]
            ],
            "price" => [
                "filter" => FILTER_CALLBACK,
                "options" => function ($value) { return (is_numeric($value) && $value >= 0) ? floatval($value) : false; }
            ],
            "quantity" => [
                "filter" => FILTER_VALIDATE_INT,
                "options" => ["min_range" => 10]
            ]
        ];
        $data = filter_input_array(INPUT_POST, $rules);

        $errors["author"] = $data["author"] === false ? "Provide the name of the author." : "";
        $errors["title"] = empty($data["title"]) ? "Provide the book title." : "";
        $errors["year"] = $data["year"] === false ? "Year should be between 1500 and 2020." : "";
        $errors["price"] = $data["price"] === false ? "Price should be non-negative." : "";
        $errors["quantity"] = $data["quantity"] === false ? "Quantity should be at least 10." : "";

        // Is there an error?
        $isDataValid = true;
        foreach ($errors as $error) {
            $isDataValid = $isDataValid && empty($error);
        }

        if ($isDataValid) {
            BookDB11::insert($data["author"], $data["title"], $data["description"],
                $data["price"], $data["year"], $data["quantity"]);
            ViewHelper_11::redirect(BASE_URL . "book");
        } else {
            self::showAddForm($data, $errors);
        }
    }

    public static function showEditForm($data = [], $errors = []) {
        if (empty($data)) {
            $data = BookDB_11::get($_GET["id"]);
        }

        if (empty($errors)) {
            foreach ($data as $key => $value) {
                $errors[$key] = "";
            }
        }
        
        ViewHelper_11::render("view/book-edit.php", ["book" => $data, "errors" => $errors]);
    }    

    public static function edit() {
        $rules = [
            "author" => [
                "filter" => FILTER_VALIDATE_REGEXP,
                "options" => ["regexp" => "/^[ a-zA-ZšđčćžŠĐČĆŽ\.\-]+$/"]
            ],
            "title" => FILTER_SANITIZE_SPECIAL_CHARS,
            "description" => FILTER_SANITIZE_SPECIAL_CHARS,
            "year" => [
                "filter" => FILTER_VALIDATE_INT,
                "options" => ["min_range" => 1500, "max_range" => 2020]
            ],
            "price" => [
                "filter" => FILTER_CALLBACK,
                "options" => function ($value) { 
                    return (is_numeric($value) && $value >= 0) ? floatval($value) : false; 
                }
            ],
            "quantity" => [
                "filter" => FILTER_VALIDATE_INT,
                "options" => ["min_range" => 0]
            ],
            "id" => [
                "filter" => FILTER_VALIDATE_INT,
                "options" => ["min_range" => 1]
            ]
        ];
        $data = filter_input_array(INPUT_POST, $rules);

        $errors["author"] = $data["author"] === false ? "Provide the name of the author." : "";
        $errors["title"] = empty($data["title"]) ? "Provide the book title." : "";
        $errors["year"] = $data["year"] === false ? "Year should be between 1500 and 2020." : "";
        $errors["price"] = $data["price"] === false ? "Price should be non-negative." : "";
        $errors["quantity"] = $data["quantity"] === false ? "Quantity should be non-negative." : "";

        $isDataValid = true;
        foreach ($errors as $error) {
            $isDataValid = $isDataValid && empty($error);
        }

        if ($isDataValid) {
            BookDB_11::update($data["id"], $data["author"], $data["title"], $data["description"],
                $data["price"], $data["year"], $data["quantity"]);
            ViewHelper_11::redirect(BASE_URL . "book?id=" . $data["id"]);
        } else {
            self::showEditForm($data, $errors);
        }
    }

    public static function delete() {
        $rules = [
            "id" => [
                "filter" => FILTER_VALIDATE_INT,
                "options" => ["min_range" => 1]
            ],
            "delete_confirmation" => [
                "filter" => FILTER_VALIDATE_BOOLEAN
            ]
        ];
        $data = filter_input_array(INPUT_POST, $rules);

        $errors["id"] = $data["id"] === null ? "Cannot delete without a valid ID." : "";
        $errors["delete_confirmation"] = $data["delete_confirmation"] === null ? "Forgot to check the delete box?" : "";

        $isDataValid = true;
        foreach ($errors as $error) {
            $isDataValid = $isDataValid && empty($error);
        }

        if ($isDataValid) {
            BookDB_11::delete($data["id"]);
            $url = BASE_URL . "book";
        } else {
            if ($data["id"] !== null) {
                $url = BASE_URL . "book/edit?id=" . $data["id"];
            } else {
                $url = BASE_URL . "book";
            }
        }

        ViewHelper8_11::redirect($url);
    }
}