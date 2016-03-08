<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:form classe="Adherent" 
    				titre="${action} un adhérent" 
    				action="${action}">
			<jsp:body>
			
				<input type="hidden" name="idAdherent" class="form-control" id="idAdherent" value="${adherent.idAdherent}">
				
				<div class="form-group">
					<label for="nomAdherent" class="col-sm-2 control-label">Nom</label>
					<div class="col-sm-10">
						<input type="text" name="txtnom" class="form-control"
							id="nomAdherent" placeholder="Nom" value="${adherent.nomAdherent}">
					</div>
				</div>
				<div class="form-group">
					<label for="prenomAdherent" class="col-sm-2 control-label">Prénom</label>
					<div class="col-sm-10">
						<input type="text" name="txtprenom" class="form-control"
							id="prenomAdherent" placeholder="Prénom" value="${adherent.prenomAdherent}">
					</div>
				</div>
				<div class="form-group">
					<label for="villeAdherent" class="col-sm-2 control-label">Ville</label>
					<div class="col-sm-10">
						<input type="text" name="txtville" class="form-control"
							id="villeAdherent" placeholder="Ville" value="${adherent.villeAdherent}">
					</div>
				</div>
			
			</jsp:body>
		</t:form>
		
    </jsp:body>
</t:layout>

