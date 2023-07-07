package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ProductDAODataSource;
import model.ProductDTO;

/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/admin/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */		
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == "insert") {
			int codice = Integer.parseInt(request.getParameter("Codice"));
			String nome = request.getParameter("Nome");
			String descrBreve = request.getParameter("DescrizioneBreve");
			float prezzo = Float.parseFloat(request.getParameter("Prezzo"));
			String categoria = request.getParameter("Categoria");
			
			ProductDTO p = new ProductDTO();
			p.setIDProdotto(codice);
			p.setNomeProdotto(nome);
			p.setDescrizione_breve(descrBreve);
			p.setPrezzo(prezzo);
			p.setCategoria(categoria);
			p.setTopImage(null);
			
			ProductDAODataSource dao = new ProductDAODataSource();
			try {
				dao.doSave(p);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/UpdateCatalogo.jsp");
		dispatcher.forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}