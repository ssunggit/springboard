<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:forEach items="${ boardlist }" var="board">
	NO : ${ board.no } <br> title : ${ board.title } <br> content: ${ board.content }   
</c:forEach>

</body>
</html>