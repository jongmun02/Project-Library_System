<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>도서관리 프로젝트</title>
</head>

<body>

	
<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="first.jsp">도서관리프로젝트</a>
		</div>
		<div class="callapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="userBookMgm.jsp">회원도서정보</a></li>

			</ul>
		</div>

	</nav>

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">

				<h3 style="text-align: center">
					<strong>사용자용 도서관리시스템</strong>
				</h3>


				<br>
				<div class="input-group">

					<div class="btn-group">
						
					


						</div>
						<button type="button" class="btn btn-primary"
							onclick="location.href = 'memberBook.do?id=<%=session.getAttribute("id") %>'">대여 정보</button>
						<button type="button" class="btn btn-primary"
							onclick="location.href = 'Logout.go'">로그아웃</button>
						<button type="button" class="btn btn-primary"
							onclick="location.href = 'selfDeleteForm.go'">회원탈퇴</button>

						<form action="selectOneBook3.go">
						<div class="input-group-btn">
						<input type="text" class="form-control"
							placeholder="검색할 도서를 입력하세요." name = "search">

						<input class="btn btn-default" type="submit" value = "검색">
				
							</div>
						</form>

						<button class="btn btn-default" name="allSearch" value="전체조회"
							onclick="location.href = 'selectAllBook3.go'" role="button">전체조회</button>
					</div>
				</div>



			</div>
		</div>
		<div class="col-lg-4"></div>




	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js.bootstrap.js"></script>
</body>
</html>