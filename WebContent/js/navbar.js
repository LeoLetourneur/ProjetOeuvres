$(window).scroll(function (event) {
    var scroll = $(window).scrollTop()
    
    //BACKGROUND
    if(scroll > 100) {
    	$('.marginHeader').css("background-color", "white");
    } else {
    	$('.marginHeader').css("background-color", "transparent");
    }
    
    //BORDER
    if(scroll > 0) {
    	$('.marginHeader').css("border-bottom", "1px solid black");
    } else {
    	$('.marginHeader').css("border-bottom", "none");
    }
    
});

