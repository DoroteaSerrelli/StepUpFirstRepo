package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.ProductDAODataSource;
import model.ProductDTO;

/**
 * Servlet implementation class GetRisultatiRicerca
 */
@WebServlet("/GetRisultatiRicerca")
public class GetRisultatiRicerca extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public GetRisultatiRicerca() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchTerm = request.getParameter("searchBar");
		System.out.println("valore: "+ searchTerm);
		if(searchTerm != null && searchTerm.trim() != "") {
			ProductDAODataSource dao = new ProductDAODataSource();
			Collection<ProductDTO> results = new LinkedList<ProductDTO>();
			try {
				results = dao.searching(searchTerm);
			} catch (SQLException e) {
				System.out.println("Errore nella ricerca dei prodotti");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/Homepage.jsp");
				rd.forward(request, response);
			}
			
			request.setAttribute("risultati", results);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/RisultatiRicerca.jsp");
			rd.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}