<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $username = $_POST["username"];
    $isHost = $_POST["isHost"];
    $privateGame = $_POST["privateGame"];

    mysqli_query($con, "INSERT INTO Game1 (username, isHost, privateGame) VALUES ('$username', '$isHost', '$privateGame')");
    echo(1);
    mysqli_close($con);
?>
