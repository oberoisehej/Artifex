<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $username = $_POST["username"];
    $table = $_POST["table"];

    mysqli_query($con, "INSERT INTO `$table` (username, team, start) VALUES ('$username',0,0)");
    echo(1);
    mysqli_close($con);
?>
