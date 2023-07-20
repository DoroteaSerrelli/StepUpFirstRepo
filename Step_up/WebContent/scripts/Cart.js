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

function changeQuantity(element){
	const product = element.id;
	const productQuantity = element.value;
	var xhr = new XMLHttpRequest();
	if(productQuantity == 0){
		xhr.open("GET", "ManageCarrello?action=remove&codice="+product);
	}
	xhr.open("GET", "ManageCarrello?action=updateQuantity&codice="+product+"&quantity="+productQuantity, true);
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