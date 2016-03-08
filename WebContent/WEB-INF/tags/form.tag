<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="classe" %> <!-- Servlet name -->
<%@attribute name="titre" %> <!-- Title page -->
<%@attribute name="action" %> <!-- Ajout/Modification -->

<div class="form" id="form">
	
	<h1 class="titleForm">${titre}</h1>
	
	<div id="alertMsg" class="alert alert-danger">
		<strong>Erreur !</strong> <span id="dynamicText"></span>
	</div>

	<form class="form-horizontal" method="post" action="${classe}?action=inserer" onsubmit="return checkFields()">
		
		<div class="form-body">
			<jsp:doBody/>
		</div>
		
		<div class="buttonForm">
			<a type="button" class="btn btn-danger" href="${classe}?action=liste">Annuler</a>
			<button type="submit" name="bt" class="btn btn-submit">${action}</button>
		</div>
	</form>
	
</div>