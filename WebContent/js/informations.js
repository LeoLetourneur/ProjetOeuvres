function initHover() {
		
	$(".hexagonePhoto").mouseover( showText );
	$(".hexagonePhoto").mouseleave( hideText );
}

function showText() {
	$(this).find("text").addClass("display");
}

function hideText() {
	$(this).find("text").removeClass("display");
	$(this).find("polygon").attr("fill", "url(#"+$(this).find("pattern").attr("id")+")");
}

$( document ).ready( initHover );