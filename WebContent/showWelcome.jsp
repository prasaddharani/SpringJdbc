<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Spring MVC - Storing Custom Object in Session Example</title>
</head>
<body>
	<h2>Spring MVC - Storing Custom Object in Session Example</h2>
	<form>
	<p><a href="create.jsp">Click for Create</a></p>
	<table border="1">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Email</th>
				<th>Course</th>
			</tr>
			<tr>
				<td>${sessionScope.studentObject.id}</td>
				<td>${sessionScope.studentObject.name}</td>
				<td>${sessionScope.studentObject.email}</td>
				<td>${sessionScope.studentObject.course}</td>
			</tr>
		</table>
	</form>
</body>
</html>