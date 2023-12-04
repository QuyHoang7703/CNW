<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'">
<link rel="stylesheet" href="./assets/css/styles.css" type="text/css" />
  <!-- <style>
        p {
            color: red;
            visibility: hidden;
        }
    </style> -->
<script>
        function validateForm(){
            var username = document.getElementById("username").value;
            var password = document.getElementById("password").value;
            if(username=="" || password==""){
                var announce = document.getElementById("announce");
                announce.style.visibility = "visible";
                return false;
            }
            
        	return true;
        }
        
       
</script>
<title>Login page</title>

</head>
<body>
	<form action="Account_Servlet" method="post" onsubmit="return validateForm()">
		<div class="wrap">
			<div class="block">
		    <h1>Đăng nhập</h1>
		    <input type="text" value="" placeholder="Tài khoản" id="username" name="txt_username" />
		    <input type="password" value="" placeholder="Mật khẩu" id="password" name="txt_password"/>
		   	<button name="btn_login" value="Login"  id="btn_login">Đăng nhập</button>
		   	<button type="button" name="btn_register" value="Register" onclick="window.location.href='./register.jsp'">Đăng kí</button>
		   	
		   	<% 
		   		String message = (String)request.getAttribute("message");
		   		if(message!=null){			
		   	%>
		 		<p style="color: red"><%= message %></p>
		   	<%}else{ %>
		   		<p id="announce" style="color: red; visibility: hidden">Vui lòng nhập đầy đủ thông tin</p>
		   	<%} %>
		   	</div>
	   	</div>
	</form>

</div>
</body>
</html>