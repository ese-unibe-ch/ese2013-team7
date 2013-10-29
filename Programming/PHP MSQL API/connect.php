<?php

$db = @new mysqli('localhost', USERNAME, PASSWORD, DATABASE);

mysqli_set_charset($db, 'utf8');

?>