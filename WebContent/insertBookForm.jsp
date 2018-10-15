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
	<jsp:include page="adminBookMgm.jsp" />
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form method="post" action="insertBook.go">
					<h3 style="text-align: center">
						<strong>도서입고 페이지</strong>
					</h3>
					<br>

					<div class="form-group">
						<input type="text" class="form-control" placeholder="도서명"
							name="b_name" maxlength="20">
					</div>

					<div class="form-group">
						<input type="text" class="form-control" placeholder="저자명"
							name="b_writer" maxlength="20">
					</div>
					<div class="form-group">
						장르선택 : <select name="b_sort" class="form-control">
							<option value="총류">총류</option>
							<option value="인문학">인문학</option>
							<option value="종교">종교</option>
							<option value="사회과학">사회과학</option>
							<option value="자연과학">자연과학</option>
							<option value="기술과학">기술과학</option>
							<option value="예술">예술</option>
							<option value="언어">언어</option>
							<option value="문학">문학</option>
							<option value="역사">역사</option>
							<option value="기타">기타</option>
						</select><br>
					</div>

					<input type="submit" class="btn btn-primary form-control"
						value="입고"> <input type="reset"
						class="btn btn-primary form-control" value="재입력">

				</form>
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