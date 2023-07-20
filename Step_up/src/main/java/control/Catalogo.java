package control;

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


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action != null) {
			if(action.equals("insert")) {
				int codice = Integer.parseInt(request.getParameter("Codice"));
				String nome = request.getParameter("Nome");
				String descrBreve = request.getParameter("DescrizioneBreve");
				String descrDett = request.getParameter("DescrizioneDettagliata");
				float prezzo = Float.parseFloat(request.getParameter("Prezzo"));
				String brand = request.getParameter("Brand");
				String categoria = request.getParameter("Categoria");

				ProductDTO p = new ProductDTO();
				p.setIDProdotto(codice);
				p.setNomeProdotto(nome);
				p.setDescrizione_breve(descrBreve);
				p.setDescrizione_dettagliata(descrDett);
				p.setBrand(brand);
				p.setPrezzo(prezzo);
				p.setCategoria(categoria);

				ProductDAODataSource dao = new ProductDAODataSource();
				try {
					dao.doSave(p);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(action.equals("delete")) {
				int codice = Integer.parseInt(request.getParameter("Codice"));
				
				ProductDAODataSource dao = new ProductDAODataSource();
				try {
					dao.doDelete(codice);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			if(action.equals("update")) {
				int codice = Integer.parseInt(request.getParameter("Codice"));
				String nome = request.getParameter("Nome");
				String descrBreve = request.getParameter("DescrizioneBreve");
				float prezzo = Float.parseFloat(request.getParameter("Prezzo"));
				String categoria = request.getParameter("Categoria");

				ProductDTO p = new ProductDTO();
				ProductDAODataSource dao = new ProductDAODataSource();
				try {
					p = dao.doRetrieveByKey(codice);
					System.out.println("Prodotto trovato");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if((nome != null) && (!nome.trim().equals(""))){
					p.setNomeProdotto(nome);
				}
				if((descrBreve != null) && (!descrBreve.trim().equals(""))) {
					p.setDescrizione_breve(descrBreve);
				}
				if(prezzo != 0) {
					p.setPrezzo(prezzo);
				}
				if((categoria != null) && (!categoria.trim().equals(""))){
					p.setCategoria(categoria);
				}
				
				
				try {
					dao.doDelete(codice);
					System.out.println("Prodotto cancellato!");
					dao.doSave(p);
					System.out.println("Prodotto salvato!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/UpdateCatalogo.jsp");
		dispatcher.forward(request, response);
	}
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}