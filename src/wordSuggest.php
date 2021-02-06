<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $word = $_POST["word"];

    if (mysqli_query($con, "INSERT INTO `newWords` (`id`, `word`) VALUES (NULL, '$word')")){
        echo(1);
    }else{
        echo("Error ".mysqli_error($con));
    }
    mysqli_close($con);
?>