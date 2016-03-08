function initAdherent() {
	$(".btnmail").click( sendMail );
}

function sendMail() {
	$("#nomAdherent").text($(this).data('nom'));
}

$(document).ready(initAdherent);