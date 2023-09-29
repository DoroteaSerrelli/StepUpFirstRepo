package json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.ProductDAODataSource;
import dao.VetrinaDAODataSource;
import model.ProductDTO;

/**
 * Servlet implementation class GetAllProducts
 */
@WebServlet("/admin/GetAllProducts")
public class GetAllProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("Codice") == null) {
			ProductDAODataSource dao = new ProductDAODataSource();
			try {
				Collection<ProductDTO> products = dao.doRetrieveAll("IDProdotto");
				JSONArray jsonArray = new JSONArray();
				for (ProductDTO product : products) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("idProdotto", product.getIDProdotto());
					jsonObject.put("nomeProdotto", product.getNomeProdotto());
					jsonArray.add(jsonObject);

					/*per ogni oggetto ProductDTO nella collezione products, viene creato un nuovo 
					 * oggetto JSONObject e vengono aggiunti i valori delle proprietà idProdotto e nomeProdotto. 
					 * Quindi, l'oggetto JSONObject viene aggiunto all'array JSON jsonArray.
					 * Infine, viene convertito l'array JSON in una stringa e inviato come risposta.*/

				}

				String jsonProducts = jsonArray.toString();
				response.setContentType("application/json");
				response.getWriter().write(jsonProducts);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			int codice = Integer.parseInt(request.getParameter("Codice"));
			ProductDAODataSource dao = new ProductDAODataSource();
			VetrinaDAODataSource vdao = new VetrinaDAODataSource();
			try {
				Collection<ProductDTO> products = dao.doRetrieveAll("IDProdotto");
				Collection<ProductDTO> viewproducts = vdao.doRetrieveAllProducts("IDProdotto", codice);
				JSONArray jsonArray = new JSONArray();
				for (ProductDTO product : products) {
					if(!(viewproducts.contains(product))) {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("idProdotto", product.getIDProdotto());
					jsonObject.put("nomeProdotto", product.getNomeProdotto());
					jsonArray.add(jsonObject);

					/*per ogni oggetto ProductDTO nella collezione products, viene creato un nuovo 
					 * oggetto JSONObject e vengono aggiunti i valori delle proprietà idProdotto e nomeProdotto. 
					 * Quindi, l'oggetto JSONObject viene aggiunto all'array JSON jsonArray.
					 * Infine, viene convertito l'array JSON in una stringa e inviato come risposta.*/
					}

				}
				System.out.println(jsonArray);
				String jsonProducts = jsonArray.toString();
				response.setContentType("application/json");
				response.getWriter().write(jsonProducts);
			} catch (SQLException e) {
				e.printStackTrace();
		}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}