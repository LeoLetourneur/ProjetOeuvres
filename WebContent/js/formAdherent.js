function checkFields()
{
	var ok = true;
	if($("#nomAdherent").val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer votre nom");
	} else if($("#prenomAdherent").val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer votre prenom");
	} else if($("#villeAdherent").val() == "") {
		ok = false;
		$("#dynamicText").text("Veuillez entrer la ville");
	}

	if(!ok) {
		$("#alertMsg").fadeIn();
	}
	
	return ok;
}