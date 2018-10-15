<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Model.book"%>
<%@page import="java.util.List"%>
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
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">접속하기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li class="active"><a href="loginForm.jsp">로그인</a></li>
						<li><a href="joinForm.jsp">회원가입</a></li>
						<li><a href="updateMemberForm.jsp">회원정보수정</a></li>
						<li><a href="selfDelete.go">회원탈퇴</a></li>

					</ul></li>
			</ul>
		</div>

	</nav>


	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">

				<h3 style="text-align: center">
					<strong><%=request.getAttribute("id")%>님의 도서 대여 정보</strong>
				</h3>
				<br>
				<div class="input-group-btn">
					<label for="disabledSelect"> <%=request.getAttribute("id")%>님의
						대여정보 <%List<book> bookList = (List<book>) request.getAttribute("bookList");%>

					</label>

				</div>
				<br>

				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th scope="col">No</th>
								<th scope="col">도서명</th>
								<th scope="col">저자</th>
								<th scope="col">대여일</th>
								<th scope="col">반납일</th>
								<th scope="col">장르</th>
								<th scope="col">고유번호</th>
								<th scope="col">반납여부</th>
								<th scope="col">연체일수</th>

							</tr>
						</thead>
						<tbody>
							<%
								for (book b : bookList) {
							%>
							<tr>
								<th scope="row">1</th>
								<td><%=b.getB_name()%></td>
								<td><%=b.getB_writer()%></td>
								<td><%=b.getB_lendDate()%></td>
								<td><%=b.getB_returnDate()%></td>
								<td><%=b.getB_sort()%></td>
								<td><%=b.getB_num()%></td>
								<td><%=b.getB_returnStatus()%></td>
								<td><%=b.getB_overdue() %></td>
							</tr>
							<%
								}
							%>

						</tbody>
					</table>
				</div>

			</div>
		</div>
		<div class="col-lg-4"></div>
		<button class="btn btn-primary form-control" name="back"
					value="메인으로" onclick="location.href = 'adminCheck.do'" role="button">메인으로</button>
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