<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script src="lib/jquery/jquery-1.11.2.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<title>Affichage de tous les adhérents</title>
</head>
<body>
	<p>
		<a type="button" name="btnReturn" class="btn btn-warning" href="index.jsp">Retour accueil</a>
	</p>
	<p align="center">
		Listing des adhérents
	</p>

	<table class="table table-hovers">
		<tr>
			<th>Numero</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Ville</th>

		</tr>

		<c:forEach items="${mesAdherents}" var="item">
			<tr>
				<td>${item.idAdherent}</td>
				<td>${item.nomAdherent}</td>
				<td>${item.prenomAdherent}</td>
                <td>${item.villeAdherent}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>