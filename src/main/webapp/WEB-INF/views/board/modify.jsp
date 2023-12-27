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
<h1>board Modify.jsp</h1>
<c:set value="${boardDTO.bvo }" var="bvo"/>
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
	<tr>
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
					<span class="badge text-bg-primary">${fvo.file_size }Byte</span>
					<button type="button" class="file-x" data-uuid="${fvo.uuid }">X</button>
					<!-- X 버튼은 비동기로 버튼을 누르면 uuid와 비교하여 DB와 파일을 저장하는 로컬 폴더에서 삭제하면 됨 -->
				</li>
			</c:forEach>
		</ul>
	</div>
	<!-- 수정 파일 등록 라인 -->
	<!-- 이미지 파일은 수정의 개념이 아니라 삭제 후 다시 넣어주는 것임 -->
	<!-- register.jsp에서 file upload 관련 입력 라인 복사 -->
	<div class="mb-3">
	  <label for="file" class="form-label">files...</label>
	  <input type="file" name="files" class="form-control" id="file" multiple="multiple" style="display: none">
	  <br>
	  <button type="button" class="btn btn-primary" id="trigger">fileUpload</button>
	</div>
	<!-- 파일 목록 표기 라인 -->
	<div class="mb-3" id="fileZone"></div>
	
	</tr>
	<tr>
	<!-- file 업로드를 위해서 id="regBtn" 추가 -->
	<button type="submit" class="btn btn-primary" id="regBtn">Edit</button>
	<a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
	<input type="hidden" name="bno" value="${bvo.bno }"> 
	</tr>
</table>
</div>
</form>
	
	<!-- file 업로드를 위해서 register.jsp에서 js 복사 -->
	<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
	<script type="text/javascript" src="/resources/js/boardModify.js"></script>
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
</body>
</html>