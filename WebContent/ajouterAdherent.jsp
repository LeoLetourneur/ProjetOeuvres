<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<h1 align="center">Ajout d'un adhérent</h1>
	
		<form class="form-horizontal" method="post" action="Controleur?action=insererAdherent" onsubmit="checkFields()">
			<div class="form-group">
				<label for="nomAdherent" class="col-sm-2 control-label">Nom</label>
				<div class="col-sm-3">
					<input type="text" name="txtnom" class="form-control"
						id="nomAdherent" placeholder="Nom">
				</div>
			</div>
			<div class="form-group">
				<label for="prenomAdherent" class="col-sm-2 control-label">Prénom</label>
				<div class="col-sm-3">
					<input type="text" name="txtprenom" class="form-control"
						id="prenomAdherent" placeholder="Prénom">
				</div>
			</div>
			<div class="form-group">
				<label for="villeAdherent" class="col-sm-2 control-label">Ville</label>
				<div class="col-sm-3">
					<input type="text" name="txtville" class="form-control"
						id="villeAdherent" placeholder="Ville">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a type="button" name="btAnnuler" class="btn btn-danger" href="index.jsp">Annuler</a>
					<button type="submit" name="bt" class="btn btn-primary">Ajouter</button>
				</div>
			</div>
		</form>
	
    </jsp:body>
</t:layout>

