/**
 * 
 */

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
    const fieldset = document.forms[nameForm].getElementById("addProductFieldset");
    fieldset.innerHTML = "";
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