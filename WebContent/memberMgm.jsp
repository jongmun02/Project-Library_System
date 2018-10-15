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

	<nav class="navbar navbar-default">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="first.do">도서관리프로젝트</a>
		</div>
		<div class="callapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="main.do">관리자메인</a></li>
				<li><a href="adminBookMgm.go">도서관리페이지</a></li>
				<li><a href="memberMgm.do">회원관리페이지</a></li>
			</ul>
		</div>

	</nav>

<body>
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form method="post" action="selectOneMember.do">
					<h3 style="text-align: center">
						<strong>회원관리 페이지</strong>
					</h3>
					<br>
					<div class="input-group">

						<div class="btn-group">
							
							
							
							<br><br>
							<div class="input-group-btn">
								<input type="text" class="form-control"
									placeholder="검색할 회원을 입력하세요." name="search" size = "70"> 
									<input class="btn btn-primary form-control" type="submit" value="검색">

							</div>
						</div>
					</div>
					<br>

				</form>

				<button class="btn btn-primary form-control" name="selectAllMember"
					value="전체조회" onclick="location.href = 'selectAllMember.do'"
					role="button">전체조회</button>
				<button class="btn btn-primary form-control" name="back"
					value="뒤로가기" onclick="location.href = 'main.do'" role="button">뒤로가기</button>

			</div>
		</div>
		<div class="col-lg-4"></div>




	</div>




	<!-- jQuery library -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

	<!-- Latest compiled JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js.bootstrap.js"></script>
</body>
</html>