<?php

session_start();

require_once("controller/BookController.php");
require_once("controller/StoreController.php");

define("BASE_URL", $_SERVER["SCRIPT_NAME"] . "/");
define("IMAGES_URL", rtrim($_SERVER["SCRIPT_NAME"], "index.php") . "static/images/");
define("CSS_URL", rtrim($_SERVER["SCRIPT_NAME"], "index.php") . "static/css/");

$path = isset($_SERVER["PATH_INFO"]) ? trim($_SERVER["PATH_INFO"], "/") : "";

$urls = [
    "book" => function () {
       BookController::index();
    },
    "book/search" => function () {
        BookController::search();
    },
    "book/add" => function () {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            BookController::add();
        } else {
            BookController::showAddForm();
        }
    },
    "book/edit" => function () {
        if ($_SERVER["REQUEST_METHOD"] == "POST") {
            BookController::edit();
        } else {
            BookController::showEditForm();
        }
    },
    "book/delete" => function () {
        BookController::delete();
    },
    "store" => function () {
        StoreController11::index();
    },
    "store/cart" => function () {
        StoreController11::cartContents();
    },
    "store/add-to-cart" => function () {
        StoreController11::addToCart();
    },
    "store/update-cart" => function () {
        StoreController11::updateCart();
    },
    "store/purge-cart" => function () {
        StoreController11::purgeCart();
    },
    "store/ajax" => function () {
        StoreController11::indexAjax();
    },
    "book/search/ajax" => function () {
        BookController::searchAjax();
    },
    "" => function () {
        ViewHelper::redirect(BASE_URL . "store");
    },
];

try {
    if (isset($urls[$path])) {
       $urls[$path]();
    } else {
        echo "No controller for '$path'";
    }
} catch (Exception $e) {
    echo "An error occurred: <pre>$e</pre>";
    // ViewHelper::error404();
} 
