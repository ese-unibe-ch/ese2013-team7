<?php 

error_reporting(E_ALL ^ E_NOTICE);	 
header('Content-Type: text/html; charset=utf-8');

include("connect.php");

if (isset($_POST['androidrequest']) AND 
    isset($_POST['usernamemd5']) AND 
    isset($_POST['menutitle']) AND 
    isset($_POST['stars']) AND 
    isset($_POST['mensaid']) AND 
    isset($_POST['comment'])) {
    
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
		
		$_POST['menutitle'] = str_replace("%20%"," ", $_POST['menutitle']);
        $stmt->bind_param('s', $_POST['menutitle']);
        if (!$stmt->execute()) {
            die ('Query 2 konnte nicht ausgeführt werden: '.$stmt->error);
        }
        $menuid = $stmt->insert_id;
        $stmt->close();
    }
    
    $sql = 'SELECT
               id
           FROM
               user
           WHERE
               name = ?
           LIMIT
               1';
    if (!$stmt = $db->prepare($sql)) {
       die ('Etwas stimmte mit dem Query 3 nicht: '.$db->error);
    }
    $stmt->bind_param('s', $_POST['usernamemd5']);
    if (!$stmt->execute()) {
       die ('Query 3 konnte nicht ausgef�hrt werden: '.$stmt->error);
    }
    $stmt->bind_result($userid);
    $statusUser = (bool) $stmt->fetch();
    $stmt->close();
    
    if (!$statusUser) {
        $sql = 'INSERT INTO
                    user(name)
                VALUES
                    (?)';
        if (!$stmt = $db->prepare($sql)) {
            die ('Etwas stimmte mit dem Query 4 nicht: '.$db->error);
        }
        $stmt->bind_param('s', $_POST['usernamemd5']);
        if (!$stmt->execute()) {
            die ('Query 4 konnte nicht ausgeführt werden: '.$stmt->error);
        }
        $userid = $stmt->insert_id;
        $stmt->close();
    }
    
    $sql = 'SELECT
               id
           FROM
               rating
           WHERE
               userid = ? AND
               menuid = ?
           LIMIT
               1';
    if (!$stmt = $db->prepare($sql)) {
       die ('Etwas stimmte mit dem Query 5 nicht: '.$db->error);
    }
    $stmt->bind_param('ii', $userid, $menuid);
    if (!$stmt->execute()) {
       die ('Query 5 konnte nicht ausgef�hrt werden: '.$stmt->error);
    }
    $stmt->bind_result($ratingid);
    $statusRating = (bool) $stmt->fetch();
    $stmt->close();
    
    if (!$statusRating) {
        $sql = 'INSERT INTO
                    rating(stars, comment, time, userid, menuid, mensaid)
                VALUES
                    (?, ?, , NOW(), ?, ?, ?)';
        if (!$stmt = $db->prepare($sql)) {
            die ('Etwas stimmte mit dem Query 6 nicht: '.$db->error);
        }
        $stmt->bind_param('isiii', $_POST['stars'], $_POST['comment'], $userid, $menuid, $_POST['mensaid']);
        if (!$stmt->execute()) {
            die ('Query 6 konnte nicht ausgeführt werden: '.$stmt->error);
        }
        $userid = $stmt->insert_id;
        $stmt->close();
    } else {
        $sql = 'UPDATE 
                    rating
                SET 
                    stars = ?,
                    comment = ?,
                    time = NOW(),
                WHERE 
                    userid  = ? AND menuid = ? AND mensaid = ?';
        	  $stmt = $db->prepare($sql);
         if (!$stmt) {
            die ('Etwas stimmte mit dem Query 7 nicht: '.$db->error);
         } 
         $stmt->bind_param('isiii', $_POST['stars'], $_POST['comment'], $userid, $menuid, $_POST['mensaid']);
         if (!$stmt->execute()) {
            die ('Query 7 konnte nicht ausgef�hrt werden: '.$stmt->error);
         }
         $stmt->close();
    }
}


?>