<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>

<div class="container-md">
<h1>board Modify.jsp</h1>
<br>
<form action="/board/edit" method="post">
<table class="table">
	<tr>
	<div class="mb-3">
	  <label for="title" class="form-label">Title</label>
	  <input type="text" name="title" class="form-control" id="title" value="${bvo.title }">
	</div>
	</tr>
	<tr>
	<div class="mb-3">
	  <label for="writer" class="form-label">Writer</label>
	  <input type="text" name="writer" class="form-control" id="writer" readonly="readonly" value="${bvo.writer }">
	</div>
	</tr>
	<tr>
	<div class="mb-3">
	  <label for="content" class="form-label">Content</label><br>
	  <textarea rows="3" cols="30" name="content" id="content">${bvo.content }</textarea>
	</div>
	</tr>
	<tr>
	<div class="mb-3">
		<span class="badge text-bg-primary">${bvo.read_count }</span>
	  <label for="reg_date" class="form-label">Reg_date</label>
	  <input type="text" name="reg_date" class="form-control" id="reg_date" readonly="readonly" value="${bvo.reg_date }">
	</div>
	</tr>
	<button type="submit" class="btn btn-primary">Edit</button>
	<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
	<input type="hidden" name="bno" value="${bvo.bno }"> 
</table>
</div>
</form>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
</body>
</html>