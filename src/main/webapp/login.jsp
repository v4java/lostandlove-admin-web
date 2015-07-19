<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- saved from url=(0064)http://www.17sucai.com/preview/242918/2015-05-05/login/demo.html -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>江西师范大学软件学院毕业设计选题管理系统用户登录</title>
<link href="./login/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all">
<script src="http://static.lostandlove.com/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script type="text/javascript" src="./login/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="./login/Validform_v5.3.2_min.js"></script>
<!-- <script type="text/javascript">
$(document).ready(function(){
	var $tab_li = $('#tab ul li');
	$tab_li.hover(function(){
		$(this).addClass('selected').siblings().removeClass('selected');
		var index = $tab_li.index(this);
		$('div.tab_box > div').eq(index).show().siblings().hide();
	});	
});
</script> -->
<script type="text/javascript">
$(function(){
/*学生登录信息验证*/
$("#stu_username_hide").focus(function(){
 var username = $(this).val();
 if(username=='输入账号'){
 $(this).val('');
 }
});
$("#stu_username_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入账号');
 }
});
$("#stu_password_hide").focus(function(){
 var username = $(this).val();
 if(username=='输入密码'){
 $(this).val('');
 }
});
$("#stu_password_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入密码');
 }
});
$("#stu_code_hide").focus(function(){
 var username = $(this).val();
 if(username=='输入验证码'){
 $(this).val('');
 }
});
$("#stu_code_hide").focusout(function(){
 var username = $(this).val();
 if(username==''){
 $(this).val('输入验证码');
 }
});
$(".stu_login_error").Validform({
	tiptype:function(msg,o,cssctl){
		var objtip=$(".stu_error_box");
		cssctl(objtip,o.type);
		objtip.text(msg);
		console.log("dasfad");
	},
	ajaxPost:true
});
});
</script>
<script type="text/javascript">
$(function(){
	$(".screenbg ul li").each(function(){
		$(this).css("opacity","0");
	});
	$(".screenbg ul li:first").css("opacity","1");
	var index = 0;
	var t;
	var li = $(".screenbg ul li");	
	var number = li.size();
	function change(index){
		li.css("visibility","visible");
		li.eq(index).siblings().animate({opacity:0},3000);
		li.eq(index).animate({opacity:1},3000);
	}
	function show(){
		index = index + 1;
		if(index<=number-1){
			change(index);
		}else{
			index = 0;
			change(index);
		}
	}
	t = setInterval(show,8000);
	//根据窗口宽度生成图片宽度
	var width = $(window).width();
	$(".screenbg ul img").css("width",width+"px");
});
</script>
</head>

<body>
<div id="tab">
  <div class="tab_box"> 
    <!-- 学生登录开始 -->
    <div >
      <div class="stu_error_box"></div>
      <form action="login.do" method="post" class="stu_login_error">
        <div id="username">
          <label>账&nbsp;&nbsp;&nbsp;号：</label>
          <input type="text" id="stu_username_hide" name="account" value="输入账号" nullmsg="账号不能为空！" datatype="s4-18" errormsg="账号范围在4~18个字符之间！" sucmsg="账号验证通过！">
          <!--ajaxurl="demo/valid.jsp"--> 
        </div>
        <div id="password">
          <label>密&nbsp;&nbsp;&nbsp;码：</label>
          <input type="password" id="stu_password_hide" name="userPwd" value="输入密码" nullmsg="密码不能为空！" datatype="*6-16" errormsg="密码范围在6~16位之间！" sucmsg="密码验证通过！">
        </div>
        <div id="code">
          <label>验证码：</label>
          <input type="text" id="stu_code_hide" name="code" value="输入验证码" nullmsg="验证码不能为空！" datatype="*4-4" errormsg="验证码有4位数！" sucmsg="验证码验证通过！">
          <img src="./login/captcha.jpg" title="点击更换" alt="验证码占位图"> </div>
        <div id="remember">
          <input type="checkbox" name="remember">
          <label>记住密码</label>
        </div>
        <div id="login">
          <button type="submit">登录</button>
        </div>
      </form>
    </div>
   <!-- 学生登录结束-->
  </div>
</div>
<div class="bottom">©2014 Leting <a href="javascript:;" target="_blank">关于</a> <span>京ICP证030173号</span><img width="13" height="16" src="./login/copy_rignt_24.png"></div>
<div class="screenbg">
  <ul> 
    <li style="opacity: 0; visibility: visible;"><a href="javascript:;"><img src="./login/0.jpg" style="width: 1351px;"></a></li>
    <li style="opacity: 0; visibility: visible;"><a href="javascript:;"><img src="./login/1.jpg" style="width: 1351px;"></a></li>
    <li style="opacity: 1; visibility: visible;"><a href="javascript:;"><img src="./login/2.jpg" style="width: 1351px;"></a></li>
  </ul>
</div>
</body>
</html>
