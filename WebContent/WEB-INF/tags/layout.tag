<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="tabTitle" %>

<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="lib/datepicker/css/datepicker.css" rel="stylesheet" type="text/css" />
		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		
		<script src="lib/jquery/jquery-2.2.0.min.js"></script>
		<script src="lib/bootstrap/js/bootstrap.min.js"></script>
		<script src="lib/datepicker/js/bootstrap-datepicker.js"></script>
		<script type="text/javascript" src="js/navbar.js"></script>
		
		<c:if test="${!empty vue}">
			<link href="css/${vue}.css" rel="stylesheet" type="text/css" /> <!-- Add css associated to current vue-->
			<script type="text/javascript" src="js/${vue}.js"></script> <!-- Add js associated to current vue-->
		</c:if>
		
		<c:if test="${!empty module}">
			<link href="css/${module}.css" rel="stylesheet" type="text/css" /> <!-- Add css associated to current module-->
			<script type="text/javascript" src="js/${module}.js"></script> <!-- Add js associated to current module-->
		</c:if>

		<link rel="icon" type="image/png" href="img/favicon.ico" />
		<title>${tabTitle}</title>
	</head>
	
	<body>
		<div id="background"></div>
		<nav class="navbar" role="navigation">
            <div class="container">
                <div class="navbar-header">
                	<button id="navbarcollapsebutton" type="button" class="navbar-toggle self-border" data-toggle="collapse" data-target="#navbar-collapse-target">
                        <span class="icon-bar self-border"></span>
                        <span class="icon-bar self-border"></span>
                        <span class="icon-bar self-border"></span>
                    </button>
                    <a class="titreNavBar" href="/ProjetOeuvres/">
                    	<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
							<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="#339999"/>
							
							<text font-size="20" x="50" y="55" text-anchor="middle">M</text>
						</svg>
                 	</a>
                 	<h4 id="currentPageTitle"> / ${tabTitle}</h4>
                 </div>
                 <div class="collapse navbar-collapse" id="navbar-collapse-target">
                	<ul class="nav navbar-nav navbar-right">
						<a href="Adherent?action=liste">
							<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
								<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="#339999"/>
								
								<text font-size="13" x="50" y="52" text-anchor="middle">Adhérents</text>
							</svg>
						</a>
						<a href="OeuvreVente?action=liste">
							<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
								<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="#339999"/>
								
								<text font-size="13" x="50" y="45" text-anchor="middle">Vente</text>
								<text font-size="13" x="50" y="60" text-anchor="middle">d'Oeuvres</text>
							</svg>
						</a>
						<a href="OeuvrePret?action=liste">
							<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
								<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="#339999"/>
								
								<text font-size="13" x="50" y="45" text-anchor="middle">Prêt</text>
								<text font-size="13" x="50" y="60" text-anchor="middle">d'Oeuvres</text>
							</svg>
						</a>
						<a href="Reservation?action=liste">
							<svg class="hexagone" viewbox="5 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
								<polygon points="50 1 95 25 95 75 50 99 5 75 5 25" fill="#339999"/>
								
								<text font-size="13" x="50" y="52" text-anchor="middle">Réservations</text>
							</svg>
						</a>
					</ul>
                </div>
            </div>
        </nav>
        
        
		<div id="header">

		</div>
		
		<div id="body">
			<jsp:doBody/>
		</div>
		
		<div id="footer">
			
		</div>
	
	</body>
</html>
