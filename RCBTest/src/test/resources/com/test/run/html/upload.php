<?php
$target_dir = "C:/Users/Hp/git/repository/RCBTest/RCBTest/src/test/resources/com/file/json/";
$target_file = $target_dir . basename($_FILES["jsonFile"]["name"]);

if (move_uploaded_file($_FILES["jsonFile"]["tmp_name"], $target_file)) {
    // Overwrite the existing file with the uploaded file
    rename($target_file, $target_dir . "TeamRCB.json");

    // Execute the RCBTeamValidations.xml file
    // You could use exec() function in PHP to run a shell command
    exec("java -cp /RCBTest/libs/*:. org.testng.TestNG /RCBTest/RCBTeamValidations.xml");

    // Refresh the page to display the contents of the test result file
    header("Refresh:0");
} else {
    echo "Failed to upload file.";
}
?>
