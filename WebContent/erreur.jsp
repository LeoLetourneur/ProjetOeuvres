<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
    <jsp:body>
    	<div id="bodyError">
			<h1>${messageErreur}</h1>
			<img id="imgError" src="img/erreur.png" alt="Erreur" class="img-responsive" height="400" width="400">
   		</div>
    </jsp:body>
</t:layout>
