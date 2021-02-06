<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];
    $drawer = $_POST["drawer"];

    mysqli_query($con, "INSERT INTO `$pin` (drawer, dice) VALUES ('$drawer',-1)");
    echo(1);
    mysqli_close($con);
?>