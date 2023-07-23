/**
 * 
 */

const nameOrLastnameErrorMessage = "Questo campo deve contenere solo lettere ed eventualmente spazi";
const emailErrorMessage = "L'email deve essere nel formato username@domain.ext";
const phoneErrorMessage = "Il numero di cellulare deve essere nel formato xxx-xxx-xxxx";
const questionErrorMessage = "Inserisci i dettagli della tua richiesta";
const emptyFieldErrorMessage = "Questo campo non può essere vuoto";

function validateFormElem(formElem, span, errorMessage) {
	if(formElem.checkValidity()){
		formElem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	formElem.classList.add("error");
	span.style.color = "#A50D36";
	if (formElem.validity.valueMissing){	//l'utente non ha fornito un valore
		span.innerHTML = emptyFieldErrorMessage;
	} else {
		span.innerHTML = errorMessage;	//pattern mismatched
	}
	return false;
}


function validateDati() {
	
	let valid = true;	
	let form = document.getElementById("servizioClienti");
	
	let spanName = document.getElementById("errorName");
	console.log(spanName);
	if(!validateFormElem(form.Nome, spanName, nameOrLastnameErrorMessage)){
		valid = false;
	}
	let spanSurname = document.getElementById("errorSurname");
	console.log(spanSurname);
	if (!validateFormElem(form.Cognome, spanSurname, nameOrLastnameErrorMessage)){
		valid = false;
	}
	let spanEmail = document.getElementById("errorEmail");
	console.log(spanEmail);
	if (!validateFormElem(form.Email, spanEmail, emailErrorMessage)){
		valid = false;
	}
	
	let spanPhone = document.getElementById("errorPhone");
	console.log(spanPhone);
	if(!validateFormElem(form.Telefono, spanPhone, phoneErrorMessage)){
		valid = false;
	}
	
	let spanMessage = document.getElementById("errorQuestionMessage");
	console.log(spanMessage);
	if(!validateFormElem(form.Messaggio, spanMessage, questionErrorMessage)){
		valid = false;
	}
	
	if(valid == true){
		form.Nome.value = "";
		form.Cognome.value = "";
		form.Email.value = "";
		form.Telefono.value = "";
		form.Messaggio.value = "";
	}
	
	return valid;
}