<html xmlns="http://www.w3.org/1999/xhtml"> 
<head> 
<title>test</title> 
<meta http-equiv="content-type" content="text/html; charset=UTF-8" /> 
</head> 
<body> 
<!-- 只能输入数字 --> 
<input type="text" id="test" name="test" onkeyup="value=value.replace(/[^\d]/g,'')"/>
 <input type="button" onclick="test()" value="test"> 
<input type="text" id="age" name="age"> 
<input type="button" onclick="GetAge()" value="年龄"> 
</body> 

<script type="text/javascript"> 

//手机号码校验 
function test(){ 
var a = document.getElementById("test").value; 
var reg = /^1[3|4|5|8][0-9]\d{4,8}$/; 
if(!reg.test(a)){ 
alert("手机号码格式不正确！！"); 
} 
} 

//身份证获取当前年龄 
function GetAge() { 
var a = document.getElementById("age").value; 
var strBirthday = a.substr(6, 4) + "/" + a.substr(10, 2) + "/" + a.substr(12, 2);
 var birthDate = new Date(strBirthday); 
var nowDateTime = new Date(); 
var age = nowDateTime.getFullYear() - birthDate.getFullYear(); 
//再考虑月、天的因素;.getMonth()获取的是从0开始的，这里进行比较，不需要加1 
if (nowDateTime.getMonth() < birthDate.getMonth() || (nowDateTime.getMonth() == birthDate.getMonth() && nowDateTime.getDate() < birthDate.getDate())) {
 age--; 
} 
alert(age); 
} 

</script> 

</html> 