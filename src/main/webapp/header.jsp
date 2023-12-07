<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.navbar {
		background-color: #ddd;
		padding: 20px;
		display: flex;
		align-items: center;
	}
	a {
		text-decoration: none;
		color: back;
		padding: 10px;
		
	}
	
	.option {
		margin-left: 100px;
	}
	
	.option a{
		margin-right: 50px;
		font-size: 17px;
	}
	
/* 	.option a:hover{
		background-color: #ccc;
	} */
	
	.home{
		font-size: 20px;
		font-weight: 700;
	}
</style>
</head>
<body>
	<form action="ProductView_Servlet" methob="post">
		  <nav class="navbar">
                    <a href="#" class="home">Page-Admin</a>
                    <!-- Navigation -->
                    <div class="option">
                    	<a href="ProductView_Servlet?function=manage">Management Product</a>
	                    <a href="#contact">Setting</a>
	                    <a href="#contact">Log out</a>
                    </div>
 
         </nav>          
	</form>
</body>
</html>