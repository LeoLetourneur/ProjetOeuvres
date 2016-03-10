<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<link href="css/informations.css" rel="stylesheet" type="text/css" />

<t:layout>
    <jsp:body>
    	<h1 class="titre">Développeurs du site</h1>
    	<div id="informations">
    	<div class="row">
			<svg id="hexagoneLoic" class="col-sm-7" viewbox="0 0 150 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgLoic" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgLolo" xlink:href="img/loic.jpg" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexLoic" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgLoic)"/>
			</svg>
			
			<h3 class="col-sm-5">Loïc GERLAND 4A Info</h3>
		</div>
			
			<div class="row">
			<h3 class="col-sm-5">Léo LETOURNEUR 4A Info</h3>
			
			<svg id="hexagoneLeo" class="col-sm-7" viewbox="0 0 150 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgLeo" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgLeoHover" xlink:href="img/leo.jpg" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexLeo" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgLeo)"/>
			</svg>
			</div>
			
		</div>
    </jsp:body>
</t:layout>
