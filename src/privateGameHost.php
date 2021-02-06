<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];
    $pind = $pin."d";

    $sql = "CREATE TABLE `$pin` (
user_id INT(6) AUTO_INCREMENT PRIMARY KEY NOT NULL, 
username VARCHAR(16) NOT NULL ,
team TINYINT(1) NOT NULL ,
start TINYINT(1) NOT NULL 
)";


if (mysqli_query($con, $sql)) {
    echo "1";
} else {
    echo "Error: " . mysqli_error();
}
mysqli_close($con);

?>
