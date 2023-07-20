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
		if(searchTerm != null) {
			ProductDAODataSource dao = new ProductDAODataSource();
			Collection<ProductDTO> results = new LinkedList<ProductDTO>();
			try {
				results = dao.searching(searchTerm);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONArray jsonArray = new JSONArray();
			for (ProductDTO product : results) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("idProdotto", product.getIDProdotto());
				jsonObject.put("nomeProdotto", product.getNomeProdotto());
				jsonObject.put("descrizioneBreve", product.getDescrizione_breve());
				jsonObject.put("prezzo", product.getPrezzo());
				jsonObject.put("brand", product.getBrand());
				jsonObject.put("topImage", product.getTopImage());
				jsonArray.add(jsonObject);
			}
			request.setAttribute("searchResults", jsonArray);
			RequestDispatcher rd = request.getRequestDispatcher("/RisultatiRicerca.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("searchResults", null);
			RequestDispatcher rd = request.getRequestDispatcher("/RisulatiRicerca.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}