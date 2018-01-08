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
$casenum=isset($_POST['casenum']) ? $_POST['casenum'] : ''; 
$year=isset($_POST['year']) ? $_POST['year'] : '';
$month=isset($_POST['month']) ? $_POST['month'] : '';
$day=isset($_POST['day']) ? $_POST['day'] : '';
$hour=isset($_POST['hour']) ? $_POST['hour'] : '';
$minute=isset($_POST['minute']) ? $_POST['minute'] : '';
$second=isset($_POST['second']) ? $_POST['second'] : '';  

if ($id !="" and $casenum !="" and $year != ""){   
  
    $sql="insert into WalkState(id,casenum,year,month,day,hour,minute,second) values('$id','$casenum','$year','$month','$day','$hour','$minute','$second')";  
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
