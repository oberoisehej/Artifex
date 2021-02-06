<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];
    $type = $_POST["type"];
    $x = $_POST["x"];
    $y = $_POST["y"];
    $colour = $_POST["colour"];
    $thickness = $_POST["thickness"];

	$sql = "INSERT INTO `$pin` (`p`, `type`, `x`, `y`, `colour`, `thickness`) VALUES (NULL, '$type', '$x', '$y', '$colour', '$thickness')";
    	if (mysqli_query($con, $sql)){
    	    echo(true);
    	}else{
    	    echo "ERROR: Could not able to execute $sql. " . mysqli_error($con);
    	}
    mysqli_close($con);
?>