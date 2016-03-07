function initDatepicker() {
	$('#datepicker').datepicker({
		format: 'dd/mm/yyyy'
	});
};

function checkFields() {
	var ok = true;
	
	if($("#oeuvre").children("option:selected").val()=="-1") {
		ok = false;
		$("#dynamicText").text("Veuillez sélectionner une oeuvre");
	}
	else if($('#datepicker').val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer une date");
	}
	else if($("#adherents").children("option:selected").val()=="-1") {
		ok = false;
		$("#dynamicText").text("Veuillez sélectionner un adhérent");
	}
	
	if(!ok) {
		$("#alertMsg").fadeIn();
	}
	
	return ok;
}

function changeId() {
	$('#idOeuvre').val($("#oeuvre").children("option:selected").val());
	$('#prixOeuvrevente').text($("#oeuvre").children("option:selected").data('prix'));
	$('#idProprietaire').text($("#oeuvre").children("option:selected").data('proprietaire'));
}

$( document ).ready( initDatepicker );