<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="Model.member"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- bootstrap -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Insert title here</title>
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
				<h5>
					<strong> ■ ID값을 클릭하면 사용자의 도서 대여정보 확인 가능 </strong>
				</h5>
				<%
					List<member> list = (List<member>) request.getAttribute("list");
				%>
				<div class="table-responsive">
					<table class="table table-bordered">
						<tbody>
							<tr>
								<th>ID</th>
								<th>회원명</th>
								<th>연락처</th>
								<th>주소</th>
								<th>연령</th>
								<th>성별</th>
								<th>회원유형</th>
								<th>정보수정</th>
								<th>회원삭제</th>
							</tr>
							<%
								for (member m : list) {
									if (!m.isP_admin() || session.getAttribute("id").equals(m.getP_id())) {
							%>
							<tr>
								<td
									onclick="location.href = 'memberBook.do?id=<%=m.getP_id()%>'"><%=m.getP_id()%></td>
								<td><%=m.getP_name()%></td>
								<td><%=m.getP_phone()%></td>
								<td><%=m.getP_addr()%></td>
								<td><%=m.getP_age()%></td>
								<%
									if (m.isP_gender()) {
												String gender = Boolean.toString(m.isP_gender());
												gender = "남";
								%>
								<td><%=gender%></td>
								<%
									} else {
												String gender = Boolean.toString(m.isP_gender());
												gender = "여";
								%>
								<td><%=gender%></td>
								<%
									}
								%>
								<%
									if (m.isP_admin()) {
												String admin = Boolean.toString(m.isP_admin());
												admin = "관리자";
								%>
								<td><%=admin%></td>
								<%
									} else {
												String admin = Boolean.toString(m.isP_admin());
												admin = "사용자";
								%>
								<td><%=admin%></td>
								<%
									}
								%>
								<td><input type = "button" name = "update" value = "수정" 
								onclick = "location.href='updateMemberForm.do?id=<%=m.getP_id()%>'"></td>
								<td><input type = "button" name = "delete" value = "삭제" 
								onclick = "location.href='deleteMemberForm.do?id=<%=m.getP_id()%>'"></td>
							</tr>
							<%
								}
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