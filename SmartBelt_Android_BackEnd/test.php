<?php



$conn=mysql_connect('localhost','root','111111');



if($conn){

	echo "success";

}

else {

	echo "fail";

}



$db=mysql_select_db("test",$conn);

if($db){

	echo "db success";

}

else {

	echo "db fail";

}



$sql="create table php_tbl(num int, name varchar(10))";

mysql_query($sql, $conn);

?>
