<?php  

$connect=mysql_connect("localhost", "root", "111111") or
 die("Cannot connect SQL server");

mysql_query("SET NAMES UTF8");

mysql_select_db("db", $connect);

session_start();

$sql = "select * from WalkState";

$result = mysql_query($sql, $connect);

$total_record = mysql_num_rows($result);

echo "{\"status\":\"OK\",\"num_results\":\"$total_record\",\"results\":[";
 
// 반환된 각 레코드별로 JSONArray 형식으로 만들기.
for ($i=0; $i < $total_record; $i++)                    
{
   // 가져올 레코드로 위치(포인터) 이동  
   mysql_data_seek($result, $i);       
        
   $row = mysql_fetch_array($result);
   echo "{\"id\":$row[id],\"casenum\":\"$row[casenum]\",\"year\":\"$row[year]\",\"month\":\"$row[month]\",\"day\":\"$row[day]\",\"hour\":\"$row[hour]\",\"minute\":\"$row[minute]\",\"second\":\"$row[second]\"}";
 
   // 마지막 레코드 이전엔 ,를 붙인다. 그래야 데이터 구분이 되니깐.  
   if($i<$total_record-1){
      echo ",";
   }
    
}
// JSONArray의 마지막 닫기
echo "]}";
   
?>
