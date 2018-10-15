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
	<jsp:include page="adminBookMgm.jsp" />
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">

				<h3 style="text-align: center">
					<strong>조회된 도서정보</strong>
				</h3>
				<br>
				<%
					List<book> bookList = (List<book>) request.getAttribute("bookList");
				%>
				<div class="table-responsive">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>도서명</th>
								<th>저자</th>
								<th>장르</th>
								<th>입고일자</th>
								<th>도서번호</th>
								<th>대여가능 여부</th>
								<th>도서 출고</th>
							</tr>
							<%
								for (book b : bookList) {
							%>
							<tr>
								<td><%=b.getB_name()%></td>
								<td><%=b.getB_writer()%></td>
								<td><%=b.getB_sort()%></td>
								<td><%=b.getB_inputDate()%></td>
								<td><%=b.getB_num()%></td>
								<td><%=b.getB_lendStatus()%></td>
								<%if(b.getB_lendStatus().equals("대여가능")){ %>
								<td><input type = "button" name = "delete" value = "출고" 
								onclick = "location.href='deleteBookForm.go?b_num=<%=b.getB_num()%>'"></td>
								<%} %>
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