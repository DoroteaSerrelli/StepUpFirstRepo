package json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.OrdineDAODataSource;
import dao.ProfileDAODataSource;
import model.ProfileDTO;
import model.ItemCarrello;
import model.OrdineDTO;

/**
 * Servlet implementation class GetOrdersforDate
 */
@WebServlet("/admin/GetOrdersforDate")
public class GetOrdersforDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetOrdersforDate() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("OK");
    	String dateString = request.getParameter("start");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate dates = LocalDate.parse(dateString, formatter);
    	dateString = request.getParameter("end");
    	LocalDate datef = LocalDate.parse(dateString, formatter);;
    	OrdineDAODataSource dao = new OrdineDAODataSource();
    	try {
    		Collection<OrdineDTO> orders = dao.doRetrieveForDate(Date.from(dates.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()), Date.from(datef.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
    		JSONArray jsonArray = new JSONArray();
    		for (OrdineDTO o : orders) {
    			JSONObject jsonObject = new JSONObject();
    			jsonObject.put("IDORDINE", o.getIDOrdine());
    			Collection<ItemCarrello> products = dao.doRetrieveAllProducts(o.getIDOrdine());
    			for(ItemCarrello ic: products) {
    				String productString = ic.toString();
    				jsonObject.put("PRODOTTI", products);
    			}
    			ProfileDAODataSource profiledao = new ProfileDAODataSource();
    			ProfileDTO profilo = profiledao.doRetrieveByKey((String) request.getSession().getAttribute("username"));
    			String utente = ""+profilo.getNome()+" "+profilo.getCognome();
    			jsonObject.put("UTENTE", utente);
    			jsonObject.put("METODOSPEDIZIONE", o.getMetSpedizione());
    			jsonObject.put("METODOCONSEGNA", o.getMetConsegna());
    			jsonObject.put("DATA", o.getDataOrdine());
    			jsonObject.put("ORA", o.getOraOrdine());
    			jsonArray.add(jsonObject);
    			
    			/*per ogni oggetto OrdineDTO nella collezione orders, viene creato un nuovo 
    			 * oggetto JSONObject e vengono aggiunti i valori delle proprietà idOrdine, Prodotti (elenco dei prodotti: 
    			 * idProdotto, NomeProdotto, Prezzo di acquisto -- vedasi metodo doRetrieveAllProducts in package dao.OrdineDAODataSource--,
    			 * Brand, Categoria, Quantità), il nome e cognome dell'acquirente, il metodo di spedizione, il metodo di consegna, la data e l'ora. 
    			 * Quindi, l'oggetto JSONObject viene aggiunto all'array JSON jsonArray.
    			 * Infine, viene convertito l'array JSON in una stringa e inviato come risposta.*/

    		}

    		String jsonProducts = jsonArray.toString();
    		System.out.println("Oggetto: "+jsonProducts);
    		response.setContentType("application/json");
    		response.getWriter().write(jsonProducts);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
}