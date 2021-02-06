<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin =  $_POST["pin"];
    $index = $_POST["index"];
    
    $sql="SELECT * FROM `$pin` WHERE `p`>$index";
	$result=mysqli_query($con,$sql);
	
	while(list($pri, $guess, $user)=$result->fetch_row()){
        echo "$pri,$user,$guess\n";
    }
    mysqli_close($con);
?>