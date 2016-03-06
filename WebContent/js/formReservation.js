$(function() {
	$('#datepicker').datepicker({
		format: 'dd/mm/yyyy'
	});
});

function checkFields() {
	var ok = true;
	if($('#datepicker').val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer une date");
	}
	
	if(!ok) {
		$("#alertMsg").fadeIn();
	}
	
	return ok;
}