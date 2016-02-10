<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script src="lib/jquery/jquery-1.11.2.min.js"></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<title>Ajouter un adhérent</title>
</head>
<script language="Javascript" type="text/javascript"></script>
<script type="text/javascript" src="js/foncControle.js"></script>


<body>
	<h1 align="center">Ajout d'un adhérent</h1>

	<form class="form-horizontal" method="post"
		action="Controleur?action=insererAdherent" onsubmit="return teste()">
		<div class="form-group">
			<label for="nomAdherent" class="col-sm-2 control-label">Nom</label>
			<div class="col-sm-3">
				<input type="text" name="txtnom" class="form-control"
					id="nomAdherent" placeholder="Nom">
			</div>
		</div>
		<div class="form-group">
			<label for="prenomAdherent" class="col-sm-2 control-label">Prénom</label>
			<div class="col-sm-3">
				<input type="text" name="txtprenom" class="form-control"
					id="prenomAdherent" placeholder="Prénom">
			</div>
		</div>
		<div class="form-group">
			<label for="villeAdherent" class="col-sm-2 control-label">Ville</label>
			<div class="col-sm-3">
				<input type="text" name="txtville" class="form-control"
					id="villeAdherent" placeholder="Ville">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<a type="button" name="btAnnuler" class="btn btn-danger" href="index.jsp">Annuler</a>
				<button type="submit" name="bt" class="btn btn-primary">Ajouter</button>
			</div>
		</div>
	</form>

</body>
</html>