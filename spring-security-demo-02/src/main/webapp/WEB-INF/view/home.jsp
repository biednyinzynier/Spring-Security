<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Burak Selvi Home</title>
</head>
<body>
	<h2>MY HOME PAGE</h2>
	<hr>
	<p>
	<i>THIS IS HOME PAGE FOR DIFFERENT ROLES</i>
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	
	<p>
		<b>User:</b> <security:authentication property="principal.username"/><br><br>
		<b>Role(s):</b> <security:authentication property="principal.authorities"/>
	</p>
	
	<security:authorize access="hasRole('MANAGER')">
	
		<!-- Add a link to point to /leaders ... this is for the managers -->
		
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			(Only for Managers)
		</p>

	</security:authorize>	
	
	
	<security:authorize access="hasRole('ADMIN')">  

		<!-- Add a link to point to /systems ... this is for the admins -->
		
		<p>
			<a href="${pageContext.request.contextPath}/systems">IT Systems Meeting</a>
			(Only for Admins)
		</p>
	
	</security:authorize>
	
	<hr>
	
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>	
	
</body>
</html>