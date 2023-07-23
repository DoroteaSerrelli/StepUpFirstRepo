/**
 * 
 */

/***************************** Catalogo dei prodotti ***************************/

$(document).ready(function(){
	$("#selectImage").change(function() {
		const fieldset = document.getElementById("image-container");
		if(fieldset.style.display != "none"){
			while (fieldset.firstChild) {
      			fieldset.removeChild(fieldset.firstChild);
    		}
			fieldset.style.display = "none";
		}
        var productId = $(this).val();
        const xhr = new XMLHttpRequest();
		xhr.open("GET", "LoadProductImages?Codice="+productId, true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const images = JSON.parse(xhr.responseText);
				var imageContainer = document.getElementById("image-container");
				fieldset.style.display = "block";
    			for (var i = 0; i < images.length; i++) {
					var checkbox = document.createElement("input");
					checkbox.type = "checkbox";
					checkbox.name = "chooseImage";
      				var image = document.createElement("img");
      				image.src = "../GetProductImages?CodiceP="+productId+"&CodiceI="+images[i].IDIMMAGINE;
      				image.onerror="this.src='../images/NoPhotoAvailable.jpg'";
      				image.style="width:100px;height:100px";
      				checkbox.value = images[i].IDIMMAGINE;
      				imageContainer.appendChild(checkbox);
      				imageContainer.appendChild(image);
    			}
          	}
        };
        xhr.onerror = function() {
		console.error("Errore nel recupero delle immagini: ", xhr.statusText);
			};
		xhr.send();
      });
});
	



/************ Catalogo delle vetrine **************/
 function checkValue(nameForm, nameElement){
  var el = document.forms[nameForm].elements[nameElement];
  if (el.value !== null && el.value.trim() !== "") {
    document.querySelector("fieldset").style.display = "block";
  }
}

function showProducts(nameForm){
	const codiceValue = document.forms[nameForm].elements['Codice'].value;
	if((codiceValue != null) && (codiceValue.trim() != "")){
		const xhr = new XMLHttpRequest();
		xhr.open("GET", "GetAllProducts", true);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4 && xhr.status == 200){
				const products = JSON.parse(xhr.responseText);
		  		insertProductFieldset(products, nameForm);
			}
		};
			xhr.onerror = function() {
			console.error("Errore nel recupero dei prodotti: ", xhr.statusText);
			};
		xhr.send();
	} else {
		const fieldset = document.getElementById("addProductFieldset");
		fieldset.innerHTML = "";
		fieldset.style.display = "none";
	}
}

 
 function fetchProducts(nameForm) {
	const codiceValue = document.forms[nameForm].elements["Codice"].value;
  	if (codiceValue != null && codiceValue.trim() !== "") {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "GetProducts?Codice="+codiceValue, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
		  const products = JSON.parse(xhr.responseText);
		  updateProductFieldset(products);
      }
    };
    xhr.onerror = function() {
      console.error("Errore nel recupero dei prodotti: ", xhr.statusText);
    };
    xhr.send();
  } else {
    const fieldset = document.getElementById("productFieldset");
    fieldset.innerHTML = "";
    fieldset.style.display = "none";
  }
}

function showOtherProducts(nameForm){
	const codiceValue = document.forms[nameForm].elements["Codice"].value;
  	if (codiceValue != null && codiceValue.trim() != "") {
    const xhr = new XMLHttpRequest();
    xhr.open("GET", "GetAllProducts?Codice="+codiceValue, true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
		  const products = JSON.parse(xhr.responseText);
		  insertOtherProductFieldset(products);
      }
    };
    xhr.onerror = function() {
      console.error("Errore nel recupero dei prodotti: ", xhr.statusText);
    };
    xhr.send();
  } else {
    const fieldset = document.getElementById("addOtherProductFieldset");
  	fieldset.innerHTML = "";
    fieldset.textContent = "";
    fieldset.style.display = "none";
  }
}


function insertProductFieldset(products){
	const fieldset = document.getElementById("addProductFieldset");
  	fieldset.innerHTML = "";

  if (products.length > 0) {
    const legend = document.createElement("legend");
    legend.textContent = "Prodotti da scegliere";
    fieldset.appendChild(legend);

    products.forEach(product => {
      const label = document.createElement("label");
      label.textContent = `${product.nomeProdotto} (Codice: ${product.idProdotto})`;
      label.htmlFor = "product";

      const input = document.createElement("input");
      input.type = "checkbox";
      input.name = "product";
      input.value = product.idProdotto;

      fieldset.appendChild(label);
      fieldset.appendChild(input);
      fieldset.appendChild(document.createElement("br"));
    });
  } else {
    const p = document.createElement("p");
    p.textContent = "Nessun prodotto disponibile";
    fieldset.appendChild(p);
  }

  fieldset.style.display = "block";
}


function updateProductFieldset(products){
  const fieldset = document.getElementById("productFieldset");
  fieldset.innerHTML = "";

  if (products.length > 0) {
    const legend = document.createElement("legend");
    legend.textContent = "Prodotti da cancellare";
    fieldset.appendChild(legend);

    products.forEach(product => {
      const label = document.createElement("label");
      label.textContent = `${product.nomeProdotto} (Codice: ${product.idProdotto})`;
      label.htmlFor = "product";

      const input = document.createElement("input");
      input.type = "checkbox";
      input.name = "productDelete";
      input.value = product.idProdotto;

      fieldset.appendChild(label);
      fieldset.appendChild(input);
      fieldset.appendChild(document.createElement("br"));
    });
  } else {
    const p = document.createElement("p");
    p.textContent = "Nessun prodotto disponibile";
    fieldset.appendChild(p);
  }

  fieldset.style.display = "block";
}


function insertOtherProductFieldset(products){
	const fieldset = document.getElementById("addOtherProductFieldset");
  	fieldset.innerHTML = "";

  if (products.length > 0) {
    const legend = document.createElement("legend");
    legend.textContent = "Prodotti da scegliere";
    fieldset.appendChild(legend);

    products.forEach(product => {
      const label = document.createElement("label");
      label.textContent = `${product.nomeProdotto} (Codice: ${product.idProdotto})`;
      label.htmlFor = "productInsert";

      const input = document.createElement("input");
      input.type = "checkbox";
      input.name = "productInsert";
      input.value = product.idProdotto;

      fieldset.appendChild(label);
      fieldset.appendChild(input);
      fieldset.appendChild(document.createElement("br"));
    });
  } else {
    const p = document.createElement("p");
    p.textContent = "Nessun prodotto disponibile";
    fieldset.appendChild(p);
  }

  fieldset.style.display = "block";
}


/********************************** ORDINI COMMISSIONATI ***************************************/

function filter() {
  const option = document.getElementsByName("filter")[0];
  const divFieldDate = document.getElementById("divFieldDate");
  const divFieldUser = document.getElementById("divFieldUser");
  let ordersField = document.getElementById("OrdersTable");
  console.log(option.value);
  if (option.value === "DataOrdine") {
    divFieldDate.style.display = "block";
    divFieldUser.style.display = "none";
    ordersField.innerHTML = "";
    
  } else if (option.value === "Username") {
    divFieldDate.style.display = "none";
    divFieldUser.style.display = "block";
    ordersField.innerHTML = "";
  }
}

const errorData = "Il formato data deve essere anno-mese-giorno (aaaa-mm-gg)";
const errorEmptyField = "Questo campo non può essere vuoto";

function controllo(Elem, span, errorMessage){
	if(Elem.checkValidity()){
		Elem.classList.remove("error");
		span.style.color = "black";
		span.innerHTML = "";
		return true;
	}
	Elem.classList.add("error");
	span.style.color = "#A50D36";
	if (Elem.validity.valueMissing){	//l'utente non ha fornito un valore
		span.innerHTML = errorEmptyField;
	} else {
		span.innerHTML = errorMessage;	//pattern mismatched
	}
	return false;
}

function validDateFields(){
	let valid = true;	
	let startDate = document.getElementById("Inizio"); 
	let endDate = document.getElementById("Fine");
	
	let spanInizio = document.getElementById("errorInizio");
	if(!controllo(startDate, spanInizio, errorData)){
		valid = false;
	}
	let spanFine = document.getElementById("errorFine");
	if (!controllo(endDate, spanFine, errorData)){
		valid = false;
	}
	return valid;
}

function getOrdersForDate() {
    let startDate = document.getElementById("Inizio");
    let endDate = document.getElementById("Fine");
    if (startDate != null && endDate != null) {
        var xhr = new XMLHttpRequest();
        xhr.open("POST", "GetOrdersforDate", true);
        xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        xhr.onreadystatechange = function () {
            if (xhr.status === 200 && xhr.readyState === 4) {
                const orders = JSON.parse(xhr.responseText);
                showOrdersForDate(orders);
            }
        };
        xhr.onerror = function () {
            console.error("Errore nel recupero degli ordini: ", xhr.statusText);
        };
        xhr.send("date=" + encodeURIComponent(startDate.value) + "&endDateString=" + encodeURIComponent(endDate.value));

    }
}


function showOrdersForDate(orders) {
  let ordersTable = document.getElementById("OrdersTable");
  ordersTable.innerHTML = "";
  const table = document.createElement("table");
  const headerRow = document.createElement("tr");
  headerRow.innerHTML = "<th>Numero ordine</th><th>Prodotti</th><th>Utente</th><th>Metodo di spedizione</th><th>Metodo di consegna</th><th>Data ordine</th><th>Ora</th>";
  table.appendChild(headerRow);

  if (orders.length > 0) {
    orders.forEach(order => {
      const tableRow = document.createElement("tr");
      tableRow.innerHTML = `<td>${order.IDORDINE}</td><td>${order.PRODOTTI}</td><td>${order.UTENTE}</td><td>${order.METODOSPEDIZIONE}</td><td>${order.METODOCONSEGNA}</td><td>${order.DATA}</td><td>${order.ORA}</td>`;
      table.appendChild(tableRow);
    });
  } else {
    const p = document.createElement("p");
    p.textContent = "Nessun ordine commissionato nel periodo definito";
    ordersTable.appendChild(p);
  }

  ordersTable.innerHTML = "";
  ordersTable.appendChild(table);
  ordersTable.style.display = "table";
}

function getUserOrders(){
	let user = document.getElementById("Utente");
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "GetOrdersforUser", true);
	xhr.setRequestHeader("content-type", "application/x-www-form-urlencoded");
	xhr.onreadystatechange = function(){
		if(xhr.status == 200 && xhr.readyState == 4){
			const orders = JSON.parse(xhr.responseText);
			showUserOrders(orders);
		}
		xhr.onerror = function() {
     	console.error("Errore nel recupero degli ordini: ", xhr.statusText);
    	};
	}

	xhr.send("username="+encodeURIComponent(user.value));
}

function showUserOrders(orders){
	let ordersTable = document.getElementById("OrdersTable");
  ordersTable.innerHTML = "";
  const table = document.createElement("table");
  const headerRow = document.createElement("tr");
  headerRow.innerHTML = "<th>Numero ordine</th><th>Prodotti</th><th>Metodo di spedizione</th><th>Metodo di consegna</th><th>Data ordine</th><th>Ora</th>";
  table.appendChild(headerRow);

  if (orders.length > 0) {
    orders.forEach(order => {
      const tableRow = document.createElement("tr");
      tableRow.innerHTML = `<td>${order.IDORDINE}</td><td>${order.PRODOTTI}</td><td>${order.METODOSPEDIZIONE}</td><td>${order.METODOCONSEGNA}</td><td>${order.DATA}</td><td>${order.ORA}</td>`;
      table.appendChild(tableRow);
    });
  } else {
    const p = document.createElement("p");
    p.textContent = "Nessun ordine commissionato da questo utente";
    ordersTable.appendChild(p);
  }

  ordersTable.innerHTML = "";
  ordersTable.appendChild(table);
  ordersTable.style.display = "table";
}