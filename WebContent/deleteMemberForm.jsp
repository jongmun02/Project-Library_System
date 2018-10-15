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

	<jsp:include page="memberMgm.jsp" />
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">

				<form method="post" action="deleteMember.do">
					<h3 style="text-align: center">
						<strong>회원삭제 관리</strong>
					</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="삭제하고자 하는 사용자의 ID를 입력하세요."
							name="id" maxlength="20" value = "<%=request.getAttribute("id") %>" readonly = "readonly">
					</div>
					<div class="form-group">
						<input type="password" class="form-control"
							placeholder="관리자 본인의 비밀번호(8~16자리)를 입력하세요." name="pw"
							minlength="8" maxlength="20">

					</div>
					<div class="form-group">
						<input type="password" class="form-control"
							placeholder="비밀번호를 한번 더 입력하세요." name="pwCheck" minlength="8"
							maxlength="20">
					</div>
					<input type="submit" class="btn btn-primary form-control"
						value="삭제">
					<input type="reset" class="btn btn-primary form-control"
						value="다시 입력">
						

				</form>
				<button class="btn btn-primary form-control" name="back"
						value="뒤로가기" onclick="location.href = 'selectAllMember.do'" role="button">뒤로가기</button>
						
			</div>
		</div>
		<div class="col-lg-4"></div>

	</div>

	<!-- jQuery (부트스트랩의 자바스크립트 플러그인을 위해 필요합니다) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<!-- 모든 컴파일된 플러그인을 포함합니다 (아래), 원하지 않는다면 필요한 각각의 파일을 포함하세요 -->
	<script src="js/bootstrap.min.js"></script>

</body>
</html>







