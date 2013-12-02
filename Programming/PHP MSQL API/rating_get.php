<?php
header('Content-Type: application/json');
header('Cache-Control: no-cache, must-revalidate');
header('Expires: Mon, 26 Jul 1997 05:00:00 GMT');

error_reporting(E_ALL ^ E_NOTICE);

include("connect.php");
include("json_readable.php");

if (isset($_GET['androidrequest']) AND 
    isset($_GET['mensaid']) AND 
    isset($_GET['menutitle']) AND strlen($_GET['menutitle']) > 3) {
    
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
    $stmt->bind_param('s', $_GET['menutitle']);
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
        $stmt->bind_param('s', $_GET['menutitle']);
        if (!$stmt->execute()) {
            die ('Query 2 konnte nicht ausgeführt werden: '.$stmt->error);
        }
        $menuid = $stmt->insert_id;
        $stmt->close();
    }

    $sql = 'SELECT
               user.name,
               rating.stars,
               rating.comment,
			   rating.time
           FROM
               rating
           JOIN
               user
           ON
               rating.userid = user.id
           WHERE
               rating.menuid = ? AND rating.mensaid = ?
           ORDER BY
               rating.time DESC';
    if (!$stmt = $db->prepare($sql)) {
        die ('Etwas stimmte mit dem Query 3 nicht: '.$db->error);
    }
    $stmt->bind_param('ii', $menuid, $_GET['mensaid']);
    if (!$stmt->execute()) {
        die ('Query 3 konnte nicht ausgef�hrt werden: '.$stmt->error);
    }
    $stmt->bind_result($name, $stars, $comment, $time);
    $ratings = array();
    $numOfRatings = 0;
    $totalStars = 0;
    while ($stmt->fetch()) {
        $ratings['result']['content'][] = array('username' => $name,
                           'stars' => $stars,
                           'comment' => $comment,
						   'time' => $time);
        $totalStars += $stars;
        $numOfRatings++;
    }
    if ($numOfRatings != 0) {
        $avgStars = ($totalStars / $numOfRatings);
    } else {
        $avgStars = -1;
    } 
    
    $ratings['result']['avgstars'] = $avgStars;
    
    $stmt->close();
    //echo json_encode($ratings);    
    echo indent(json_encode($ratings));

}
?>