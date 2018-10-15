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
</head>
<body>
	<jsp:include page="memberMgm.jsp" />
	
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form method="post" action="updateMember.do">
					<h3 style="text-align: center">회원수정 페이지</h3>
					<br>
					<div class="form-group">
						<input type="text"
							class="form-control"  name="id"
							maxlength="20" value = "<%=request.getAttribute("id") %>" readonly = "readonly">
						</label>
					</div>

					<div class="form-group">
						<input type="password"
							class="form-control" placeholder="관리자 본인의 비밀번호(8~16자리)를 입력하세요." name="pw" minlength = "8"
							maxlength="16">						
					</div>
					
					<div class="form-group">
						<input type="password"
							class="form-control" placeholder="비밀번호 재입력" name="pwCheck" minlength = "8"
							maxlength="16">
						
					</div>

					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름"
							name="name" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="연락처"
							name="phone" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="주소"
							name="addr" maxlength="20">
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="나이"
							name="age" maxlength="20">
					</div>

<!-- 					<div class="form-group" style="text-align: center;"> -->
<!-- 						<div class="btn-group" data-toggle="buttons"> -->
<!-- 							<label class="btn btn-primary active"> <input -->
<!-- 								type="radio" name="p_gender" autocomplete="off" value="남자" -->
<!-- 								checked>남자 -->
<!-- 							</label> <label class="btn btn-primary"> <input type="radio" -->
<!-- 								name="p_gender" autocomplete="off" value="여자" checked>여자 -->
<!-- 							</label> -->
<!-- 						</div> -->
<!-- 					</div> -->

<!-- 					<div class="form-group" style="text-align: center;"> -->
<!-- 						<div class="btn-group" data-toggle="buttons"> -->
<!-- 							<label class="btn btn-primary active"> <input -->
<!-- 								type="radio" name="p_member" autocomplete="off" value="사용자" -->
<!-- 								checked>일반 모드 -->
<!-- 							</label> <label class="btn btn-primary"> <input type="radio" -->
<!-- 								name="p_admin" autocomplete="off" value="관리자" checked>관리자 -->
<!-- 								모드 -->
<!-- 							</label> -->
<!-- 						</div> -->
<!-- 					</div> -->

					<input type="submit" class="btn btn-primary form-control"
						value="확인"> <input type="reset" class="btn btn-primary form-control"
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


