<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<h1 align="center">${action} une oeuvre</h1>
		
		<form class="form-horizontal" method="post" action="OeuvreVente?action=inserer">
			
			<input type="hidden" name="idOeuvre" class="form-control" id="idOeuvre" value="${oeuvre.idOeuvre}">
			
			<div class="form-group">
				<label for="titreOeuvre" class="col-sm-2 control-label">Titre</label>
				<div class="col-sm-3">
					<input type="text" name="txtTitre" class="form-control"
						id="titreOeuvre" placeholder="Titre" value="${oeuvre.titreOeuvre}">
				</div>
			</div>
			<div class="form-group">
				<label for="prixOeuvrevente" class="col-sm-2 control-label">Prix</label>
				<div class="col-sm-3">
					<input type="number" min="0" step="0.01" value="${oeuvre.prixOeuvrevente}" 
						name="txtPrix" class="form-control" id="prixOeuvrevente" />
				</div>
			</div>
			<div class="form-group">
				<label for="idProprietaire" class="col-sm-2 control-label">Propriétaire</label>
				<div class="col-sm-3">
					<select class="form-control" name="txtProprietaire" id="idProprietaire">
					<c:forEach items="${proprietaires}" var="item">
						<option value="${item.idProprietaire}">${item.nomProprietaire} ${item.prenomProprietaire}</option>
					</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a type="button" class="btn btn-danger" href="OeuvreVente?action=liste">Annuler</a>
					<button type="submit" name="bt" class="btn btn-primary">${action}</button>
				</div>
			</div>
		</form>
	
    </jsp:body>
</t:layout>

