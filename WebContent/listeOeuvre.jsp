<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<div>
			<a type="button" class="btn btn-warning" href="index.jsp">
				<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span> Retour accueil
			</a>
			<a type="button" class="btn btn-success" href="Controleur?action=ajouterOeuvre">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Ajouter une oeuvre
			</a>
		</div>
		
		<p align="center">
			Listing des oeuvres
		</p>
	
		<table class="table table-hovers">
			<tr>
				<th>Numero</th>
				<th>Titre</th>
				<th>Etat</th>
				<th>Prix</th>
				<th>Propriétaire</th>
				<th>Actions</th>
			</tr>
	
			<c:forEach items="${oeuvres}" var="item">
				<tr>
					<td>${item.idOeuvrevente}</td>
					<td>${item.titreOeuvrevente}</td>
					<td>${item.etatOeuvrevente}</td>
	                <td>${item.prixOeuvrevente}</td>
	                <td>${item.proprietaire.nomProprietaire} ${item.proprietaire.prenomProprietaire}</td>
	                <td>
	                	<a type="button" class="btn btn-primary" href="Controleur?action=modifierOeuvre&idOeuvre=${item.idOeuvrevente}">
	               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
	               		</a>
	                	<a type="button" class="btn btn-danger" href="Controleur?action=supprimerOeuvre&idOeuvre=${item.idOeuvrevente}">
	                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
	                	</a>
                	</td>
				</tr>
			</c:forEach>
		</table>
		
    </jsp:body>
</t:layout>
