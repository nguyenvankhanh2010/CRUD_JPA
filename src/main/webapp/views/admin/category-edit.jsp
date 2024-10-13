<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<form action="<c:url value="/admin/category/update"/>" method="post"
	enctype="multipart/form-data">
	<input type="text" name="categoryid" hidden="hidden"
		value="${cate.categoryId}"> 
		<label for="fname">Category name:</label><br> 
		<input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br> 
		<label for="lname">Link images:</label><br> 
		<input type="text" id="images" name="images" value="${cate.images}"><br> 
		<label for="lname">upload file:</label><br> 
		<input type="file" id="images1" name="images1" value="${cate.images}"><br> 
		<label for="lname">Status:</label><br> 
		<input type="radio" name="status" value="1" ${cate.status==1?'checked':''}> 
		<label for="css">Hoạt động</label><br> 
		<input type="radio"  name="status" value="0" ${cate.status!=1?'checked':''}> 
		<label for="javascript">Khóa</label><br>
	<hr>
	<input type="submit" value="Insert">
</form>