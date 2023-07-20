/**
 * 
 */

 let count = 2;

const nameOrLastnameErrorMessage = "Questo campo deve contenere solo lettere ed eventualmente spazi";
const emailErrorMessage = "L'email deve essere nel formato username@domain.ext";
const phoneErrorMessage = "Il numero di cellulare deve essere nel formato xxx-xxx-xxxx";

const viaErrorMessage = "La via deve essere una sequenza di lettere ed eventualmente spazi";
const civicoErrorMessage = "Il civico è formato o da una sequenza di numeri o dalla sequenza lettera-numeri (es. A10) o numeri-lettera (es. 10A)";
const cittaErrorMessage = "La città è una sequenza di lettere ed eventualmente spazi";
const capErrorMessage = "Il CAP è una sequenza di numeri nel formato #####";
const provinciaErrorMessage = "La provincia è una sequenza di caratteri ed eventualmente spazi";
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
	
	for (let i = 1; i < count; i++){
		let spanAddress = document.getElementById("errorAddress" + i);
		let spanVia = document.getElementById("errorVia" + i);
		let spanCivico = document.getElementById("errorCivico" + i);
		let spanCitta = document.getElementById("errorCitta" + i);
		let spanProvincia = document.getElementById("errorProvincia" + i);
		let spanCap = document.getElementById("errorCap" + i);
		if (spanAddress == null){ // è stato rimosso
			continue;
		} else {
			if (!validateFormElem(document.getElementById("Via" + i), spanVia, viaErrorMessage)){
				valid = false;
			}
			if (!validateFormElem(document.getElementById("Civico" + i), spanCivico, civicoErrorMessage)){
				valid = false;
			}
			if (!validateFormElem(document.getElementById("Citta" + i), spanCitta, cittaErrorMessage)){
				valid = false;
			}
			if (!validateFormElem(document.getElementById("Provincia" + i), spanProvincia, provinciaErrorMessage)){
				valid = false;
			}
			if (!validateFormElem(document.getElementById("Cap" + i), spanCap, capErrorMessage)){
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
	label.appendChild(document.createTextNode("Indirizzo"));	
	div.appendChild(label);
	
	let hiddenelement = document.createElement("input");
	hiddenelement.type = "hidden";
	hiddenelement.name = "address"+count;
	hiddenelement.value = count;
	div.appendChild(hiddenelement);
	
	let br = document.createElement("br");
	div.appendChild(br);

	
	/************** VIA *****************/
	
	let labelVia = document.createElement("label");
	labelVia.htmlFor = "Via" + count;
	labelVia.appendChild(document.createTextNode("Via: "));	
	div.appendChild(labelVia);
	
	let viaElement = document.createElement("input");
	viaElement.type = "text";
	viaElement.name = "Via";
	viaElement.id = "Via" + count;
	viaElement.pattern = "^[A-Za-z\\s]+$";
	viaElement.required = true;
	viaElement.placeholder = "Inserisci la via";
	div.appendChild(viaElement);
	
	let spanVia = document.createElement("span");
	spanVia.id = "errorVia" + count;
	div.appendChild(spanVia);
	viaElement.addEventListener("change", function() {validateFormElem(viaElement, spanVia, viaErrorMessage)});
	
	let br2 = document.createElement("br");
	div.appendChild(br2);

	
	/******************** CIVICO ***************/
	
	let labelCivico = document.createElement("label");
	labelCivico.htmlFor = "Civico" + count;
	labelCivico.appendChild(document.createTextNode("Civico: "));	
	div.appendChild(labelCivico);
	
	let civicoElement = document.createElement("input");
	civicoElement.type = "text";
	civicoElement.name = "Civico";
	civicoElement.id = "Civico" + count;
	civicoElement.pattern = "^(([0-9])|(([0-9]+|\\w)(\\w|[0-9]+)))$";
	civicoElement.required = true;
	civicoElement.placeholder = "Inserisci il numero civico";
	div.appendChild(civicoElement);
	
	let spanCivico = document.createElement("span");
	spanCivico.id = "errorCivico" + count;
	div.appendChild(spanCivico);
	civicoElement.addEventListener("change", function() {validateFormElem(civicoElement, spanCivico, civicoErrorMessage)});
	
	let br3 = document.createElement("br");
	div.appendChild(br3);
	
	/************** CITTA'**************/
	
	let labelCitta = document.createElement("label");
	labelCitta.htmlFor = "Citta" + count;
	labelCitta.appendChild(document.createTextNode("Città: "));	
	div.appendChild(labelCitta);
	
	let cittaElement = document.createElement("input");
	cittaElement.type = "text";
	cittaElement.name = "Citta";
	cittaElement.id = "Citta" + count;
	cittaElement.pattern = "^[A-Za-z\\s]+$";
	cittaElement.required = true;
	cittaElement.placeholder = "Inserisci la città";
	div.appendChild(cittaElement);
	
	let spanCitta = document.createElement("span");
	spanCitta.id = "errorCitta" + count;
	div.appendChild(spanCitta);
	cittaElement.addEventListener("change", function() {validateFormElem(cittaElement, spanCitta, cittaErrorMessage)});
	
	let br4 = document.createElement("br");
	div.appendChild(br4);
	
	/************** PROVINCIA ******************/
	
	let labelProvincia = document.createElement("label");
	labelProvincia.htmlFor = "Provincia" + count;
	labelProvincia.appendChild(document.createTextNode("Provincia: "));	
	div.appendChild(labelProvincia);
	
	let provinciaElement = document.createElement("input");
	provinciaElement.type = "text";
	provinciaElement.name = "Provincia";
	provinciaElement.id = "Provincia" + count;
	provinciaElement.pattern = "^[A-Za-z\\s]+$";
	provinciaElement.required = true;
	provinciaElement.placeholder = "Inserisci la provincia";
	div.appendChild(provinciaElement);
	
	let spanProvincia = document.createElement("span");
	spanProvincia.id = "errorCitta" + count;
	div.appendChild(spanProvincia);
	provinciaElement.addEventListener("change", function() {validateFormElem(provinciaElement, spanProvincia, provinciaErrorMessage)});
	
	let br5 = document.createElement("br");
	div.appendChild(br5);
	
	let input = document.createElement("input");
	input.type = "button";
	input.value = "-";
	input.addEventListener("click", function() {removeAddress(div.id)});
	div.appendChild(input);
	
	let input2 = document.createElement("input");
	input2.type = "button";
	input2.value = "+";
	input2.addEventListener("click", function() {addAddress(div.id)});
	div.appendChild(input2);
	
	count++;
	container.appendChild(div);
}

function removeAddress(id) {
	let element = document.getElementById(id);
	element.parentNode.removeChild(element);
}