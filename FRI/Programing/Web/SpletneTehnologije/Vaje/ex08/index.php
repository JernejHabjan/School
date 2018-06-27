<?php

require_once("controller/BookController_8.php");

define("BASE_URL", $_SERVER["SCRIPT_NAME"] . "/");
define("IMAGES", rtrim($_SERVER["SCRIPT_NAME"], "index.php") . "static/images/");

$path = isset($_SERVER["PATH_INFO"]) ? trim($_SERVER["PATH_INFO"], "/") : "";

$urls = [
    "book" => function () {
        if (isset($_GET["id"])) {
            BookController_8::get();
        } else {
            BookController_8::getAll();
        }
    },
    "book/add" => function () {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            BookController_8::add();
        } else {
            BookController_8::showAddForm();
        }
    },
    "" => function () {
        ViewHelper_8::redirect(BASE_URL . "book");
    },

    // TODO: Add router entries for 1) search, 2) book/edit and 3) book/delete

    "book/search" => function () {
        //echo "router book/search is found";
        BookController_8::search();
    },
    "book/edit" => function () {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            BookController_8::edit();
        } else {
            BookController_8::showEditForm();
        }
    },
    "book/delete" => function () {
        BookController_8::delete();
    }
];

try {
    if (isset($urls[$path])) {
       $urls[$path]();
    } else {
        echo "No controller for '$path'";
    }
} catch (Exception $e) {
    ViewHelper_8::error404();
} 
