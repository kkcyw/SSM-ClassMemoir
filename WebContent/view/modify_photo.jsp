<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<center>
  <form action="${pageContext.request.contextPath }/logon.do?action=modify_login_photo&zt=open&op=login_information&p_address=student_img&id=${login_id}" method="post" enctype="multipart/form-data">
   <input type="file" name="uploadFile" id="uploadFile" style="display: none"/>
    	<a href="javascript:void(0);" id="a_img" style="text-decoration: none;">
     		<img id="img" src="${pageContext.request.contextPath }/images/student_img/${photo}"  style="width: 2.5cm;height: 3.5cm;"/>
     		<br/>
     		<span id="a_img_t">修改头像</span>
  		</a>
  		<a href="javascript:void(0);" id="up_img" style="display: none;"><font color="red">确认上传</font></a>
   		<input type="submit" id="upload_btn" style="display: none" value="上传"/>
  	</form>
 </center>
  	<script src="${pageContext.request.contextPath }/js/logon/jquery-1.11.3.min.js" type="text/javascript" charset="utf-8"></script>
  	<script src="${pageContext.request.contextPath }/js/global/modify_login_photo.js" type="text/javascript" charset="utf-8"></script>
  	<script src="${pageContext.request.contextPath }/comm/artDialog4.1.7/artDialog.source.js?skin=black"></script>
	<script src="${pageContext.request.contextPath }/comm/artDialog4.1.7/plugins/iframeTools.source.js"></script>
	<script src="${pageContext.request.contextPath }/js/global/NoRightClickAndSelectAndCopyUtil.js" type="text/javascript"></script>
</body>
</html>