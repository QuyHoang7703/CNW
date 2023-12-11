<%@page import="model.bean.Size"%>
<%@page import="model.bean.Product_size"%>
<%@page import="model.bean.Product_Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
		<style>
            form {
                width: 650px;
                margin: 0 auto;
            }
        </style>
        <script>
	        function previewImage() {
	            var preview = document.getElementById('product_image'); // Sử dụng ID 'product_image' cho thẻ <img>
	            var file = document.getElementById('preview').files[0]; // Sử dụng ID 'preview' cho thẻ <input>
	            var reader = new FileReader();
	
	            reader.onload = function() {
	                preview.src = reader.result;
	            };
	
	            if (file) {
	                reader.readAsDataURL(file); // Đọc file dưới dạng URL dữ liệu
	            } else {
	                preview.src = ''; // Nếu không có file, không hiển thị ảnh
	            }
	        }
	        
	        function addInput() {
                var container = document.getElementById("inputContainer");

                var newInputDiv = document.createElement("div");
                newInputDiv.innerHTML =
                    ' <label for="">Kích cỡ</label> <input type="text" name="size[]">' +
                    ' <label for="">Số lượng</label> <input type="text" name="quantity[]">' +
                    ' <button type="button" onclick="removeInput(this)">-</button>' ;
                    

                container.appendChild(newInputDiv);
            }

            function removeInput(button) {
                button.parentNode.remove();
            }
		</script>
<title>Update Product</title>
</head>
<body>
	 <form action="Product_Servlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            <table width="100%">
                <caption>
                    <h2>Cập nhập thông tin sản phẩm</h2>
                </caption>
                <tr>
                    <td>Loại sản phẩm</td>
                    <td>
                    	<%
                    		Product product = (Product)request.getAttribute("product");
                    		ArrayList<Size> listSize = (ArrayList<Size>)request.getAttribute("listSize");
                    		ArrayList<Product_Category> listCategory = (ArrayList<Product_Category>)request.getAttribute("listCategory");
                    		
                    	%>
                        <select name="id_category">
                        <option value="" disabled selected>Lựa chọn</option>
                         <option value="" disabled>Lựa chọn</option>
						    <% 
						    	String productIdCategory = product.getId_category();
						        for(Product_Category category : listCategory){
						            String categoryId = category.getId();
						            String categoryName = category.getName_category();
						           
						    %>
						            <option value="<%=categoryId%>" <%= productIdCategory.equals(categoryId) ? "selected" : "" %>><%=categoryName %></option>
						    <%}%>
                        </select>
                       
                    </td>
                </tr>
                <tr>
                    <td>Mã sản phẩm</td>
                    <td><input type="text" name="id_product" id="id_product" value="<%=product.getId()%>" readonly/></td>
                </tr>
                <tr>
                    <td>Tên sản phẩm</td>
                    <td><input type="text" name="name_product" id="name_product" value="<%=product.getName()%>"/></td>
                </tr>
                <tr>
                    <td>Thương hiệu</td>
                    <td><input type="text" name="brand" id="brand" value="<%=product.getBrand()%>"/></td>
                </tr>
                <tr>
                    <td>Giá</td>
                    <td><input type="text" name="price" id="price" value="<%=product.getPrice()%>"/></td>
                </tr>
                <tr>
                    <td>Xuất xứ</td>
                    <td><input type="text" name="origin" id="origin" value="<%=product.getOrigin()%>"/></td>
                </tr>
               	<tr>
                    <td>Hình ảnh</td>
                    <td>
                    	<%
                    		byte[] image_data = product.getImage();
                    	 	String base64Image = java.util.Base64.getEncoder().encodeToString(image_data);
                    	%>
                    	<%-- <img alt="product_image" id="image" src="data:image/jpeg;base64, <%= base64Image%>" width="100" height="100">
                        <input type="file" name="image" id="preview" onchange="previewImage()"/> --%>
                         <img alt="product_image" id="product_image" src="data:image/jpeg;base64, <%= base64Image%>" width="100" height="100">
        				 <input type="file" name="image" id="preview" onchange="previewImage()" />
                    </td>
                </tr> 
                <tr>
                    <td>Mô tả</td>
                    <td>
                        <textarea name="description" id="description" cols="20" rows="5"><%=product.getDescription()%></textarea>
                    </td>
                </tr>
               
              	
                
            </table>
            <div id="inputContainer">
	            <%
	            	ArrayList<Product_size> list = product.getProductSizes();
	            	for(Product_size product_size : list){
	            		int id_size = product_size.getId_size();
	            		
	            		for(Size size : listSize){
	            			if(size.getId()==id_size){
	            		
	            %>
                <div>
                    <label for="">Kích cỡ</label>
                    <input type="text" name="size[]" value="<%=size.getName_size()%>" />
                    <label for="">Số lượng</label>
                    <input type="text" name="quantity[]" value="<%=product_size.getQuantity()%>"/>
                    <button type="button" onclick="addInput()">+</button>
                </div>
                <%		break;	
                	} 
	            	
	            	}}%>
            </div>
            <button name="btn_updateProduct" value="btn_updateProduct" type="submit">Cập nhập sản phẩm</button>
          <%-- 	<% 
		   		String message = (String)request.getAttribute("message");
               
		   		if(message!=null){			
		   			
		   	%>
		 		<p style="color: red"><%= message %></p>
		   	<%}else{ %>
		   		<p id="announce" style="color: red; visibility: hidden">Vui lòng nhập đầy đủ thông tin</p>
		   	<%} %> --%>
        </form>
</body>
</html>