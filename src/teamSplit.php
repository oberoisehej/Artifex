<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $user_id = $_POST["id"];
    $pin = $_POST["pin"];
    
    $sql = "UPDATE `$pin` SET `team`=1 WHERE `user_id`= $user_id";
	
	//if (
	    mysqli_query($con, $sql);
	    //){
    	echo "1";
    //}else{
   	//    echo "ERROR: Could not able to execute $sql. " . mysqli_error($con);
    //}
    mysqli_close($con);
?>