﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/jsp/share/jquery_easyui.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>国际邮政账务数据采集系统v1.0</title>
<style type="text/css">
<!--
*{overflow:hidden; font-size:11pt;}
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-image: url(images/bg.gif);
	background-repeat: repeat-x;
}
-->
</style></head>
<script type="text/javascript">

$(function(){
	$("#loginButton").keydown(function(){submitLoginForm()})
	$("#loginButton").click(function(){submitLoginForm()})
	$("#userPwd").blur(function(){submitLoginForm()})
});
function submitLoginForm(){
	//$("#loginForm");
	var u = $("#userName");
	var p = $("#userPwd");
	if(u.val()!="" && p.val()!=""){
		$('#loginForm').submit();
	}else{
		if(u.val()==""){
			window.alert("请填写用户名");
			return false;
		}
		if(p.val()==""){
			window.alert("请填写密码");
			return false;
		}
	}
}
</script>

<body>

<form id="loginForm" method="post" action="j_spring_security_check">

<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="561" style="background:url(images/lbg.gif)"><table width="940" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="238" style="background:url(images/login01.jpg)">&nbsp;</td>
          </tr>
          <tr>
            <td height="190"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="208" height="190" style="background:url(images/login02.jpg)">&nbsp;</td>
                <td width="518" style="background:url(images/login03.jpg)"><table width="320" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="40" height="50"><img src="images/user.gif" width="30" height="30"></td>
                    <td width="38" height="50">用户</td>
                    <td width="242" height="50"><input type="text" name="j_username" id="userName" style="width:164px; height:32px; line-height:34px; background:url(images/inputbg.gif) repeat-x; border:solid 1px #d1d1d1; font-size:9pt; font-family:Verdana, Geneva, sans-serif;"></td>
                  </tr>
                  <tr>
                    <td height="50"><img src="images/password.gif" width="28" height="32"></td>
                    <td height="50">密码</td>
                    <td height="50"><input type="password" name="j_password" id="userPwd" style="width:164px; height:32px; line-height:34px; background:url(images/inputbg.gif) repeat-x; border:solid 1px #d1d1d1; font-size:9pt; "></td>
                  </tr>
                  <tr>
                    <td height="40">&nbsp;</td>
                    <td height="40">&nbsp;</td>
                    <td height="60"> 
                    <input name="login"  id="loginButton" type="button" style="background:url(images/login.gif) no-repeat;width:100px;height:37px;border:0;" />            
                   <!--<img src="images/login.gif" width="95" height="34">--> </td>
                  </tr>
                </table></td>
                <td width="214" style="background:url(images/login04.jpg)" >&nbsp;</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="133" style="background:url(images/login05.jpg)">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>

</html>