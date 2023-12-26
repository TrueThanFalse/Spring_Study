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
<!-- file upload를 위해 enctype="multipart/form-data" 추가 -->
<form action="/board/register" method="post" enctype="multipart/form-data">
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
	
	<!-- file upload 관련 입력 라인 추가 -->
	<div class="mb-3">
	  <label for="file" class="form-label">files...</label>
	  <input type="file" name="files" class="form-control" id="file" multiple="multiple" style="display: none">
	  <br>
	  <button type="button" class="btn btn-primary" id="trigger">fileUpload</button>
	</div>
	<!-- 파일 목록 표기 라인 -->
	<div class="mb-3" id="fileZone"></div>
	
	<!-- 파일은 있을수도 있고 없을수도 있으므로 disabled 속성은 추가하지 않음 -->
	<button type="submit" class="btn btn-primary" id="regBtn" >Register</button>
</form>
</div>

<script type="text/javascript" src="/resources/js/boardRegister.js"></script>

<jsp:include page="../layout/footer.jsp"></jsp:include>
