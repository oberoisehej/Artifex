<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];

	$sql = "SELECT `start` FROM `$pin` WHERE 1";
	$result=mysqli_query($con,$sql);
	
	//$row=mysqli_fetch_array($result,MYSQLI_BOTH);
	while(list($start)=$result->fetch_row()){
        echo "$start\n";
    }
    mysqli_close($con);
?>