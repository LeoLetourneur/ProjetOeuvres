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
			                	<a type="button" class="btn self-border" href="#">
			               			<span class="glyphicon glyphicon-transfer" aria-hidden="true"></span>
			               		</a>
			                	<a type="button" class="btn self-border" href="OeuvrePret?action=modifier&idOeuvre=${item.idOeuvre}">
			               			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			               		</a>
			                	<a type="button" class="btn self-border" href="OeuvrePret?action=supprimer&idOeuvre=${item.idOeuvre}">
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
