<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:liste classe="Adherent" 
    				ajout="Ajouter un adhérent" 
    				nbPage="${nbPage}"
    				currentPage="${currentPage}"
    				currentNumberPerPage="${currentNumberPerPage}">
			<jsp:body>		
	
				<table class="table table-hover table-striped">
					<tr>
						<th>Numero</th>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Ville</th>
						<th></th>
					</tr>
					
					<c:forEach items="${adherents}" var="item">
						<tr>
							<td>${item.idAdherent}</td>
							<td>${item.nomAdherent}</td>
							<td>${item.prenomAdherent}</td>
			                <td>${item.villeAdherent}</td>
			                <td class="actionCol">
			                	<a type="button" class="btn btnmail self-border" title="Envoyer un mail" 
			                		data-nom="${item.nomAdherent} ${item.prenomAdherent}" data-toggle="modal" data-target="#emailModal">
			                		<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
			                	</a>
			                	<a type="button" class="btn self-border" title="Modifier" href="Adherent?action=modifier&idAdherent=${item.idAdherent}">
			               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			               		</a>
								<a type="button" class="btn btndel self-border" title="Supprimer" data-id="${item.idAdherent}" data-toggle="modal" data-target="#confirmationModal">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			                	</a>
			                </td>
						</tr>
					</c:forEach>
					<c:if test="${empty adherents}">
						<tr><td class="aucuneLigne" colspan=5>Aucun adhérent</td></tr>
					</c:if>
				</table>
		
			</jsp:body>
		</t:liste>
		
		<t:modal modalTitle="Envoyer un mail" modalAccept="Envoyer" modalId="emailModal" modalAction="$('#emailModal').modal('hide')">
			<jsp:body>
			<div class="row">
				<label class="col-lg-2">A :</label>
				<label id="nomAdherent"></label>
			</div>
			<div class="row">
			    <label class="col-lg-2">Objet :</label>
		    	<input type="text" />
		    </div>
		    <div class="row">
		    	<label class="col-lg-2">Corps :</label>
		    	<textarea id="textAreaMail" rows="6" cols="70" placeholder="Texte du message"></textarea>
		    </div>
		    </jsp:body>
		</t:modal>
		
		<t:modal modalTitle="Confirmation" modalAccept="Valider" modalId="confirmationModal" modalAction="confirmDelete()">
			<jsp:body>
				Etes-vous sur de vouloir supprimer cet adhérent ?
			</jsp:body>
		</t:modal>

    </jsp:body>
</t:layout>
