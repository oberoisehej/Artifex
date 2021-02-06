<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin =  $_POST["pin"];
    $turn = $_POST["turn"];
    
    $sql="SELECT `drawer`FROM `$pin` WHERE `turn`=$turn";
	$result=mysqli_query($con,$sql);
	
	while(list($draw)=$result->fetch_row()){
        echo "$draw";
    }
    mysqli_close($con);
?>