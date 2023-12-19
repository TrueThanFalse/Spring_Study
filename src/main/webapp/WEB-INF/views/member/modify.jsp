<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<div class="container-md">
		<h1>member modify.jsp</h1>
		<br>
		<form action="/member/edit" method="post">
			<div class="mb-3">
			  <label for="id" class="form-label">ID</label>
			  <input type="text" name="id" class="form-control" id="id" value="${ses.id }" readonly="readonly">
			</div>
			<div class="mb-3">
			  <label for="pw" class="form-label">PW</label>
			  <input type="password" name="pw" class="form-control" id="pw" placeholder="변경시에만 입력해주세요.">
			</div>
			<div class="mb-3">
			  <label for="name" class="form-label">Name</label>
			  <input type="text" name="name" class="form-control" id="name" value="${ses.name }">
			</div>
			<div class="mb-3">
			  <label for="email" class="form-label">Email</label>
			  <input type="email" name="email" class="form-control" id="email" value="${ses.email }">
			</div>
			<div class="mb-3">
			  <label for="home" class="form-label">Home</label>
			  <input type="text" name="home" class="form-control" id="home" value="${ses.home }">
			</div>
			<div class="mb-3">
			  <label for="age" class="form-label">Age</label>
			  <input type="text" name="age" class="form-control" id="age" value="${ses.age }">
			</div>
			<button type="submit" class="btn btn-primary">Edit</button>
			<a href="/"><button type="button" class="btn btn-primary">Exit</button></a>
			<a href="/member/withdrawal"><button type="button" class="btn btn-danger">Withdrawal</button></a>
		</form>
	</div>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>