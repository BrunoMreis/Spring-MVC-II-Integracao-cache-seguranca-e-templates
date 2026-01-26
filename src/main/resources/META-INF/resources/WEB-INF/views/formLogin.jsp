<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><s:message code="login.title" /></title>
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<style type="text/css">
body {
	padding: 60px 0px;
}
</style>
</head>
<body>
	<div class="container">
		<h1><s:message code="login.header" /></h1>
		<c:if test="${not empty param.error}">
			<div class="alert alert-danger">
				<s:message code="login.error" />
			</div>
		</c:if>
		<form:form servletRelativeAction="/login" method="POST">
			<div class="form-group">
				<label><s:message code="login.label.email" /></label> <input type="text" name="username"
					class="form-control" />
			</div>
			<div class="form-group">
				<label><s:message code="login.label.password" /></label> <input type="password" name="password"
					class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary"><s:message code="login.button.login" /></button>
		</form:form>
	</div>
</body>
</html>
