<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<link href="css/informations.css" rel="stylesheet" type="text/css" />
<script src="lib/jquery/jquery-2.2.0.min.js"></script>
<script src="js/informations.js"></script>

<t:layout tabTitle="Informations">
    <jsp:body>
    	<h1 class="titre">Développeurs du site </h1>
    	<div id="informations">
			<svg id="hexagoneLoic" class="hexagonePhoto" viewbox="0 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgLoic" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgLolo" xlink:href="img/loic.jpg" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexLoic" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgLoic)"/>
				
				<text font-size="10" x="50" y="52" text-anchor="middle">Loïc GERLAND</text>
			</svg>
						
			<svg id="hexagoneLeo" class="hexagonePhoto right" viewbox="0 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgLeo" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgLeoHover" xlink:href="img/leo.jpg" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexLeo" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgLeo)"/>
				
				<text font-size="10" x="50" y="52" text-anchor="middle">Léo LETOURNEUR</text>
			</svg>			
		</div>
    </jsp:body>
</t:layout>
