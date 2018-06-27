<?php
use MongoDB\BSON\Type;

/**
 * Created by PhpStorm.
 * User: Jernej Habjan
 * Date: 4. 04. 2017
 * Time: 09:43
 */
class BookDB_5
{
    /**check-prime.php?number=719
     * Returns the list of all books from the 'database'
     *
     * @return array
     */
    public static function getAllBooks() {
        $books = array();
        $books[1] = new Book(1, "Prolog Programming for Artificial Intelligence", "Ivan Bratko", 50);
        $books[2] = new Book(2, "Arhitektura računalniških sistemov", "Dušan Kodek", 75);
        $books[3] = new Book(3, "Managing Information Systems Security and Privacy", "Denis Trček", 60);
        $books[4] = new Book(4, "Študijski koledar", "FRI", 10);

        return $books;
    }

    /**
     * Returns a book with a given ID. If no such book exists, an exception is thrown.
     * @param type $id
     * @return type Book
     */
    public static function get($id) {
        $allBooks = self::getAllBooks();

        if (isset($allBooks[$id])) {
            return $allBooks[$id];
        } else {
            throw new InvalidArgumentException("There is no book with id = $id.");
        }
    }

    public static function find($query) {
        $hits = [];

        foreach (self::getAllBooks() as $book) {
            $is_author = is_int(stripos($book->author, $query));
            $is_title = is_int(stripos($book->title, $query));

            if ($is_title OR $is_author) {
                $hits[] = $book;
            }
        }

        return $hits;
    }
}

require_once 'Book_5.php';

/*
 * BookDB6 simulates a database of books.
 *
 * Usually, this kind of data would be fetched from a database. But
 * for this exercise, we will use a set of hard-coded books provided
 * in a associative array.
 */
