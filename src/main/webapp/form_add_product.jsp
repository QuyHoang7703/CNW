<%@page import="model.bean.Product_Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
		<style>
            form {
                width: 650px;
                margin: 0 auto;
            }
        </style>
        <script>
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
            
            function validateForm(){
            	var id_category = document.getElementsByName("id_category")[0].value;
                var id_product = document.getElementById("id_product").value;
                var name_product = document.getElementById("name_product").value;
                var brand = document.getElementById("brand").value;
                var price = document.getElementById("price").value;
                var origin = document.getElementById("origin").value;
                var description = document.getElementById("description").value;
                var image = document.getElementById("image").value;
                
               
                
             // Kiểm tra inputContainer
                var inputs = document.querySelectorAll('#inputContainer input[type="text"]');
                for (var i = 0; i < inputs.length; i++) {
                    if (inputs[i].value === "") {
                         var announce = document.getElementById("announce");
                        announce.style.visibility = "visible";
                        announce.innerHTML = "Vui lòng điền đầy đủ thông tin"; 
                        return false;
                    }
                }
                
                

                // Kiểm tra các trường thông tin sản phẩm
                if (id_category === "" || id_product === "" || name_product === "" || brand === "" || price === "" || origin === "" || description === "") {
                     var announce = document.getElementById("announce");
                    announce.style.visibility = "visible";
                    announce.innerHTML = "Vui lòng điền đầy đủ thông tin"; 
                    return false;
                }
                
                if (image === "") {
                    var announce = document.getElementById("announce");
                    announce.style.visibility = "visible";
                    announce.innerHTML = "Vui lòng chọn hình ảnh";
                    return false;
                }

                return true;
            }
            
        </script>
       
</head>
<body>
	
	 <form action="Product_Servlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
            <table width="100%">
                <caption>
                    <h2>Thêm sản phẩm</h2>
                </caption>
                <tr>
                    <td>Loại sản phẩm</td>
                    <td>
                    	
                        <select name="id_category">
                        <option value="" disabled selected>Lựa chọn</option>
                        <% 
							ArrayList<Product_Category> listCategory = (ArrayList<Product_Category>)request.getAttribute("listCategory");
                        	
							for(Product_Category category : listCategory){
								
						%>
                            <option value="<%=category.getId()%>"><%=category.getName_category() %></option>

						<%}%>
                        </select>
                       
                    </td>
                </tr>
                <tr>
                    <td>Mã sản phẩm</td>
                    <td><input type="text" name="id_product" id="id_product" /></td>
                </tr>
                <tr>
                    <td>Tên sản phẩm</td>
                    <td><input type="text" name="name_product" id="name_product" /></td>
                </tr>
                <tr>
                    <td>Thương hiệu</td>
                    <td><input type="text" name="brand" id="brand" /></td>
                </tr>
                <tr>
                    <td>Giá</td>
                    <td><input type="text" name="price" id="price" /></td>
                </tr>
                <tr>
                    <td>Xuất xứ</td>
                    <td><input type="text" name="origin" id="origin" /></td>
                </tr>
               	<tr>
                    <td>Hình ảnh</td>
                    <td>
                        <input type="file" name="image" id="image" />
                    </td>
                </tr> 
                <tr>
                    <td>Mô tả</td>
                    <td>
                        <textarea name="description" id="description" cols="20" rows="5"></textarea>
                    </td>
                </tr>
               
              	
                
            </table>
            <div id="inputContainer">
                <div>
                    <label for="">Kích cỡ</label>
                    <input type="text" name="size[]"  />
                    <label for="">Số lượng</label>
                    <input type="text" name="quantity[]" />
                    <button type="button" onclick="addInput()">+</button>
                </div>
            </div>
            <button name="btn_addProduct" value="btn_addProduct" type="submit">Thêm sản phẩm</button>
          	<% 
		   		String message = (String)request.getAttribute("message");
		   		if(message!=null){			
		   	%>
		 		<p style="color: red"><%= message %></p>
		   	<%}else{ %>
		   		<p id="announce" style="color: red; visibility: hidden">Vui lòng nhập đầy đủ thông tin</p>
		   	<%} %>
        </form>
</body>
</html>