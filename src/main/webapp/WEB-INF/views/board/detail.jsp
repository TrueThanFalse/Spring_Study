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
<h1>board Detail.jsp</h1>
<br>
<table class="table">
	<tr>
	<div class="mb-3">
	  <label for="title" class="form-label">Title</label>
	  <input type="text" name="title" class="form-control" id="title" placeholder="Title..." readonly="readonly" value="${bvo.title }">
	</div>
	</tr>
	<tr>
	<div class="mb-3">
	  <label for="writer" class="form-label">Writer</label>
	  <input type="text" name="writer" class="form-control" id="writer" placeholder="Writer..." readonly="readonly" value="${bvo.writer }">
	</div>
	</tr>
	<tr>
	<div class="mb-3">
	  <label for="content" class="form-label">Content</label><br>
	  <textarea rows="3" cols="30" name="content" id="content" placeholder="Content..." readonly="readonly">${bvo.content }</textarea>
	</div>
	</tr>
	<tr>
	<div class="mb-3">
		<span class="badge text-bg-primary">${bvo.read_count }</span>
	  <label for="writer" class="form-label">Reg_date</label>
	  <input type="text" name="writer" class="form-control" id="writer" placeholder="Writer..." readonly="readonly" value="${bvo.writer }">
	</div>
	</tr>
	<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">Modify</button></a>
	<a href="/board/delete?bno=${bvo.bno }"><button type="button" class="btn btn-danger">Delete</button></a>
</table>
</div>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
</body>
</html>