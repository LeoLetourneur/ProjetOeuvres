<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="css/index.css" rel="stylesheet" type="text/css" />
		
		<script src="lib/jquery/jquery-2.2.0.min.js"></script>
		<script src="lib/bootstrap/js/bootstrap.min.js"></script>
		<script src="js/index.js"></script>

		<link rel="icon" type="image/png" href="img/favicon.ico" />
		<title>Accueil</title>
	</head>
	
	<body>
		<div id="background"></div>
		<div id="body">
			<div id="tousLesHexagones">
			
			<a href="">
			<svg viewbox="-10 0 120 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="#339999"/>
				
				<text font-size="10" x="50" y="40" text-anchor="middle">Médiathèque</text>
				<text font-size="10" x="50" y="55" text-anchor="middle">de POLYTECH</text>
				<text font-size="5" x="50" y="70" text-anchor="middle">Gestion de l'exposition 2016</text>
				
			</svg>
			</a>
			
			<a href="Adherent?action=liste">
			<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgUser" patternUnits="userSpaceOnUse" width="100" height="100">
						<image xlink:href="img/user.png" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgUser)"/>
				
				<text font-size="10" x="50" y="52" text-anchor="middle">Adhérents</text>
			</svg>
			</a>
			
			<a href="OeuvreVente?action=liste">
			<svg class="hexagone rightHexagone" viewbox="-5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgVente" patternUnits="userSpaceOnUse" width="100" height="100">
						<image xlink:href="img/vente.png" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgVente)"/>
				
				<text font-size="10" x="50" y="52" text-anchor="middle">Ventes</text>
			</svg>
			</a>
			
			<a href="informations.jsp">
			<svg id="hexagoneChat" viewbox="-25 0 150 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="img" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgChat" xlink:href="img/cote.png" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexChat" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#img)"/>
			</svg>
			</a>

			<a href="OeuvrePret?action=liste">
			<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgEchange" patternUnits="userSpaceOnUse" width="100" height="100">
						<image xlink:href="img/echange.png" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgEchange)"/>
				
				<text font-size="10" x="50" y="52" text-anchor="middle">Prêts</text>
			</svg>
			</a>
			
			<a href="Reservation?action=liste">
			<svg class="hexagone rightHexagone" viewbox="-5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgReservation" patternUnits="userSpaceOnUse" width="100" height="100">
						<image xlink:href="img/reservation.png" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgReservation)"/>
				
				<text font-size="10" x="50" y="52" text-anchor="middle">Réservations</text>
			</svg>
			</a>
			
			</div>
		</div>
		
		<div id="footer">
			
		</div>
	
	</body>
</html>
