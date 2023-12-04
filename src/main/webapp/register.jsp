<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'">
<link rel="stylesheet" href="./assets/css/styles.css" type="text/css" />
<script>
	function check_validForm() {
	    var username = document.getElementById("username").value;
	    var password = document.getElementById("password").value;
	    var name = document.getElementById("name").value;
	    var email = document.getElementById("email").value;
	    var phone_number = document.getElementById("phone_number").value;
	    var address = document.getElementById("address").value;
	
	    if (username == "" || password == "" || name == "" || email == "" || phone_number == "" || address == "") {
	        var announce = document.getElementById("annouce"); 
	        announce.style.visibility = "visible";
	        return false;
	    }
	
	    return true;
	}
        
       
</script>
<title>Insert title here</title>
</head>
<body>
	 <form action="Account_Servlet" method="post" onsubmit="return check_validForm()">
      <div class ="wrap">
            <div class="block">
                <h1>Đăng kí tài khoản</h1>
                <input type="text" value="" placeholder="Tên tài khoản" id="username" name="txt_username" onblur="myFunction()"/>
                <input type="password" value="" placeholder="Mật khẩu" id="password" name="txt_password"/>
                <input type="text" value="" placeholder="Họ và tên" id="name" name="txt_name"/>
                <input type="text" value="" placeholder="Email" id="email" name="txt_email"/>
                <input type="text" value="" placeholder="Số điện thoại" id="phone_number" name="txt_phone"/>
                <input type="text" value="" placeholder="Địa chỉ" id="address" name="txt_address"/>
                <button name="btn_create_account" value="Register" >Tạo tài khoản</button>
                <button type ="button" name="btn_back" value="Back" onclick="window.location.href = './login.jsp'">Quay lai</button>
                <% 
                	String message = (String)request.getAttribute("message");
                	if(message!=null){
                		
                %>
                	<p style="color: red"><%= message %></p>
                <%} else{ %>
                	<p style="  color: red; visibility: hidden; " id="annouce">Vui lòng nhập đầy đủ thông tin</p>
               	<% }%>
            </div>
      </div>
    </form>
</body>
</html>