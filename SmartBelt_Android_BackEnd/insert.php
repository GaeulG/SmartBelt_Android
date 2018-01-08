<?php  
error_reporting(E_ALL); 
ini_set('display_errors',1); 

$link=mysqli_connect("localhost","root","111111","db"); 
if (!$link)  
{ 
   echo "MySQL 접속 에러 : ";
   echo mysqli_connect_error();
   exit();
}  


mysqli_set_charset($link,"utf8");  

//POST 값을 읽어온다.
$id=isset($_POST['id']) ? $_POST['id'] : '';  
$password=isset($_POST['password']) ? $_POST['password'] : ''; 
$name=isset($_POST['name']) ? $_POST['name'] : '';  

if ($id !="" and $password !="" and $name != ""){   
  
    $sql="insert into UserInfo(id,password,name) values('$id','$password','$name')";  
    $result=mysqli_query($link,$sql);  

    if($result){  
       echo "SQL문 처리 성공";  
    }  
    else{  
       echo "SQL문 처리중 에러 발생 : "; 
       echo mysqli_error($link);
    } 
 
} else {
    echo "데이터를 입력하세요 ";
}


mysqli_close($link);
?>

<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>
   
      <form action="<?php $_PHP_SELF ?>" method="POST">
         Id: <input type = "text" name = "id" />
         Password: <input type = "text" name = "password" />
	 Name: <input type = "text" name = "name" />
         <input type = "submit" />
      </form>
   
   </body>
</html>
<?php
}
?>
