<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];
    $numPeople = $_POST["numPeople"];
    $i=1;

	$sql = "UPDATE `$pin` SET `start`=1 WHERE 1";
    	if (mysqli_query($con, $sql)){
    	    echo(1);
    	}else{
    	    echo "ERROR: Could not able to execute $sql. " . mysqli_error($con);
    	}
    mysqli_close($con);
?>