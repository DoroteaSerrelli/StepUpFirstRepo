/**
 * 
 */


function searchingKeywords(){
	var searchBar = document.getElementById("searchBar");
	var searchTerm = searchBar.value;
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "Ricerca?value="+encodeURIComponent(searchTerm), true);
	xhr.onreadystatechange = function(){
		if(xhr.status == 200 && xhr.readyState == 4){
			const products = JSON.parse(xhr.responseText);
		  	insertSuggestFieldset(products);
		}
	}
	
	xhr.onerror = function(){
		console.log("Errore: " + error);
	};
	xhr.send();
}

function insertSuggestFieldset(products){
	
	const fieldset = document.getElementById("suggests");
  	fieldset.innerHTML = "";

  if (products.length > 0) {

    products.forEach(product => {
      const paragraph = document.createElement("p");
      paragraph.textContent = `${product.nomeProdotto}`;
      
      const hr = document.createElement("hr");

      fieldset.appendChild(paragraph);
      fieldset.appendChild(hr);
      fieldset.appendChild(document.createElement("br"));
    });
  } else {
    const p = document.createElement("p");
    p.textContent = "Nessun prodotto disponibile";
    fieldset.appendChild(p);
  }

  fieldset.style.display = "block";
}