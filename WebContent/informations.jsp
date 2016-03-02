<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
    	<div id="informations">
			<svg id="hexagoneLoic" viewbox="0 0 150 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgLoic" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgLolo" xlink:href="img/loic.jpg" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexLoic" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgLoic)"/>
			</svg>
			
			<svg id="hexagoneLeo" viewbox="0 0 100 100" version="1.1" xmlns="http://www.w3.org/2000/svg">
				<defs>
					<pattern id="imgLeo" patternUnits="userSpaceOnUse" width="100" height="100">
						<image id="imgLeoHover" xlink:href="img/leo.jpg" x="-25" width="150" height="100" />
					</pattern>
				</defs>
				<polygon id="hexLeo" points="50 1 95 25 95 75 50 99 5 75 5 25" fill="url(#imgLeo)"/>
			</svg>
		</div>
    </jsp:body>
</t:layout>
