<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="Model.member"%>
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
	<jsp:include page="memberMgm.jsp" />

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
			
			<h3 style="text-align: center">
					<strong>조회된 회원정보</strong>
				</h3><br>
				
				<%
					if (request.getAttribute("search") != null) {
						member member = (member) request.getAttribute("search");
						String gender = Boolean.toString(member.isP_gender());
						String admin = Boolean.toString(member.isP_admin());
						if (member.isP_gender()) {
							gender = "남";
						} else {
							gender = "여";
						}
						if (member.isP_admin()) {
							admin = "관리자";
						} else {
							admin = "사용자";
						}
				%>

				<div class="table-responsive">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th scope="col">No</th>
								<th scope="col">ID</th>
								<th scope="col">회원명</th>
								<th scope="col">연락처</th>
								<th scope="col">주소</th>
								<th scope="col">연령</th>
								<th scope="col">성별</th>
								<th scope="col">회원유형</th>
								<th>정보수정</th>
								<th>회원삭제</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td><%=member.getP_num()%></td>
								<td
									onclick="location.href = 'memberBook.do?id=<%=member.getP_id()%>'"><%=member.getP_id()%></td>
								<td><%=member.getP_name()%></td>
								<td><%=member.getP_phone()%></td>
								<td><%=member.getP_addr()%></td>
								<td><%=member.getP_age()%></td>
								<td><%=gender%></td>
								<td><%=admin%></td>
								<td><input type = "button" name = "update" value = "수정" 
								onclick = "location.href='updateMemberForm.do?id=<%=member.getP_id()%>'"></td>
								<td><input type = "button" name = "delete" value = "삭제" 
								onclick = "location.href='deleteMemberForm.do?id=<%=member.getP_id()%>'"></td>
						</tbody>
					</table>
					<%
						} else {
					%>
					<%=request.getAttribute("msg")%>
					<%
						}
					%>
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