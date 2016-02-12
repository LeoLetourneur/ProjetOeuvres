<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
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
				<th>Actions</th>
			</tr>
	
			<c:forEach items="${mesAdherents}" var="item">
				<tr>
					<td>${item.idAdherent}</td>
					<td>${item.nomAdherent}</td>
					<td>${item.prenomAdherent}</td>
	                <td>${item.villeAdherent}</td>
	                <td>
	                	<a type="button" class="btn btn-primary" data-id="${item.idAdherent}" href="#">Modifier</a>
	                	<a type="button" class="btn btn-danger" data-id="${item.idAdherent}" href="#">Supprimer</a>
                	</td>
				</tr>
			</c:forEach>
		</table>
		
    </jsp:body>
</t:layout>
