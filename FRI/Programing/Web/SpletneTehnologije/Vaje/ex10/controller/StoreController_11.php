<?php

require_once("model/BookDB6_6.php");
require_once("model/Cart.php");
require_once("ViewHelper.php");

class StoreController_11 {

    public static function index() {
        $vars = [
            "books" => BookDB11::getAll(),
            "cart" => Cart::getAll(),
            "total" => Cart::total()
        ];

        ViewHelper8::render("view/store-index.php", $vars);
    }

    public static function addToCart() {
        $id = isset($_POST["id"]) ? intval($_POST["id"]) : null;

        if ($id !== null) {
            Cart::add($id);
        }

        ViewHelper8::redirect(BASE_URL . "store");
    }

    public static function updateCart() {
        $id = (isset($_POST["id"])) ? intval($_POST["id"]) : null;
        $quantity = (isset($_POST["quantity"])) ? intval($_POST["quantity"]) : null;

        if ($id !== null && $quantity !== null) {
            Cart::update($id, $quantity);
        }

        ViewHelper8::redirect(BASE_URL . "store");
    }

    public static function purgeCart() {
        Cart::purge();
        ViewHelper8::redirect(BASE_URL . "store");
    }
}