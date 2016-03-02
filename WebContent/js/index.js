function initHover() {
	
	$("#hexChat").mouseover( function() { $('#imgChat').attr('xlink:href',"img/face.png"); } );
	$("#hexChat").mouseleave( function() { $('#imgChat').attr('xlink:href',"img/cote.png"); } );
	
	$(".hexagone").mouseover( showText );
	$(".hexagone").mouseleave( hideText );
}

function showText() {
	$(this).find("text").addClass("display");
}

function hideText() {
	$(this).find("text").removeClass("display");
}

$( document ).ready( initHover );