<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>

<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link href="css/layout.css" rel="stylesheet" type="text/css" />
		<link href="css/${module}.css" rel="stylesheet" type="text/css" /> <!-- Add css associated to current module-->
		
		<script src="lib/jquery/jquery-2.2.0.min.js"></script>
		<script src="lib/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="js/navbar.js"></script>
		<script type="text/javascript" src="js/${module}.js"></script> <!-- Add js associated to current module-->
		
		<title>${tabTitle}</title>
	</head>
	
	<body>
		<div id="header">
			<div class="marginHeader">
				<div class="row">
					
					<div class="col-xs-3 col-sm-3 col-md-3 col-lg-3">
						<svg version="1.1" id="preloader" x="0px" y="0px" height="80px" viewBox="0 0 240 120" >
							<style type="text/css" >
								<![CDATA[
									#plug, #socket { fill:#fb5061 } #loop-normal { fill: none; stroke: #86549d; stroke-width: 12 } #loop-offset { display: none }
								]]>
							</style>
							
							<path id="loop-normal" class="st1" d="M120.5,60.5L146.48,87.02c14.64,14.64,38.39,14.65,53.03,0s14.64-38.39,0-53.03s-38.39-14.65-53.03,0L120.5,60.5
							L94.52,87.02c-14.64,14.64-38.39,14.64-53.03,0c-14.64-14.64-14.64-38.39,0-53.03c14.65-14.64,38.39-14.65,53.03,0z">
								<animate attributeName="stroke-dasharray" attributeType="XML"
							    	from="500, 50"  to="450 50"
							    	begin="0s" dur="2s"
							    	repeatCount="indefinite"/>
								<animate attributeName="stroke-dashoffset" attributeType="XML"
							    	from="-40"  to="-540"
							    	begin="0s" dur="2s"
							    	repeatCount="indefinite"/>  
							</path>
							  
							<path id="loop-offset" d="M146.48,87.02c14.64,14.64,38.39,14.65,53.03,0s14.64-38.39,0-53.03s-38.39-14.65-53.03,0L120.5,60.5
							L94.52,87.02c-14.64,14.64-38.39,14.64-53.03,0c-14.64-14.64-14.64-38.39,0-53.03c14.65-14.64,38.39-14.65,53.03,0L120.5,60.5
							L146.48,87.02z"/>
							  
							<path id="socket" d="M7.5,0c0,8.28-6.72,15-15,15l0-30C0.78-15,7.5-8.28,7.5,0z"/>  
							  
							<path id="plug" d="M0,9l15,0l0-5H0v-8.5l15,0l0-5H0V-15c-8.29,0-15,6.71-15,15c0,8.28,6.71,15,15,15V9z"/>
							  
							<animateMotion
								xlink:href="#plug"
							  	dur="2s"
								rotate="auto"
								repeatCount="indefinite"
								calcMode="linear"
								keyTimes="0;1"    
								keySplines="0.42, 0, 0.58, 1">
								<mpath xlink:href="#loop-normal"/>
							</animateMotion>
							  
							<animateMotion             
								xlink:href="#socket"
							  	dur="2s"
								rotate="auto"
								repeatCount="indefinite"
								calcMode="linear"
								keyTimes="0;1"
								keySplines="0.42, 0, 0.58, 1">
								<mpath xlink:href="#loop-offset"/>
							</animateMotion>  
						</svg>
					</div>
					
					<div class="buttonHeader col-xs-3 col-sm-2 col-md-2">
						<p><a href="Controleur?action=listeAdherent">Adhérents</a></p>
					</div>
					
					<div class="buttonHeader col-xs-3 col-sm-2 col-md-2">
						<p><a href="Controleur?action=listeOeuvre">Oeuvres</a></p>
					</div>
				</div>
			</div>
		</div>
		
		<div id="body">
			<jsp:doBody/>
		</div>
		
		<div id="footer">
			
		</div>
	
	</body>
</html>
