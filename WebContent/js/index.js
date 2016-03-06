function initHover() {
	
	$("#hexChat").mouseover( function() { $('#imgChat').attr('xlink:href',"img/face.png"); } );
	$("#hexChat").mouseleave( function() { $('#imgChat').attr('xlink:href',"img/cote.png"); } );
	
	$(".hexagone").mouseover( showText );
	$(".hexagone").mouseleave( hideText );
}

function showText() {
	$(this).find("text").addClass("display");
	$(this).find("polygon").attr("fill", "#339999");
}

function hideText() {
	$(this).find("text").removeClass("display");
	$(this).find("polygon").attr("fill", "url(#"+$(this).find("pattern").attr("id")+")");
}

$( document ).ready( initHover );