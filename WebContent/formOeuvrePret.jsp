<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<h1 align="center">${action} une oeuvre prêt</h1>
		
		<form class="form-horizontal" method="post" action="OeuvrePret?action=inserer">
			
			<input type="hidden" name="idOeuvrePret" class="form-control" id="idOeuvrePret" value="${oeuvrePret.idOeuvrepret}">
			
			<div class="form-group">
				<label for="titreOeuvrePret" class="col-sm-2 control-label">Titre</label>
				<div class="col-sm-3">
					<input type="text" name="txtTitre" class="form-control"
						id="titreOeuvrePret" placeholder="Titre" value="${oeuvrePret.titreOeuvrepret}">
				</div>
			</div>
			<div class="form-group">
				<label for="proprietaire" class="col-sm-2 control-label">Proprietaire</label>
				<div class="col-sm-3">
					<select></select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<a type="button" class="btn btn-danger" href="OeuvrePret?action=liste">Annuler</a>
					<button type="submit" name="bt" class="btn btn-primary">${action}</button>
				</div>
			</div>
		</form>
	
    </jsp:body>
</t:layout>

