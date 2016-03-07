<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
    	
    	<t:liste classe="Reservation" 
    				ajout="Ajouter une reservation" 
    				nbPage="${nbPage}"
    				currentPage="${currentPage}"
    				currentNumberPerPage="${currentNumberPerPage}">
			<jsp:body>
				
				<table class="table table-hover table-striped">
					<tr>
						<th>Oeuvre Vente</th>
						<th>Adherent</th>
						<th>Date de réservation</th>
						<th></th>
					</tr>
			
					<c:forEach items="${reservations}" var="item">
						<tr>
							<td>${item.oeuvrevente.titreOeuvre}</td>
							<td>${item.adherent.nomAdherent} ${item.adherent.prenomAdherent}</td>
							<td><fmt:formatDate value="${item.date}" pattern="dd / MM / yyyy" /></td>
			                <td class="actionCol">
			                	<a type="button" class="btn self-border" href="Reservation?action=modifier&idOeuvre=${item.oeuvrevente.idOeuvre}&idAdherent=${item.adherent.idAdherent}">
			               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			               		</a>
			                	<a type="button" class="btn self-border" href="Reservation?action=supprimer&idOeuvre=${item.oeuvrevente.idOeuvre}&idAdherent=${item.adherent.idAdherent}">
			                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			                	</a>
		                	</td>
						</tr>
					</c:forEach>
				</table>
		
			</jsp:body>
		</t:liste>
		
    </jsp:body>
</t:layout>