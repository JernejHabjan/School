<!DOCTYPE html>

<link rel="stylesheet" type="text/css" href="<?= CSS_URL . "responsive.css" ?>">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $("#search-field").keyup(function(){
        $.get("<?= BASE_URL . "book/search/ajax" ?>", 
            {query: $(this).val()},
            function (data) {
                $("#book-hits").html(data);
            }
        );
    });
});
</script>
<meta charset="UTF-8" />
<title>AJAX Book search</title>

<h1>AJAX Book search</h1>

<p>[
<a href="<?= BASE_URL . "book" ?>">All books</a> |
<a href="<?= BASE_URL . "book/search" ?>">Search</a> | 
<a href="<?= BASE_URL . "book/add" ?>">Add new</a> |
<a href="<?= BASE_URL . "store" ?>">Bookstore</a> |
<a href="<?= BASE_URL . "store/ajax" ?>">AJAX Bookstore</a> |
<a href="<?= BASE_URL . "book/search/ajax" ?>">AJAX Search</a>
]</p>

<label>Search: <input id="search-field" type="text" name="query" autocomplete="off" autofocus /></label>

<ul id="book-hits"></ul>
