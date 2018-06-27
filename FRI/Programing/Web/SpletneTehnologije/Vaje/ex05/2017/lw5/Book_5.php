<?php

class Book {
    public $id = 0;
    public $title = null;
    public $author = null;
    public $price = 0.0;

    /**
     * Constructor: creates a new instance.
     * @param $id
     * @param $title
     * @param $author
     * @param $price 
     */
    public function __construct($id, $title, $author, $price) {
        $this->id = $id;
        $this->title = $title;
        $this->author = $author;
        $this->price = $price;
    }

    /**
     * A string representation
     * @return String
     */
    public function __toString() {
        return sprintf("Book{%d, %s, %s, %.2f}", $this->id, $this->author, $this->title, $this->price);
    }

}