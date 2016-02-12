<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<div>
			<a type="button" class="btn btn-warning" href="index.jsp">Retour accueil</a>
			<a type="button" class="btn btn-success" href="Controleur?action=ajouterAdherent">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajout un adhérent
			</a>
		</div>
		
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
	                	<a type="button" class="btn btn-primary" href="Controleur?action=modifierAdherent&idAdherent=${item.idAdherent}">Modifier</a>
	                	<a type="button" class="btn btn-danger" href="Controleur?action=supprimerAdherent&idAdherent=${item.idAdherent}">Supprimer</a>
                	</td>
				</tr>
			</c:forEach>
		</table>
		
    </jsp:body>
</t:layout>
