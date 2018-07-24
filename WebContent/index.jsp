<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");  
java.util.Date currentTime = new java.util.Date();//得到当前系统时间  
String str_date1 = formatter.format(currentTime); //将日期时间格式化  
System.out.println("项目访问:"+str_date1); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>班级回忆录</title>
<link href="images/logon/home.png" rel="shortcut icon">
<link rel="stylesheet" href="css/logon/index_style.css" />
<script type="text/javascript" src="js/logon/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/logon/index.js"></script>
<script src="comm/artDialog4.1.7/artDialog.source.js?skin=black"></script>
<script src="comm/artDialog4.1.7/plugins/iframeTools.source.js"></script>
<script type="text/javascript">
$(function() {
	art.dialog({
	    title: '提示',
	    content: '超级管理员： 学号：000 密码：123123<br>管理员: 学号：001 密码：123123<br>非管理员：学号：004 密码：123123',
	    ok: function(){
	    	this.close();
	        return false;
	    }
	});
});
</script>
</head>

<body>
<div id="loading" style="width:100%;position:absolute;z-index:1;margin-top: 50px;display: none;">
    <center>
 	<img alt="加载中" src="images/logon/loading.gif"/>
 	</center>
  </div>
<form id="frm_logon" method="post">
	<div class="login_div">
		<div style="width:100%;text-align: center;padding: 5px 0px 0px 0px;opacity: 0.8;">
			<font size="+3" color="#FFFFFF">登录</font>
			<hr width="100%" style="color: #CCCCCC;" />
		</div>
		<div style="width:100%;padding: 20px 0px 0px 40px;">
			<input required='' type='text' name="number" id="number"/>
			<label alt='请输入学号' placeholder='学号'></label>
			<input required="" type="password" name="password" id="password"/>
			<label alt='请输入密码' placeholder='密码'></label>
			<div align="center" style="width:80%;">
				<input type="button" id="login_btn" class="button" value="登录"  onClick="Validate()"/>&nbsp;&nbsp;&nbsp;
				<input type="reset" class="button" value="重置" />
			</div>
		</div>
	</div>
</form>
</body>
</html>