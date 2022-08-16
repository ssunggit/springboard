<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:if test="${ not empty loginVO }">
		${ loginVO.name }님 반가워요.
	</c:if>
	
	<table class="table table-striped table-hover" style="width: 80%; margin: auto; text-align: center;">
		<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
		</tr>
		</thead>
		
	<%-- 	<tbody>
			<c:forEach items="${ boardlist }" var="board">
				<tr>
					<td>${board.no}</td>
					<td> <a href="${ pageContext.request.contextPath }/board/detail?boardNo=${board.no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.regDate}</td>
				</tr>
			</c:forEach>
		</tbody> --%>
		
		<tbody>
			<c:forEach items="${ boardlist }" var="board">
				<tr>
					<td>${board.no}</td>
					<td> <a href="${ pageContext.request.contextPath }/board/detail/${board.no}">${board.title}</a></td>
					<td>${board.writer}</td>
					<td>${board.regDate}</td>
				</tr>
			</c:forEach>
		</tbody>
		
	</table>	
	
</body>
</html>