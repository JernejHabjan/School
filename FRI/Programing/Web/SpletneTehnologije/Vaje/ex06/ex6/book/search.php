<?php

require_once("BookDB6.php");

if (isset($_GET["query"])) {
    $query = $_GET["query"];
    $hits = BookDB11::search($query);
} else {
    $hits = [];
    $query = "";
}

?><!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Search books</title>
</head>
<body>

<h1>Search for books</h1>

<form action="<?= $_SERVER["PHP_SELF"] ?>" method="get">
    <label for="query">Search books:</label>
    <input type="text" name="query" id="query" value="<?= $query ?>" autofocus />
    <button type="submit">Search</button>
</form>

<?php if (!empty($hits)): ?>

    <ul>
        <?php foreach ($hits as $book): ?>
            <li><a href="detail.php?id=<?= $book["><?= $book["author"] ?>: <?= $book["title"] ?></a></li>
        <?php endforeach; ?>
    </ul>

<?php endif; ?>

</body>
</html>
