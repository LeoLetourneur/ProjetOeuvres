<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<h1 align="center">${action} un adhérent</h1>
		
		<form class="form-horizontal" method="post" action="Adherent?action=insererAdherent" onsubmit="return checkFields()">
			
			<input type="hidden" name="idAdherent" class="form-control" id="idAdherent" value="${adherent.idAdherent}">
			
			<div class="form-group">
				<label for="nomAdherent" class="col-sm-2 control-label">Nom</label>
				<div class="col-sm-3">
					<input type="text" name="txtnom" class="form-control"
						id="nomAdherent" placeholder="Nom" value="${adherent.nomAdherent}">
				</div>
			</div>
			<div class="form-group">
				<label for="prenomAdherent" class="col-sm-2 control-label">Prénom</label>
				<div class="col-sm-3">
					<input type="text" name="txtprenom" class="form-control"
						id="prenomAdherent" placeholder="Prénom" value="${adherent.prenomAdherent}">
				</div>
			</div>
			<div class="form-group">
				<label for="villeAdherent" class="col-sm-2 control-label">Ville</label>
				<div class="col-sm-3">
					<input type="text" name="txtville" class="form-control"
						id="villeAdherent" placeholder="Ville" value="${adherent.villeAdherent}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a type="button" name="btAnnuler" class="btn btn-danger" href="Adherent?action=listeAdherent">Annuler</a>
					<button type="submit" name="bt" class="btn btn-primary">${action}</button>
				</div>
			</div>
		</form>
	
    </jsp:body>
</t:layout>

