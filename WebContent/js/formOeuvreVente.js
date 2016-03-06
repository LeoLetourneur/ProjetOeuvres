function checkFields()
{
	var ok = true;
	if($("#titreOeuvre").val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer un titre");
	} else if($("#txtPrix").val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer un prix");
	}

	if(!ok) {
		$("#alertMsg").fadeIn();
	}
	
	return ok;
}