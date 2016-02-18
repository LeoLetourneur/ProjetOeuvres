$(window).scroll(function (event) {
    var scroll = $(window).scrollTop()
    
    $('.navbar').css("background-position", "0px -"+scroll/5+"px");
});

function init() {
	
	$("#navbarcollapsebutton").click( switchCollapse );
}

function switchCollapse() {

	$("#navbar-collapse-target > ul > li").toggleClass("showBack");
	$("#navbar-collapse-target > ul > li").toggleClass("hideBack");
}

$( document ).ready( init );