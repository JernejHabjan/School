<?php

require_once("BookDB_5.php");

?><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Library</title>
</head>
<body>

<h1>A book library written in PHP</h1>

<p>Check our collection of fine books. <a href="search-books.php">Search for books.</a></p>

<ul>
    <?php foreach (BookDB_5::getAllBooks() as $book): ?>
        <li><a href="book-detail.php?id=<?= $book->id ?>"><?= $book->author ?>: <?= $book->title ?></a></li>
    <?php endforeach; ?>
</ul>

</body>
</html>
