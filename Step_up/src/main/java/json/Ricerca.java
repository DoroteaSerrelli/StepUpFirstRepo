package json;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.ProductDAODataSource;
import model.ProductDTO;

/**
 * Servlet implementation class Ricerca
 */
@WebServlet("/Ricerca")
public class Ricerca extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Ricerca() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("value");
		System.out.println("valore: "+ searchTerm);
		if(searchTerm != null) {
			ProductDAODataSource dao = new ProductDAODataSource();
			Collection<ProductDTO> results = new LinkedList<ProductDTO>();
			try {
				results = dao.searching(searchTerm);
			} catch (SQLException e) {
				System.out.println("Errore nella ricerca dei prodotti");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Homepage.jsp");
				rd.forward(request, response);
			}
			JSONArray jsonArray = new JSONArray();
			for (ProductDTO product : results) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("nomeProdotto", product.getNomeProdotto());
				jsonArray.add(jsonObject);
			}
			String jsonProducts = jsonArray.toString();
			response.setContentType("application/json");
			response.getWriter().write(jsonProducts);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}