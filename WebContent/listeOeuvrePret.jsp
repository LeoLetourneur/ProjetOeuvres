<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:liste classe="OeuvrePret" 
    				ajout="Ajouter une oeuvre" 
    				nbPage="${nbPage}"
    				currentPage="${currentPage}"
    				currentNumberPerPage="${currentNumberPerPage}">
			<jsp:body>
			
				<table class="table table-hover table-striped">
					<tr>
						<th>Numero</th>
						<th>Titre</th>
						<th>Propriétaire</th>
						<th></th>
					</tr>
			
					<c:forEach items="${oeuvres}" var="item">
						<tr>
							<td>${item.idOeuvre}</td>
							<td>${item.titreOeuvre}</td>
			                <td>${item.proprietaire.nomProprietaire} ${item.proprietaire.prenomProprietaire}</td>
			                <td class="actionCol">
			                	<a type="button" class="btn self-border" title="Effectuer un prêt" disabled="disabled" href="#">
			               			<span class="glyphicon glyphicon-transfer" aria-hidden="true"></span>
			               		</a>
			                	<a type="button" class="btn self-border" title="Modifier" href="OeuvrePret?action=modifier&idOeuvre=${item.idOeuvre}">
			               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			               		</a>
			               		<a type="button" class="btn btndel self-border" title="Supprimer" data-id="${item.idOeuvre}" data-toggle="modal" data-target="#confirmationModal">
			                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			                	</a>
			               	</td>
						</tr>
					</c:forEach>
					<c:if test="${empty oeuvres}">
						<tr><td class="aucuneLigne" colspan=4>Aucune oeuvre</td></tr>
					</c:if>
				</table>
				
			</jsp:body>
		</t:liste>
		
		<t:modal modalTitle="Confirmation" modalAccept="Valider" modalId="confirmationModal" modalAction="confirmDelete()">
			<jsp:body>
				Etes-vous sur de vouloir supprimer cette oeuvre ?
			</jsp:body>
		</t:modal>
		
    </jsp:body>
</t:layout>
