/**
 * 
 */

 /**************** PAGINA PAGAMENTO *****************/

const IntestatarioErrorMessage = "Il nominativo dell'intestatario deve contenere solo lettere ed evetualmente spazi";
const emptyFieldErrorMessage = "Questo campo non può essere vuoto";
const NumeroCartaErrorMessage = "Il numero della carta deve avere solo 16 cifre numeriche";
const DataCartaErrorMessage = "La data di scadenza deve avere formato mm/aa (mese/anno)";
const CVCCartaErrorMessage = "Il codice CVC deve essere formato da 3 cifre numeriche";
const emailErrorMessage = "L'email deve essere nel formato username@domain.ext";

function validateFormElem(formElem, span, errorMessage){
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
 
 function validazione(){
	 let form = document.forms["Pagamento"];
	 let valid = true;
	
	 let spanIntestatario = document.getElementById("errorIntestatario");
	 if(!validateFormElem(form.Intestatario, spanIntestatario, IntestatarioErrorMessage)){
		valid = false;
	 }
	 let spanEmail = document.getElementById("errorEmail");
	 if (!validateFormElem(form.Email, spanEmail, emailErrorMessage)){
		valid = false;
	 }
	 let spanCarta = document.getElementById("errorNumeroCarta");
	 if (!validateFormElem(form.NumeroCarta, spanCarta, NumeroCartaErrorMessage)){
		valid = false;
	 }
	 let spanDataCarta = document.getElementById("errorDataCarta");
	 if (!validateFormElem(form.DataCarta, spanDataCarta, DataCartaErrorMessage)){
		valid = false;
	 }
	
	 let spanCVCCarta = document.getElementById("errorCVCCarta");
	 if(!validateFormElem(form.CVCCarta, spanCVCCarta, CVCCartaErrorMessage)){
		valid = false;
	 }
	 
	 return valid;
}
 
 
 function showdatiPagamento(){
	let radioButtons = document.getElementsByName("MetPagamento");
	let selectedValue;
	let fieldDati = document.getElementById("datiPagamento");
	
	for (let i = 0; i < radioButtons.length; i++) {
  		if (radioButtons[i].checked) {
   			selectedValue = radioButtons[i].value;
   			if(selectedValue === "Carta di credito" || selectedValue === "Carta prepagata"){
				   if(fieldDati.style.display !== "none"){
					   fieldDati.innerHTML = "";
					   fieldDati.style.display = "none";
				   }
				   
				   fieldDati.style.display= "block";
				   
				   let labelIntestatario = document.createElement("label");
				   labelIntestatario.htmlFor = "Intestatario";
				   labelIntestatario.appendChild(document.createTextNode("Intestatario "));	
				   fieldDati.appendChild(labelIntestatario);
				   
				   let inputIntestatario = document.createElement("input");
				   inputIntestatario.name = "Intestatario";
				   inputIntestatario.type = "text";
				   inputIntestatario.required = "true";
				   inputIntestatario.pattern = "^([A-Za-z\\s]+)$";
				   inputIntestatario.placeholder = "Nominativo intestatario";
				   fieldDati.appendChild(inputIntestatario);
				   let spanIntestatario = document.createElement("span");
				   spanIntestatario.id = "errorIntestatario";
				   fieldDati.appendChild(spanIntestatario);
				   inputIntestatario.addEventListener("change", function() {validateFormElem(inputIntestatario, errorIntestatario, IntestatarioErrorMessage)});
				   
				   let br = document.createElement("br");
				   fieldDati.appendChild(br);
				   
				   let labelEmail = document.createElement("label");
				   labelEmail.htmlFor = "Email";
				   labelEmail.appendChild(document.createTextNode("Email intestatario:"));	
				   fieldDati.appendChild(labelEmail);
				   
				   let inputEmail = document.createElement("input");
				   inputEmail.name = "Email";
				   inputEmail.type = "email";
				   inputEmail.required = "true";
				   inputEmail.placeholder = "Email intestatario";
				   fieldDati.appendChild(inputEmail);
				   let spanEmail = document.createElement("span");
				   spanEmail.id = "errorEmail";
				   fieldDati.appendChild(spanEmail);
				   inputEmail.addEventListener("change", function() {validateFormElem(inputEmail, spanEmail, emailErrorMessage)});

				   let br2 = document.createElement("br");
				   fieldDati.appendChild(br2);
				   
				   let labelCarta = document.createElement("label");
				   labelCarta.htmlFor = "NumeroCarta";
				   labelCarta.appendChild(document.createTextNode("Numero Carta:"));	
				   fieldDati.appendChild(labelCarta);
				   
				   let inputCarta = document.createElement("input");
				   inputCarta.name = "NumeroCarta";
				   inputCarta.type = "text";
				   inputCarta.required = "true";
				   inputCarta.pattern = "\\d{16}";
				   inputCarta.placeholder = "1234123412341234";
				   fieldDati.appendChild(inputCarta);
				   let spanCarta = document.createElement("span");
				   spanCarta.id = "errorNumeroCarta";
				   fieldDati.appendChild(spanCarta);
				   inputCarta.addEventListener("change", function() {validateFormElem(inputCarta, errorNumeroCarta, NumeroCartaErrorMessage)});
				   
				   let br3 = document.createElement("br");
				   fieldDati.appendChild(br3);
				   
				   let labelDataCarta = document.createElement("label");
				   labelDataCarta.htmlFor = "DataCarta";
				   labelDataCarta.appendChild(document.createTextNode("Data di scadenza:"));	
				   fieldDati.appendChild(labelDataCarta);
				   
				   let inputDataCarta = document.createElement("input");
				   inputDataCarta.name = "DataCarta";
				   inputDataCarta.type = "text";
				   inputDataCarta.required = "true";
				   inputDataCarta.pattern = "^(\\d{2}/\\d{2})$";
				   inputDataCarta.placeholder = "MM/YY";
				   fieldDati.appendChild(inputDataCarta);
				   let spanDataCarta = document.createElement("span");
				   spanDataCarta.id = "errorDataCarta";
				   fieldDati.appendChild(spanDataCarta);
				   inputDataCarta.addEventListener("change", function() {validateFormElem(inputDataCarta, errorDataCarta, DataCartaErrorMessage)});
				   
				   let br4 = document.createElement("br");
				   fieldDati.appendChild(br4);
				   
				   let labelCVCCarta = document.createElement("label");
				   labelCVCCarta.htmlFor = "CVCCarta";
				   labelCVCCarta.appendChild(document.createTextNode("Codice CVC:"));	
				   fieldDati.appendChild(labelCVCCarta);
				   
				   let inputCVCCarta = document.createElement("input");
				   inputCVCCarta.name = "CVCCarta";
				   inputCVCCarta.type = "text";
				   inputCVCCarta.required = "true";
				   inputCVCCarta.pattern = "^\\d{3}$";
				   inputCVCCarta.placeholder = "xxx";
				   fieldDati.appendChild(inputCVCCarta);
				   let spanCVCCarta = document.createElement("span");
				   spanCVCCarta.id = "errorCVCCarta";
				   fieldDati.appendChild(spanCVCCarta);
				   inputCVCCarta.addEventListener("change", function() {validateFormElem(inputCVCCarta, errorCVCCarta, CVCCartaErrorMessage)});
				   
				   let br5 = document.createElement("br");
				   fieldDati.appendChild(br5);
				   
				   let buttonSubmit = document.createElement("button");
				   buttonSubmit.id = "pulsante";
				   buttonSubmit.type = "submit";
				   buttonSubmit.value = "Carta";
				   buttonSubmit.addEventListener("click", function(){validazione()});
				   buttonSubmit.appendChild(document.createTextNode("Effettua il pagamento"));
				   fieldDati.appendChild(buttonSubmit);
			   }
			   
			   if(selectedValue === "Paypal"){
				   if(fieldDati.style.display !== "none"){
					   fieldDati.innerHTML = "";
					   fieldDati.style.display = "none";
				   }
				   
				   fieldDati.style.display= "block";
				   let buttonPayPal = document.createElement("button");
				   buttonPayPal.name = "Paypal";
				   buttonPayPal.type = "submit";
				   buttonPayPal.value = "Paypal";
				   buttonPayPal.addEventListener("click", function(){validazione()});
				   buttonPayPal.appendChild(document.createTextNode("Paga con Paypal"));
				   fieldDati.appendChild(buttonPayPal);
			   }
			   
			   if(selectedValue === "Contanti alla consegna"){
				   if(fieldDati.style.display !== "none"){
					   fieldDati.innerHTML = "";
					   fieldDati.style.display = "none";
				   }
				   fieldDati.style.display= "block";
				   let buttonContanti = document.createElement("button");
				   buttonContanti.name = "Contanti";
				   buttonContanti.type = "submit";
				   buttonContanti.value = "Contanti";
				   buttonContanti.addEventListener("click", function(){validazione()});
				   buttonContanti.appendChild(document.createTextNode("Paga in contanti alla consegna"));
				   fieldDati.appendChild(buttonContanti);
			   }
    
  }	
 }
 }