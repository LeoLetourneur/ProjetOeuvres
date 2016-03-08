<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:form classe="OeuvreVente" 
    				titre="${action} une oeuvre vente" 
    				action="${action}">
			<jsp:body>
				<input type="hidden" name="idOeuvre" class="form-control" id="idOeuvre" value="${oeuvre.idOeuvre}">
				
				<div class="form-group">
					<label for="titreOeuvre" class="col-sm-2 control-label">Titre</label>
					<div class="col-sm-10">
						<input type="text" name="txtTitre" class="form-control"
							id="titreOeuvre" placeholder="Titre" value="${oeuvre.titreOeuvre}">
					</div>
				</div>
				<div class="form-group">
					<label for="prixOeuvrevente" class="col-sm-2 control-label">Prix</label>
					<div class="col-sm-10">
						<input type="number" min="0" step="0.01" value="${oeuvre.prixOeuvrevente}" 
							name="txtPrix" class="form-control" id="txtPrix" />
					</div>
				</div>
				<div class="form-group">
					<label for="idProprietaire" class="col-sm-2 control-label">Propriétaire</label>
					<div class="col-sm-10">
						<select class="form-control" name="txtProprietaire" id="idProprietaire">
						<c:forEach items="${proprietaires}" var="item">
							<option value="${item.idProprietaire}">${item.nomProprietaire} ${item.prenomProprietaire}</option>
						</c:forEach>
						</select>
					</div>
				</div>
			
			</jsp:body>
		</t:form>
		
    </jsp:body>
</t:layout>

