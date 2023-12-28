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
<h1>list.jsp</h1>
<br>

<!-- 검색 라인 -->
<div>
	<form action="/board/list" method="get">
		<!-- BoardController는 PagingVO를 받아야 함 -->
		<input type="hidden" name="pageNo" value="1">
		<input type="hidden" name="qty" value="10">
		
		<div class="input-group mb-3">
		<select name="type" class="form-select" id="inputGroupSelect02">
			<!-- 검색 후 검색 값 유지를 위해 selected 삽입 로직 -->
			<option ${ph.pgvo.type == null ? 'selected' : '' }>Choose...</option>
			<option value="t" ${ph.pgvo.type eq 't' ? 'selected' : '' }>Title</option>
			<option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : '' }>Writer</option>
			<option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : '' }>Content</option>
			<option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : '' }>Title&Writer</option>
			<option value="tw" ${ph.pgvo.type eq 'tw' ? 'selected' : '' }>Title&Content</option>
			<option value="wc" ${ph.pgvo.type eq 'wc' ? 'selected' : '' }>Writer&Content</option>
			<option value="twc" ${ph.pgvo.type eq 'twc' ? 'selected' : '' }>All</option>
		</select>
		<input class="form-control me-2" type="search" placeholder="Search..." aria-label="Search" name="keyword" value="${ph.pgvo.keyword }">
      	<button type="submit" class="btn btn-primary position-relative">
      		Search
			<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">${ph.totalCount}</span>
		</button>
		</div>
	</form>
</div>
<!-- 검색 끝 -->

<!-- boardVO list 출력 라인 -->
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">title</th>
      <th scope="col">writer</th>
      <th scope="col">reg_date</th>
      <th scope="col">read_count</th>
    </tr>  
  <tbody>
  	<c:forEach items="${list }" var="bvo">
    <tr>
      <th scope="row">${bvo.bno }</th>
      <td>
      	<a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a>
      	<span class="badge bg-secondary">${bvo.commentCount }</span>
      	<span class="badge bg-secondary">${bvo.fileCount }</span>
      </td>
      <td>${bvo.writer }</td>
      <td>${bvo.reg_date }</td>
      <td>${bvo.read_count }</td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<!-- 페이지네이션 라인 -->
<nav aria-label="Page navigation example">
  <ul class="pagination">
  	<!-- prev -->
  	<c:if test="${ph.prev }">
    <li class="page-item">
   	  <!-- 쿼리스트링은 띄어쓰기 절대 금지 -->
      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    
    <!-- pageNo -->
    <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
    </c:forEach>
    
    <!-- next -->
    <c:if test="${ph.next }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
  </ul>
</nav>
<!-- 페이지네이션 끝 -->
<a href="/board/list"><button type="button" class="btn btn-primary position-relative">All list</button></a>
</div>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
	
	<script type="text/javascript">
		const isDel = `<c:out value="${isDel}"/>`;
		if(isDel == 1){
			alert("게시글이 삭제되었습니다.");
		}
	</script>
</body>
</html>