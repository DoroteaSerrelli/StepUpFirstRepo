let count = 1;
const nameOrLastnameErrorMessage = "Questo campo deve contenere solo lettere ed eventualmente spazi";
const emailErrorMessage = "L'email deve essere nel formato username@domain.ext";
const phoneErrorMessage = "Il numero di cellulare deve essere nel formato xxx-xxx-xxxx";
const viaErrorMessage = "La via deve essere una sequenza di lettere e spazi";
const civicoErrorMessage = "Il civico è formato da una sequenza di numeri";
const cittaErrorMessage = "La città è una sequenza di lettere e spazi";
const capErrorMessage = "Il CAP è una sequenza di numeri nel formato #####";
const provinciaErrorMessage = "La provincia è una sequenza di caratteri e spazi";
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
	if (!validateFormElem(form.Email, spanEmail, emailErrorMessage)){
		valid = false;
	}
	
	let spanPhone = document.getElementById("errorPhone");
	if(!validateFormElem(form.Telefono, spanPhone, phoneErrorMessage)){
		valid = false;
	}
	
	for (let i = 0; i < count; i++){
		let spanAddress = document.getElementById("errorAddress" + i);
		if (spanAddress == null){ // è stato rimosso
			continue;
		} else {
			if (!validateFormElem(document.getElementById("address" + i), spanAddress, addressErrorMessage)){
				valid = false;
			}
		}	
	}
	return valid;
}

function addAddress() {
	let container = document.getElementById("addresses");
	
	let div = document.createElement("div");
	div.id = "addressRow" + count;
	
	let label = document.createElement("label");
	label.htmlFor = "address" + count;
	label.appendChild(document.createTextNode("Indirizzo:"));	--------->da qui modifica
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
	input.addEventListener("click", function() {removeAddress(div.id)});
	div.appendChild(input);
	
	let span = document.createElement("span");
	span.id = "errorAddress" + count;
	div.appendChild(span);
	element.addEventListener("change", function() {validateFormElem(element, span, phoneErrorMessage)});
	count++;
	
	container.appendChild(div);
	
}

function removeAddress(id) {
	let element = document.getElementById(id);
	element.parentNode.removeChild(element);
}
