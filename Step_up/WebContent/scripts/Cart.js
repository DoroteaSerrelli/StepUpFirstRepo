/**
 * 
 */

function insertCart(element, span){
	const product = element.id;
	console.log(span);
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "ManageCarrello?action=insert&codice="+product, true);
	xhr.onreadystatechange = function(){
		if(xhr.status == 200 && xhr.readyState == 4){
			console.log("Richiesta ricevuta");
			span.style.color = "#A50D36";
			span.innerHTML = "Prodotto aggiunto nel carrello";
			setTimeout(function() {
				span.innerHTML = "";
			}, 5000);

		}
	};
	xhr.error = function(){
		console.log("Errore nell'inserimento nel carrello: "+ error);
	};
	xhr.send();
}

function changePlusQuantity(element){
	const product = document.getElementById(element);
	let productQuantity = parseInt(product.value);
	console.log(element);
	console.log("quantità iniziale: " + productQuantity);
	productQuantity += 1;
	console.log("quantità iniziale: " + productQuantity);
	var xhr = new XMLHttpRequest();

	xhr.open("GET", "ManageCarrello?action=updateQuantity&codice="+element+"&quantity="+productQuantity, true);
	xhr.onreadystatechange = function(){
		if(xhr.status == 200 && xhr.readyState == 4){
			console.log("Richiesta ricevuta");
			location.reload();

		}
	};
	xhr.error = function(){
		console.log("Errore nella modifica della quantità: "+ error);
	};
	xhr.send();
	
}

function changeMinusQuantity(element){
	const product = document.getElementById(element);
	let productQuantity = product.value;
	console.log(element);
	productQuantity -= 1;
	
	var xhr = new XMLHttpRequest();
	if(productQuantity == 0){
		xhr.open("GET", "ManageCarrello?action=remove&codice="+element, true);
	
	}else{
		xhr.open("GET", "ManageCarrello?action=updateQuantity&codice="+element+"&quantity="+productQuantity, true);
	}
	
	xhr.onreadystatechange = function(){
		if(xhr.status == 200 && xhr.readyState == 4){
			console.log("Richiesta ricevuta");
			location.reload();

		}
	};
	xhr.error = function(){
		console.log("Errore nella modifica della quantità: "+ error);
	};
	xhr.send();
	
}