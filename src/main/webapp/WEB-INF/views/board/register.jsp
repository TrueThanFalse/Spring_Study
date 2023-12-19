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
<h1>register.jsp</h1>
<br>
<form action="/board/register" method="post">
	<div class="mb-3">
	  <label for="title" class="form-label">Title</label>
	  <!-- name의 값이 BoardVO의 멤버변수명과 일치하면 자동으로 찾아서 저장됨 -->
	  <input type="text" name="title" class="form-control" id="title" placeholder="Title...">
	</div>
	<div class="mb-3">
	  <label for="writer" class="form-label">Writer</label>
	  <input type="text" name="writer" class="form-control" id="writer" placeholder="Writer...">
	</div>
	<div class="mb-3">
	  <label for="content" class="form-label">Content</label><br>
	  <textarea rows="3" cols="30" name="content" id="content" placeholder="Content..."></textarea>
	</div>
	<button type="submit" class="btn btn-primary">Register</button>
</form>
</div>

<jsp:include page="../layout/footer.jsp"></jsp:include>
