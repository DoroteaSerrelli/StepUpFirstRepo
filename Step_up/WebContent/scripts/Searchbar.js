/**
 * 
 */


function searching(){
	var searchBar = document.getElementById("searchBar");
	var searchTerm = searchBar.value;
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "Ricerca?value="+encodeURIComponent(searchTerm), true);
	xhr.onreadystatechange = function(){
		if(xhr.status == 200){
			console.log("Richiesta ricevuta");
		}
	}
	
	xhr.onerror = function(){
		console.log("Errore: " + error);
	};
	xhr.send();
}

