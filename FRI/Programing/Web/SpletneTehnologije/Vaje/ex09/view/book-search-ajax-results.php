<?php foreach ($hits as $book): ?>

    <li><a href="<?= BASE_URL . "book?id=" . $book["id"] ?>"><?= $book["author"] ?>: 
        <?= $book["title"] ?> (<?= $book["year"] ?>)</a></li>

<?php endforeach; ?>