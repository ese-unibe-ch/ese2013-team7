<?php 

error_reporting(E_ALL ^ E_NOTICE);	 
header('Content-Type: text/html; charset=utf-8');

include("connect.php");

if (isset($_GET['androidrequest']) AND 
    isset($_GET['menutitle'])) {
    
    $sql = 'SELECT
               id
           FROM
               menu
           WHERE
               title = ?
           LIMIT
               1';
    if (!$stmt = $db->prepare($sql)) {
       die ('Etwas stimmte mit dem Query 1 nicht: '.$db->error);
    }
    $stmt->bind_param('s', $_POST['menutitle']);
    if (!$stmt->execute()) {
       die ('Query 1 konnte nicht ausgeführt werden: '.$stmt->error);
    }
    $stmt->bind_result($menuid);
    $statusMenu = (bool) $stmt->fetch();
    $stmt->close();
    
    if (!$statusMenu) {
        $sql = 'INSERT INTO
                    menu(title)
                VALUES
                    (?)';
        if (!$stmt = $db->prepare($sql)) {
            die ('Etwas stimmte mit dem Query 2 nicht: '.$db->error);
        }
        $stmt->bind_param('s', $_POST['menutitle']);
        if (!$stmt->execute()) {
            die ('Query 2 konnte nicht ausgeführt werden: '.$stmt->error);
        }
        $menuid = $stmt->insert_id;
        $stmt->close();
    }

    $sql = 'SELECT
               user.name,
               rating.stars,
               rating.comment
           FROM
               rating
           JOIN
               user
           ON
               rating.userid = user.id
           WHERE
               rating.menuid = ?
           ORDER BY
               rating.time DESC';
    if (!$stmt = $db->prepare($sql)) {
        die ('Etwas stimmte mit dem Query 3 nicht: '.$db->error);
    }
    $stmt->bind_param('i', $menuid);
    if (!$stmt->execute()) {
        die ('Query 3 konnte nicht ausgef�hrt werden: '.$stmt->error);
    }
    $stmt->bind_result($name, $stars, $comment);
    $ratings = array();
    $numOfRatings = 0;
    $totalStars = 0;
    while ($stmt->fetch()) {
        $ratings[] = array('username' => $name,
                           'stars' => $stars,
                           'comment' => $comment);
        $totalStars += $stars;
        $numOfRatings++;
    }
    if ($numOfRatings != 0) {
        $avgStars = ($totalStars / $numOfRatings);
    } else {
        $avgStars = -1;
    }
    
    
    
    $stmt->close();
?>