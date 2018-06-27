<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="<?= CSS_URL . "responsive.css" ?>">
<meta charset="UTF-8" />
<title>Add entry</title>

<h1>Add new book</h1>

<?php include("view/menu-links.php"); ?>

<form action="<?= BASE_URL . "book/add" ?>" method="post">
    <p>
        <label>Author: <input type="text" name="author" value="<?= $book["author"] ?>" 
        pattern="[ a-zA-ZšđčćžŠĐČĆŽ\.\-]+" title="The book author" required autofocus />
        <span class="important"><?= $errors["author"] ?></span></label>
	</p>
    <p>
        <label>Title: <input type="text" name="title" value="<?= $book["title"] ?>"
        title="The title of the book" required />
        <span class="important"><?= $errors["title"] ?></span></label>
	</p>
    <p>
        <label>Description: <br />
		<textarea name="description" rows="10" cols="40"><?= $book["description"] ?></textarea></label>
	</p>
    <p>
        <label>Price: <input type="number" name="price" value="<?= $book["price"] ?>" min="0" required />
        <span class="important"><?= $errors["price"] ?></span></label>
    </p>
    <p>
        <label>Year: <input type="number" name="year" value="<?= $book["year"] ?>" min="1500" max="2020" required />
        <span class="important"><?= $errors["year"] ?></span></label>
    </p>
    <p>
        <label>Quantity: <input type="number" name="quantity" value="<?= $book["quantity"] ?>" min="10" required />
        <span class="important"><?= $errors["quantity"] ?></span></label>
    </p>
    <p><button>Insert</button></p>
</form>
