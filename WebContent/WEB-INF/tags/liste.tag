<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="titre" %> <!--  Titre au dessus du tableau -->
<%@attribute name="classe" %> <!-- Servlet name -->
<%@attribute name="ajout" %> <!-- Label du bouton ajouter -->
<%@attribute name="nbPage" %> <!-- Nombre de page pour la pagination -->

<div class="liste" id="liste">
	
	<p id="titleList">
		${titre}
	</p>
	
	<div class="buttonHeader">
		<a type="button" class="btn btn-warning" href="index.jsp">
			<span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span> Retour accueil
		</a>
		<a type="button" class="btn btn-success" href="${classe}?action=ajouter">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> ${ajout}
		</a>
		
		<div id="dropdownDisplayPerPage" class="dropdown">
			Afficher
			<button class="btn btn-default dropdown-toggle" type="button" id="dropdownDPPButton" 
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
			  5
			  <span class="caret"></span>
			</button>
			par page

			<ul class="dropdown-menu" aria-labelledby="dropdownDPPButton">
			  <li><a href="#">5</a></li>
			  <li><a href="#">10</a></li>
			  <li><a href="#">20</a></li>
			  <li><a href="#">50</a></li>
			  <li><a href="#">Tous</a></li>
			</ul>
		</div>
	</div>
	
	<div class="table-body">
		<jsp:doBody/>
	</div>
		
	<div class="buttonFooter">
		<ul class="pagination">
			<li>
				<a href="#" aria-label="Première">
					<span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span>
				</a>
			</li>
			<li class="disabled">
				<a href="#" aria-label="Précédente">
					<span class="glyphicon glyphicon-arrow-left" aria-hidden="true"></span>
				</a>
			</li>
			
			<li class="active"><a href="#">1</a></li>
			
			<c:forEach var="i" begin="2" end="${nbPage}">
				<li><a href="#">${i}</a></li>
			</c:forEach>
			
			<li>
				<a href="#" aria-label="Prochaine">
					<span class="glyphicon glyphicon-arrow-right" aria-hidden="true"></span>
				</a>
			</li>
			<li>
				<a href="#" aria-label="Dernière">
					<span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span>
				</a>
			</li>
		</ul>
	</div>
		
</div>