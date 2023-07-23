<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name = "viewport" content = "width:device-width, initial-scale = 1.0">
<title>Condizioni di vendita</title>
<script type="text/javascript" src="<%= request.getContextPath()%>/scripts/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath()%>/scripts/Searchbar.js"></script>
</head>
<%@include file = "Header.jsp" %>
</head>
<body>
	<div id = "page">
		<h2>Condizioni generali di vendita</h2>
	<p class = "information">Le condizioni di vendita sono state
			predisposte nel pieno rispetto dei principi fondamentali, dettati
			dalla direttiva 97/7/CE e dal D.Lgs nr. 206/05 (artt. Da 50 a 68) per
			la protezione dei consumatori nei contratti a distanza. Tali
			condizioni generali hanno efficacia a decorrere dalla data di
			accettazione delle stesse da parte del cliente sul sito, che vale a
			tutti gli effetti quale accettazione ai sensi dell'art. 1341 del
			codice civile. La società si riserva la facoltà di modificare in
			qualsiasi momento le presenti condizioni generali di vendita
			riportandole sul sito. Viene esclusa qualsiasi responsabilità
			contrattuale o extra-contrattuale in capo alla società per danni
			diretti o indiretti a persone e/o cose provocati dalla mancata
			accettazione, anche parziale di un ordine. Effettuando un ordine
			nelle varie modalità previste, il Cliente dichiara di aver preso
			visione di tutte le indicazioni fornite durante la procedura
			d'acquisto e di accettare integralmente le Condizioni Generali di
			Vendita e di pagamento di seguito trascritte. Viene escluso ogni
			diritto del Cliente a un risarcimento danni o indennizzo, nonché
			qualsiasi responsabilità contrattuale o extracontrattuale per danni
			diretti o indiretti a persone e/o cose, provocati dalla mancata
			accettazione, anche parziale, di un ordine.<br></p>
			<h4>Tempi di evasione dell'ordine</h4>
			<p class = "information">
			Con "tempi di evasione dell'ordine" si intende l'intervallo di tempo che trascorre dalla ricezione dell'ordine alla consegna del prodotto al corriere.
			In base al tipo di spedizione scelta, si impiega il seguente numero dei giorni per l'evasione dell'ordine:
			- spedizione standard (costo 5 euro): 3 giorni lavorativi (consegna dopo 2 giorni);
			- spedizione assicurata (costo 8 euro): 3 giorni lavorativi (consegna dopo 2 giorni);
			- spedizione premium (costo 10 euro): 2 giorni lavorativi (consegna dopo 1 giorno);
			</p>
			<h4>Consegna</h4>
			<p class = "information">
			
		<ul class = "information">
			<li>Consegna Standard: La consegna avviene entro 1-2 giorni
				lavorativi dalla data di affidamento al Corriere Spedizioniere.</li>
			<li>Consegna presso fermopoint: Se scegli la modalità di
				consegna presso un fermopoint, la consegna avverrà entro 1-2 giorni
				lavorativi dalla data di affidamento al corriere. Tutti i prodotti,
				infatti, partiranno dal nostro magazzino e saranno consegnati con
				corriere presso il punto di ritiro. Quando il pacco sarà pronto per
				il ritiro riceverai una comunicazione via mail contenente un codice
				alfanumerico che dovrai esibire alla commessa per il ritiro. Hai
				tempo 7 giorni dalla ricezione della mail per poter ritirare il tuo
				ordine.</li>
			<li>Consegna priority: Questa opzione ti consente di essere
				contattato dal corriere al numero di telefono che indicherai
				nell’ordine per poter concordare il giorno e la fascia oraria a te
				più comoda per ricevere la consegna.</li>
		</ul>

		</p>

</div>

<%@ include file="Footer.jsp" %>
</body>
</html>