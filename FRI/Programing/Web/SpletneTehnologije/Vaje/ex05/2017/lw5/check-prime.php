<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Answering the prime question</title>
</head>
<body>
<?php

// Complete function isPrime($number) by implementing a simple algorithm
function isPrime($number) {
    if ($number % 2 == 0) {
        return false;
    }

    for ($i = 3; $i <= sqrt($number); $i += 2) { 
        if ($number % $i == 0) {
            return false;
        }
    }

    return true;
}

$number = $_GET["number"];

?>

<h1>Checkig if <?=$number?> is prime</h1>

<p><?php

if (isPrime($number)) {
    echo "Yes, $number is a prime number.";
} else {
    echo "No, $number is not prime.";
}

?></p>
</body>
</html>