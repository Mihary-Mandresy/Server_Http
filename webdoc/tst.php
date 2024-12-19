<?php

session_start();

$_SESSION["gaigy"] = "baba";
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=\, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>tst</h1>

    <pre>
        <?php print_r($_SESSION) ?>
    </pre>
</body>
</html>