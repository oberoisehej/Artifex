<?php
    $con = mysqli_connect("localhost", "id3764634_sehejoberoi10", "abcd1234", "id3764634_csia");
    // Check connection
    if (mysqli_connect_errno()){
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    
    $pin = $_POST["pin"];

    $sql = "CREATE TABLE `$pin` (
turn INT(6) AUTO_INCREMENT PRIMARY KEY NOT NULL, 
drawer VARCHAR(16) NOT NULL,
dice INT(6) NOT NULL,
word VARCHAR(16) NOT NULL,
start TINYINT(1) NOT NULL
)";

if (mysqli_query($con, $sql)) {
    echo "1";
} else {
    echo "ERROR: Could not able to execute $sql. " . mysqli_error($con);;
}
mysqli_close($con);

?>