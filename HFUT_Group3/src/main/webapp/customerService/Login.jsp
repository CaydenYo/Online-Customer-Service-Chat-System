<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  
<title>Insert title here</title>  
</head>  
<body>  
<jsp:useBean id="customerSvcLogin" scope="request" class="com.pentaKill.domain.CustomerSvcLogin"></jsp:useBean>
<c:url var="url" scope="request" value="/customerService/Login" />
    <form:form modelAttribute="customerSvcLogin" action="${url}" method="post">
		<fieldset class="input">
		<p><font color="red" size="2"><form:errors /></font></p>
		<p id="login-form-email">	
			<input type="text" id="cs_email" path="cs_email" name="cs_email" value="cs_email" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'email';}">
			<font color='red' size="-1">
				<i><form:errors path="cs_email" /></i>
			</font><br /><br />
		</p>
		<p id="login-form-password">
			<input type="password" id="cs_pwd" path="cs_pwd" name="cs_pwd" value="cs_pwd" onfocus="this.value = '';" >
	    	<font color='red' size="-1">
				<i><form:errors path="cs_pwd" /></i>
			</font><br /><br />
		</p>
	    <div class="forgot">
	    	<a href="#">forgot Password</a>
	    	<input type="submit" class='button' value="Login" >
	    </div>
	    </fieldset>
	    </form:form>
</body>  
</html>  