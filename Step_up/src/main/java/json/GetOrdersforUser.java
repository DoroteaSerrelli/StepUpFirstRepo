package json;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.OrdineDAODataSource;
import dao.ProfileDAODataSource;
import model.ItemCarrello;
import model.OrdineDTO;
import model.ProfileDTO;

/**
 * Servlet implementation class GetOrdersforUser
 */
@WebServlet("/admin/GetOrdersforUser")
public class GetOrdersforUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetOrdersforUser() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("username");
		System.out.println("UTENTE: "+ user);


		OrdineDAODataSource dao = new OrdineDAODataSource();
		Collection<OrdineDTO> orders = new ArrayList<OrdineDTO>();
		try {
			orders = dao.doRetrieveForUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}

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
			jsonObject.put("METODOSPEDIZIONE", o.getMetSpedizione());
			jsonObject.put("METODOCONSEGNA", o.getMetConsegna());
			jsonObject.put("DATA", (o.getDataOrdine()).toString());
			jsonObject.put("ORA", (o.getOraOrdine()).toString());
			jsonArray.add(jsonObject);

			/*per ogni oggetto OrdineDTO associato all'utente fornito nella collezione orders, viene creato un nuovo 
			 * oggetto JSONObject e vengono aggiunti i valori delle proprietà idOrdine, Prodotti (elenco dei prodotti: 
			 * NomeProdotto, Prezzo di acquisto -- vedasi metodo doRetrieveAllProducts in package dao.OrdineDAODataSource--,
			 * Categoria, Quantità), il metodo di spedizione, il metodo di consegna, la data e l'ora. 
			 * Quindi, l'oggetto JSONObject viene aggiunto all'array JSON jsonArray.
			 * Infine, viene convertito l'array JSON in una stringa e inviato come risposta.*/

		}
		String jsonProducts = jsonArray.toString();
		System.out.println("Oggetto: "+jsonProducts);
		response.setContentType("application/json");
		response.getWriter().write(jsonProducts);
	}

}
