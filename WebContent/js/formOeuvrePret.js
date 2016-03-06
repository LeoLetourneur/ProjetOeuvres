function checkFields()
{
	var ok = true;
	if($("#titreOeuvre").val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer un titre");
	}

	if(!ok) {
		$("#alertMsg").fadeIn();
	}
	
	return ok;
}