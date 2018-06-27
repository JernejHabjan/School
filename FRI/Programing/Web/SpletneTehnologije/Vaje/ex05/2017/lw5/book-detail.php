<?php

require_once("BookDB_5.php");

?><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Book detail</title>
</head>
<body>
<?php $book = BookDB_5::get($_GET["id"]); ?>
<h1>Details about: <?= $book->title ?></h1>
<ul>
    <li>Author: <b><?= $book->author ?></b></li>
    <li>Title: <b><?= $book->title ?></b></li>
    <li>Price: <b><?= $book->price ?> EUR</b></li>
</ul>

</body>
</html>
