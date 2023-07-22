package json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

        String startDateString = request.getParameter("date");
        String endDateString = request.getParameter("endDateString");
        
        
        if (startDateString == null || endDateString == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mancante data di inizio o data di fine per poter filtrare gli ordini");
            return;
        }
        try {
            
        	ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            
            OrdineDAODataSource dao = new OrdineDAODataSource();
            System.out.println("DATA INIZIO: "+ startDate);
            Collection<OrdineDTO> orders = dao.doRetrieveForDate(java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
            JSONArray jsonArray = new JSONArray();
            for (OrdineDTO o : orders) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("IDORDINE", o.getIDOrdine());
    			Collection<ItemCarrello> products = new ArrayList<ItemCarrello>();
    			try {
    				products = dao.doRetrieveAllProducts(o.getIDOrdine());
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    			String productsStr = "";
    			for(ItemCarrello ic: products) {
    				String productString = "Prodotto: ***"+ ic.getNomeProdotto() + "; " + ic.getPrezzo()+ " ;"+ 
    						ic.getCategoria()+ "; " + ic.getQuantità()+"***";
    				productsStr += " "+productString;
    			}
    			jsonObject.put("PRODOTTI", productsStr);
    			ProfileDAODataSource profiledao = new ProfileDAODataSource();
                ProfileDTO profilo = profiledao.doRetrieveByKey((String) request.getSession().getAttribute("username"));
                String utente = ""+profilo.getNome()+" "+profilo.getCognome();
                jsonObject.put("UTENTE", utente);
    			jsonObject.put("METODOSPEDIZIONE", o.getMetSpedizione());
    			jsonObject.put("METODOCONSEGNA", o.getMetConsegna());
    			jsonObject.put("DATA", (o.getDataOrdine()).toString());
    			jsonObject.put("ORA", (o.getOraOrdine()).toString());
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
        } catch (DateTimeParseException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato della data non valido");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel ritrovamento degli ordini nel range di tempo fornito");
        }
    }
}