<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form action="${pageContext.request.contextPath }/signup" method="post" modelAttribute="memberVO">
		<table>
			<tr>
				<th>ID</th>
				<td>
					<form:input path="id"/>
					<form:errors path="id"></form:errors>
				</td>
			</tr>
			<tr>
				<th>PASSWORD</th>
					<td>
						<form:input path="password"/>
						<form:errors path="password"></form:errors>
					</td>
			</tr>
			<tr>
				<th>NAME</th>
					<td>
						<form:input path="name"/>
						<form:errors path="name"></form:errors>
					</td>
			</tr>
		</table>
		<input type="submit" value="회원가입">
	</form:form>

</body>
</html>