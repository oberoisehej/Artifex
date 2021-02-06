<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];
    $turn = $_POST["turn"];
    
    $pind = substr($pin,0,-1).$turn."d";
    $ping = substr($pin,0,-1).$turn."g";

    mysqli_query($con, "UPDATE `$pin` SET `start`=1 WHERE `turn`=$turn");
    
    $sql1 = "CREATE TABLE `$pind` (
p INT(6) AUTO_INCREMENT PRIMARY KEY NOT NULL, 
type INT(11) NOT NULL ,
x FLOAT(24) NOT NULL,
y FLOAT(24) NOT NULL,
colour INT(11) NOT NUll,
thickness INT(11) NOT NUll)";
    
    $sql2 = "CREATE TABLE `$ping` (
p INT(6) AUTO_INCREMENT PRIMARY KEY NOT NULL, 
guess VARCHAR(16) NOT NULL ,
user VARCHAR(16) NOT NULL)";
    
    mysqli_query($con, $sql1);
    mysqli_query($con, $sql2);
    
    echo(1);
    
    mysqli_close($con);
?>