<%--
  Created by IntelliJ IDEA.
  User: Lord
  Date: 2018/10/16
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:forEach items="${ulist}" var="u">
    ${u.username}----${u.password}
</c:forEach>
</body>
</html>
location="D:\java培训\第二阶段v2.0\项目内容\第二天\xiaomi2\WebContent\WEB-INF\lib\mysql-connector-java-5.0.8-bin.jar" />
targetProject="D:\java培训\3阶段IDEA存放\ssm1016\src\main\java">
targetProject="D:\java培训\3阶段IDEA存放\ssm1016\src\main\resources">
targetProject="D:\java培训\3阶段IDEA存放\ssm1016\src\main\java">


 /user/password中的 ajax:

var ok1=false,ok2=false,ok3=false;
$(function () {
//验证密码
$("[name=yuanpass]").blur(function () {
var upass = $(this).val();
$.ajax({
url:"/user/checkpass",
data:"upass="+upass,
type:"post",
datatype:"text",
success:function (rs) {
if(rs=='密码正确'){
ok1=true;
}else{
ok1=false;
}
$("[name=yuanpass]").next().html("<font color='red'>"+rs+"</font>")
}

});
});

//验证新密码
$("[name=userPs]").blur(function () {
var newpass = $(this).val();
//正则
var reg = /^\w{6,}$/;
if(reg.test(newpass)){
$(this).next().html("新密码正确");
ok2=true;
}else {
$(this).next().html("新密码必须6位数以上");
ok2=false;
}
})

//重复密码
$("[name=newPass2]").blur(function () {
var pass2 = $(this).val();
var pass1 =$("[name=userPs]").val();
var reg = /^\w{6,}$/;
if(reg.test(pass2)){
if(pass1==pass2){
$(this).next().html("重复密码正确");
ok3=true;
}else {
$(this).next().html("两次密码不一致");
ok3=false;
}
}else {
$(this).next().html("新密码必须6位数以上");
ok3=false;
}

});


$("#button2").click(function () {
if(ok1&&ok2&&ok3){
document.forms[0].submit();
}else {
alert("填写信息有误");
}
})


});