$(function() {
	$('#datepicker').datepicker({
		format: 'dd/mm/yyyy'
	});
});

function checkDate() {
	var ok = true;
	if($('#datepicker').val() == "") {
		ok = false;
		alert("Veuillez entrer une date");
	}
	
	return ok;
}