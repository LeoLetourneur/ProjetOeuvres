<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
		
		<t:liste titre="Liste des adhérents" 
    				classe="Adherent" 
    				ajout="Ajouter un adhérent" 
    				nbPage="${nbPage}">
			<jsp:body>		
	
				<table class="table table-hovers">
					<tr>
						<th>Numero</th>
						<th>Nom</th>
						<th>Prénom</th>
						<th>Ville</th>
						<th>Actions</th>
					</tr>
			
					<c:forEach items="${adherents}" var="item" end="4">
						<tr>
							<td>${item.idAdherent}</td>
							<td>${item.nomAdherent}</td>
							<td>${item.prenomAdherent}</td>
			                <td>${item.villeAdherent}</td>
			                <td>
			                	<a type="button" class="btn btn-info" href="#">
			               			<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
			               		</a>
			                	<a type="button" class="btn btn-success" data-toggle="modal" data-target="#emailModal">
			                		<span class="glyphicon glyphicon-envelope" aria-hidden="true"></span>
			                	</a>
			                	<a type="button" class="btn btn-primary" href="Adherent?action=modifier&idAdherent=${item.idAdherent}">
			               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			               		</a>
								<a type="button" class="btn btn-danger" href="Adherent?action=supprimer&idAdherent=${item.idAdherent}">
		<!-- 						<a type="button" class="btn btn-danger" data-toggle="modal" data-target="#confirmationModal"> -->
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
			                	</a>
			                </td>
						</tr>
					</c:forEach>
				</table>
		
			</jsp:body>
		</t:liste>
		
		<t:modal modalTitle="Envoyer un mail" modalAccept="Envoyer" modalId="emailModal">
			<jsp:body>
			<div class="row">
				<label class="col-lg-2">A :</label>
				<label>TODO</label>
			</div>
			<div class="row">
			    <label class="col-lg-2">Objet :</label>
		    	<input type="text" />
		    </div>
		    <div class="row">
		    	<label class="col-lg-2">Corps :</label>
		    	<textarea rows="6" cols="70" placeholder="Texte du message"></textarea>
		    </div>
		    </jsp:body>
		</t:modal>
		
		<t:modal modalTitle="Confirmation" modalAccept="Valider" modalId="confirmationModal">
			<jsp:body>
				Etes-vous sur de vouloir supprimer cet adhérent ?
			</jsp:body>
		</t:modal>

    </jsp:body>
</t:layout>
