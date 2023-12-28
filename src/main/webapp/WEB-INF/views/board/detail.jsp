<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<!-- c:set을 활용하여 코드 길이를 줄일 수 있음 -->
<c:set value="${boardDTO.bvo }" var="bvo"/>
<table class="table">
	<tr>
	<div class="mb-3">
	  <label for="title" class="form-label">Title</label>
	  <input type="text" name="title" class="form-control" id="title" placeholder="Title..." readonly="readonly" value="${boardDTO.bvo.title }">
	  <%-- c:set 설정으로 value에 boardDTO.bvo.title OR bvo.title 모두 사용해도 됨 --%>
	</div>
	</tr>
	<tr>
	<div class="mb-3">
	  <label for="writer" class="form-label">Writer</label>
	  <input type="text" name="writer" class="form-control" id="writer" placeholder="Writer..." readonly="readonly" value="${bvo.writer }">
	</div>
	</tr>
	
	<!-- 파일 표시 라인 -->
	<c:set value="${boardDTO.flist }" var="flist"/>
	<div class="mb-3">
		<ul class="list-group">
			<!-- 파일의 개수만큼 li를 추가하여 파일을 표시, 타입이 1인 경우만 표시(이미지 타입일 때만 표시) -->
			<!-- 
				하나의 li -> div를 넣어서 img 그림 표시
							div를 하나 더 넣어서 파일이름, 작성일, span태그로 size
			-->
			<!-- 파일 리스트 중 하나만 가져와서 fvo로 저장 -->
			<c:forEach items="${flist }" var="fvo">
				<li class="list-group-item">
					<c:choose>
						<c:when test="${fvo.file_type > 0 }">
							<div>
								<!-- /upload/ : servlet-context 로케이션 설정에서 경로를 미리 지정해두었음 -->
								<img alt="" src="/upload/${fn:replace(fvo.save_dir, '\\', '/')}/${fvo.uuid}_th_${fvo.file_name}">
							</div>
						</c:when>
						<c:otherwise>
							<div>
								<!-- 아이콘 하나 가져와서 넣기 -->
								<i class="bi bi-file-excel-fill"></i>
							</div>
						</c:otherwise>
					</c:choose>
					<!-- div를 하나 더 넣어서 파일이름, 작성일, span태그로 size -->
					<div class="badge bg-primary text-wrap">
						${fvo.file_name }<br>
						${fvo.reg_date }
					</div>
					<br>
					<span class="badge text-bg-primary">${fvo.file_size }Byte</span>
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 파일 표시 라인 끝 -->
	
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
<!-- 이 내부 내용은 샘플임, 실제로는 JS에서 for문으로 작성되어 출력됨 -->
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
        cno, writer, reg_date
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
	<script type="text/javascript">
		PrintCommentList(bnoVal);
	</script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
</body>
</html>