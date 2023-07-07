let count = 1;
const nameOrLastnameErrorMessage = "Questo campo deve contenere solo caratteri";
const emailErrorMessage = "L'email deve essere nel formato username@domain.ext";
const phoneErrorMessage = "Il numero di cellulare deve essere nel formato xxx-xxx-xxxx";
const emptyFieldErrorMessage = "Questo campo non può essere vuoto"

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
						//l'evento da catturare deve essere di onclick oppure onchange;
						//non di onsubmit nella form altrimenti escono controlli di HTML 5
	}
	return false;
}


function validate() {
	let valid = true;	
	let form = document.getElementById("regForm");
	
	let spanName = document.getElementById("errorName");
	if(!validateFormElem(form.Nome, spanName, nameOrLastnameErrorMessage)){
		valid = false;
	} 
	let spanSurname = document.getElementById("errorLastname");
	if (!validateFormElem(form.Cognome, spanSurname, nameOrLastnameErrorMessage)){
		valid = false;
	}
	let spanEmail = document.getElementById("errorEmail");
	if (!validateFormElem(form.email, spanEmail, emailErrorMessage)){
		valid = false;
	}
	
	for (let i = 0; i < count; i++){
		let spanPhone = document.getElementById("errorPhone" + i);
		if (spanPhone == null){ // It has been removed
			continue;
		} else {
			if (!validateFormElem(document.getElementById("phone" + i), spanPhone, phoneErrorMessage)){
				valid = false;
			}
		}	
	}
	return valid;
}

function addPhone() {
	let container = document.getElementById("phones");
	
	let div = document.createElement("div");
	div.id = "phoneRow" + count;
	
	let label = document.createElement("label");
	label.htmlFor = "phone" + count;
	label.appendChild(document.createTextNode("Phone:"));
	div.appendChild(label);
	
	let element = document.createElement("input");
	element.type = "tel";
	element.name = "phone";
	element.id = "phone" + count;
	element.pattern = "^([0-9]{3}-[0-9]{3}-[0-9]{4})$";
	element.required = true;
	element.placeholder = "###-###-####";
	div.appendChild(element);
	
	let input = document.createElement("input");
	input.type = "button";
	input.value = "-";
	input.addEventListener("click", function() {removePhone(div.id)});
	div.appendChild(input);
	
	let span = document.createElement("span");
	span.id = "errorPhone" + count;
	div.appendChild(span);
	element.addEventListener("change", function() {validateFormElem(element, span, phoneErrorMessage)});
	count++;
	
	container.appendChild(div);
	
}

function removePhone(id) {
	let element = document.getElementById(id);
	element.parentNode.removeChild(element);
}
