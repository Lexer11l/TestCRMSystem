<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
	<title>Hello world company</title>
</head>

<body>
<form:form action="${pageContext.request.contextPath}/register/processRegistrationForm"
		   modelAttribute="crmUser"
		   class="form-horizontal">
	<c:if test="${registrationError != null}">
		<div class="alert alert-danger col-xs-offset-1 col-xs-10">
				${registrationError}
		</div>
	</c:if>
	<form:input path="username" placeholder="username" class="form-control" />
	<form:password path="password" placeholder="password" class="form-control" />
	<button type="submit" class="btn btn-primary">Register</button>
	<br>

	<a href="${pageContext.request.contextPath}/showMyLoginPage">Back to login form</a>

</form:form>

</body>

</html>










