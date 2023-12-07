<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.bean.ProductView"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        table {
    border-collapse: collapse;
    margin-top: 10px;
}

tbody tr {
   
  border-bottom: 2px solid #000; /* Tạo viền dưới cho mỗi hàng */
}
th, td {
  text-align: center;
}

.btn_add{
  
    float: right;
    padding: 8px;
    background-color: red;
   
}

h2{
	background-color: ff656c;
}
    </style>
</head>
<body>
	<jsp:include page="./header.jsp"></jsp:include>
	 <form action="process_add_shoe.jsp" method="post">
        <button class="btn_add">Thêm sản phẩm</button>
        <h2>Danh sách sản phẩm</h2>
        <table width="100%" >
            <thead>
                <th>Loại sản phẩm</th>
                <th>Thương hiệu</th>
                <th>Mã sản phẩm</th>
                <th>Hình ảnh</th>
                <th>Price</th>
                <th>Lựa chọn</th>
            </thead>
            
            <tbody>
            	<% 		
            		ArrayList<ProductView> listProduct = (ArrayList<ProductView>)request.getAttribute("listProduct") ;
            		for(ProductView product:listProduct){

            	%>
            	<tr>
            		<td><%=product.getcategory_name() %></td>
            		<td><%=product.getBrand() %></td>
            		<td><%=product.getId_product() %></td>
            		<td><img alt="product_image" src="data:image/jpeg;base64, <%= product.getImage_product()%>" width="100" height="100"></td>
            		<td><%=product.getPrice() %></td>
            		<td><button>Cập nhập</button> <button name="btn_del" value="<%=product.getId_product()%>">Xóa</button></td>
            	</tr>
            	<%} %>
              
            </tbody>
        </table>
    </form>
</body>
</html>