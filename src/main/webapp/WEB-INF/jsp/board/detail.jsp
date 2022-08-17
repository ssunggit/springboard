<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<jsp:include page="/WEB-INF/jsp/include/topMenu.jsp"></jsp:include>
	</header>

	<h1 style="text-align: center;">상세게시글 입니다</h1>
	
	<table class="table table-striped table-hover" style="width: 80%; margin: auto; text-align: center;">
		<tr>
			<th>제목</th>
			<td>${board.title }</td>
		</tr>
		
		<tr>
			<th>작성자</th>
			<td>${board.writer }</td>
		</tr>
		
		<tr>
			<th>등록일</th>
			<td>${board.regDate }</td>
		</tr>
		
		<tr>
			<th>내용</th>
			<td>${board.content }</td>
		</tr>	
	</table>
</body>
</html>