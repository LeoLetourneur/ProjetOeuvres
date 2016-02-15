function checkFields()
{
	var ok = true;
	if($("#nomAdherent").val() == "") {
		ok = false;
		alert("Veuillez entrer votre nom");
	} else if($("#prenomAdherent").val() == "") {
		ok = false;
		alert("Veuillez entrer votre prenom");
	} else if($("#villeAdherent").val() == "") {
		ok = false;
		alert("Veuillez entrer la ville");
	}

	return ok;
}