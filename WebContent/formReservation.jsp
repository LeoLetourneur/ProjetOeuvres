<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:form classe="Reservation" 
    				titre="${action} une réservation" 
    				action="${action}">
			<jsp:body>
		
				<input type="hidden" name="idOeuvre" class="form-control" id="idOeuvre" value="${oeuvre.idOeuvre}">
				<input type="hidden" name="oldOeuvre" class="form-control" id="oldOeuvre" value="${reservation.oeuvrevente.idOeuvre}">
				<input type="hidden" name="oldAdherent" class="form-control" id="oldAdherent" value="${reservation.adherent.idAdherent}">
				
				<c:choose> 
  				<c:when test="${oeuvres != null}">
                    <div class="form-group">
                        <label for="oeuvres" class="col-sm-2 control-label">Oeuvres en ventes</label>
                        <div class="col-sm-10">
                            <select class="form-control" name="idOeuvre" id="oeuvre" onchange="changeId()">
                            	<option value="-1" selected disabled>Sélectionnez une oeuvre</option>
                            <c:forEach items="${oeuvres}" var="item">
                                <option ${item.idOeuvre == reservation.oeuvrevente.idOeuvre ? 'selected' : '' }
                                		value="${item.idOeuvre}"
                                		data-prix="${item.prixOeuvrevente}"
                                		data-proprietaire="${item.proprietaire.nomProprietaire} ${item.proprietaire.prenomProprietaire}">
                                		${item.titreOeuvre}</option>
                            </c:forEach>
                            </select>
                        </div>
                    </div>
                </c:when>
  				<c:otherwise>
  					<div class="form-group">
					<label for="titreOeuvre" class="col-sm-2 control-label">Titre</label>
					<div class="col-sm-10">
						<label class="form-control" id="titreOeuvre">${oeuvre.titreOeuvre}</label>
					</div>
				</div>
  				</c:otherwise>
				</c:choose>
            	
				<div class="form-group">
					<label for="prixOeuvrevente" class="col-sm-2 control-label">Prix</label>
					<div class="col-sm-10">
						<label class="form-control" id="prixOeuvrevente">${oeuvre.prixOeuvrevente}</label>
					</div>
				</div>
				<div class="form-group">
					<label for="idProprietaire" class="col-sm-2 control-label">Propriétaire</label>
					<div class="col-sm-10">
						<label class="form-control" id="idProprietaire">
						${oeuvre.proprietaire.nomProprietaire} ${oeuvre.proprietaire.prenomProprietaire}</label>
					</div>
				</div>
                
				<div class="form-group">
					<label for="datepicker" class="col-sm-2 control-label">Date</label>
					<div class="col-sm-10">
						<fmt:formatDate var="fmtDate" value="${reservation.date}" pattern="dd/MM/yyyy"/>
						<input type="text" name="txtDate" id="datepicker" value="${fmtDate}">
					</div>
				</div>
				
				<div class="form-group">
					<label for="adherents" class="col-sm-2 control-label">Adhérent</label>
					<div class="col-sm-10">
						<select class="form-control" name="idAdherent" id="adherents">
							<option value="-1" selected disabled>Sélectionnez un adhérent</option>
						<c:forEach items="${adherents}" var="item">
		  					<option ${item.idAdherent == reservation.adherent.idAdherent ? 'selected' : '' }
		  					 value="${item.idAdherent}">${item.nomAdherent} ${item.prenomAdherent}</option>
						</c:forEach>
						</select>
					</div>
				</div>
			
			</jsp:body>
		</t:form>
	
    </jsp:body>
</t:layout>

