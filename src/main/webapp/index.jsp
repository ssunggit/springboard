<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	index.jsp 입니다.

	<a href="${pageContext.request.contextPath }/login">로그인</a>
	<a href="${pageContext.request.contextPath }/logout">로그아웃</a>
	<a href="${pageContext.request.contextPath }/signup">회원가입</a>

	<a href="${pageContext.request.contextPath }/board">게시판</a>
	<a href="${pageContext.request.contextPath }/board/write">새글쓰기</a>

</body>
</html>