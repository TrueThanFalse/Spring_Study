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
		<h1>member Login.jsp</h1>
		
		<form action="/member/login" method="post">
			<div class="mb-3">
			  <label for="id" class="form-label">ID</label>
			  <input type="text" name="id" class="form-control" id="id" placeholder="ID...">
			</div>
			<div class="mb-3">
			  <label for="pw" class="form-label">PassWord</label>
			  <input type="password" name="pw" class="form-control" id="pw" placeholder="PassWord...">
			</div>
			<button type="submit" class="btn btn-primary">Login</button>
		</form>
	</div>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>

</body>
</html>