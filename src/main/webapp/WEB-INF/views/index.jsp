<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%@ page session="false" %> --%>

<jsp:include page="./layout/header.jsp"></jsp:include>

<h1>
	<%-- !DOCTYPE html,html,head,body 등의 태그들을 layout의 header,footer에서 처리해 줌 --%>
	Hello world!
</h1>

<c:if test="${ses.id ne null }">
	<div>
		<p>${ses.id }님 환영합니다.</p>
		<p>last_login : ${ses.last_login }</p>
	</div>
</c:if>

<script type="text/javascript">
	const msg_login = `<c:out value="${msg_login}"/>`;
	const msg_logout = `<c:out value="${msg_logout}"/>`;
	const msg_edit = `<c:out value="${msg_edit}"/>`;
	const msg_withdrawal = `<c:out value="${msg_withdrawal}"/>`;
	
	if(msg_login == -1){
		alert("로그인 실패~!");
	}
	if(msg_edit == 1){
		alert("회원 정보 수정 성공~! 다시 로그인 해주세요.");
	}
	if(msg_withdrawal == 1){
		alert("회원 탈퇴 성공~!");
	}
	if(msg_logout == 1){
		alert("로그아웃 되었습니다.~!");
	}
</script>

<jsp:include page="./layout/footer.jsp"></jsp:include>