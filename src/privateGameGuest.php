<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $table = $_POST["pin"];

    if(mysqli_query($con,"SELECT * FROM `$table`")){
        echo "1";
    }else{
        echo "0";
    }
    mysqli_close($con);
?>
