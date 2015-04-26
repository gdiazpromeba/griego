<?php
header('Content-Type: text/plain; charset=UTF-8');

$username="kallisto_gonzalo";
$password="manuela";
$database="kallisto_itsnofun";

mysql_connect(localhost,$username,$password);
@mysql_select_db($database) or die( "Unable to select database");
$query="SELECT * FROM DICCIONARIO WHERE LETRA='A'";
$result=mysql_query($query);

while ($row = mysql_fetch_array($result, MYSQL_NUM)) {
    print_r($row);
    echo  $row[6] ;
    echo "\n";
}

mysql_free_result($result);

mysql_close();

?>