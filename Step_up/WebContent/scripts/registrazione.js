/**
 * 
 */

const usernameErrorMessage = "Questo campo deve contenere solo una sequenza di massimo 25 lettere";
const passwordErrorMessage = "Questo campo deve contenere almeno 5 caratteri e al massimo 20 caratteri di cui almeno una lettera ed un numero";
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
	let form = document.getElementById("account");
	
	let spanUsername = document.getElementById("errorUsername");
	if(!validateFormElem(form.username, spanUsername, usernameErrorMessage)){
		valid = false;
	}
	
	let spanPassword = document.getElementById("errorPassword");
	if (!validateFormElem(form.password, spanPassword, passwordErrorMessage)){
		valid = false;
	}
	
	return valid;
}