<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, Ruby, PHP e muito mais -
	Casa do Código</title>
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
	<nav class="navbar navbar-inverse">
	<h1>Casa do Código</h1>

	</nav>
	<div class="container">
	<form:form action="${s:mvcUrl('PC#gravar').build().toUriString() }" method="post"
		commandName="produto" enctype="multipart/form-data">
		<div>
			<label>Título</label>
			<form:input path="titulo" cssClass="form-control"/>
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao"  cssClass="form-control"/>
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Páginas</label>
			<form:input path="paginas" cssClass="form-control"/>
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de Lançamento</label>
			<form:input path="dataLancamento" cssClass="form-control"/>
			<form:errors path="dataLancamento" />
		</div>
		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor" cssClass="form-control" />
				<form:hidden path="precos[${status.index}].tipo"
					value="${tipoPreco}" />
			</div>
		</c:forEach>
		<div>
			<label>Sumário</label> <input name="sumario" type="file" Class="form-control"/>
		</div>
		<button type="submit" class="btn btn-primary">Cadastrar</button>
	</form:form>
	</div>
</body>
</html>