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
</table>
	<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
	<a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-warning">Modify</button></a>
	<a href="/board/delete?bno=${bvo.bno }"><button type="button" class="btn btn-danger">Delete</button></a>
	<br><hr>
	
	<!-- Comment 라인 -->
	<!-- 댓글 등록 라인 -->
<nav class="navbar bg-body-tertiary">
  <form class="container-fluid">
    <div class="input-group">
      <span class="input-group-text" id="cmtWriter">${ses.id }</span>
      <input type="text" id="cmtText" class="form-control" placeholder="Add Comment..." aria-label="comment" aria-describedby="cmtWriter">
      <button class="btn btn-outline-success" type="button" id="cmtAddBtn">Register</button>
    </div>
  </form>
</nav>
<hr>
	<!-- 댓글 표시 라인 -->
<div class="accordion" id="accordionPanelsStayOpenExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        cno, writer, reg_date + 수정,삭제 버튼
      </button>
    </h2>
    <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show">
      <div class="accordion-body">
        <strong>Comment</strong>
      </div>
    </div>
  </div>
</div>
	
</div> <!-- container-md 끝 -->
	
	<script type="text/javascript">
		const bnoVal = `<c:out value="${bvo.bno}"/>`;
	</script>
	<!-- servlet-context.xml를 보면 resources 맵핑이 되어 있음 -->
	<script src="/resources/js/boardComment.js"></script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
</body>
</html>