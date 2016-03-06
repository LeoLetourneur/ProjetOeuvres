<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:form classe="Reservation" 
    				titre="${action} une réservation" 
    				action="${action}">
			<jsp:body>
		
				<input type="hidden" name="idOeuvre" class="form-control" id="idOeuvre" value="${oeuvre.idOeuvre}">
				
				<div class="form-group">
					<label for="titreOeuvre" class="col-sm-2 control-label">Titre</label>
					<div class="col-sm-3">
						<label class="form-control" id="titreOeuvre">${oeuvre.titreOeuvre}</label>
					</div>
				</div>
				<div class="form-group">
					<label for="prixOeuvrevente" class="col-sm-2 control-label">Prix</label>
					<div class="col-sm-3">
						<label class="form-control" id="prixOeuvrevente">${oeuvre.prixOeuvrevente}</label>
					</div>
				</div>
				<div class="form-group">
					<label for="idProprietaire" class="col-sm-2 control-label">Propriétaire</label>
					<div class="col-sm-3">
						<label class="form-control" id="idProprietaire">
						${oeuvre.proprietaire.nomProprietaire} ${oeuvre.proprietaire.prenomProprietaire}</label>
					</div>
				</div>
				<div class="form-group">
					<label for="datepicker" class="col-sm-2 control-label">Date</label>
					<div class="col-sm-3">
						<input type="text" name="txtDate" id="datepicker" value="${reservation.date}">
					</div>
				</div>
				<div class="form-group">
					<label for="adherents" class="col-sm-2 control-label">Adhérent</label>
					<div class="col-sm-3">
						<select class="form-control" name="idAdherent" id="adherents">
						<c:forEach items="${adherents}" var="item">
							<option value="${item.idAdherent}">${item.nomAdherent} ${item.prenomAdherent}</option>
						</c:forEach>
						</select>
					</div>
				</div>
			
			</jsp:body>
		</t:form>
	
    </jsp:body>
</t:layout>

