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
			                	<a type="button" class="btn self-border" title="Modifier" href="Reservation?action=modifier&idOeuvre=${item.oeuvrevente.idOeuvre}&idAdherent=${item.adherent.idAdherent}">
			               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			               		</a>
			               		<a type="button" class="btn btndel self-border" title="Supprimer" data-id="${item.oeuvrevente.idOeuvre}" data-id2="${item.adherent.idAdherent}" data-toggle="modal" data-target="#confirmationModal">
			                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			                	</a>
		                	</td>
						</tr>
					</c:forEach>
					<c:if test="${empty reservations}">
						<tr><td class="aucuneLigne" colspan=4>Aucune réservation</td></tr>
					</c:if>
				</table>
		
			</jsp:body>
		</t:liste>
		
		<t:modal modalTitle="Confirmation" modalAccept="Valider" modalId="confirmationModal" modalAction="confirmDelete()">
			<jsp:body>
				Etes-vous sur de vouloir supprimer cette réservation ?
			</jsp:body>
		</t:modal>
		
    </jsp:body>
</t:layout>