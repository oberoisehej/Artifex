<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin =  $_POST["pin"];
    $turn = $_POST["turn"];
    $num = $_POST["num"];
    
    $sql="SELECT `Word` FROM Words WHERE `id`=$num";
	$result=mysqli_query($con,$sql);
	
	while(list($word)=$result->fetch_row()){
	    mysqli_query($con, "UPDATE `$pin` SET `word`='$word' WHERE `turn`=$turn");
	}
        
    
    
    mysqli_close($con);
?>