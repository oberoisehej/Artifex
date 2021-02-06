<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];
    $turn = $_POST["turn"];
    $dice = $_POST["dice"];

    mysqli_query($con, "UPDATE `$pin` SET `dice`=$dice WHERE `turn`=$turn");
    echo(1);
    mysqli_close($con);
?>