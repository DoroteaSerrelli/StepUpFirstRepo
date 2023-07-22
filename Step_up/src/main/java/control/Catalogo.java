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
				String prezzo = request.getParameter("Prezzo");
				String categoria = request.getParameter("Categoria");
				String descrDettagliata = request.getParameter("DescrizioneDettagliata");
				String brand = request.getParameter("Brand");

				ProductDTO p = new ProductDTO();
				ProductDAODataSource dao = new ProductDAODataSource();
				try {
					p = dao.doRetrieveByKey(codice);
					System.out.println("Prodotto trovato");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				if((nome != null) && (!nome.trim().equals(""))){
					try {
						dao.updateData(p.getIDProdotto(), "NOMEPRODOTTO",nome);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if((descrBreve != null) && (!descrBreve.trim().equals(""))) {
					try {
						dao.updateData(p.getIDProdotto(), "DESCRIZIONE_BREVE", descrBreve);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if((descrDettagliata != null) && (!descrDettagliata.trim().equals(""))) {
					try {
						dao.updateData(p.getIDProdotto(), "DESCRIZIONE_DETTAGLIATA", descrDettagliata);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if((brand != null) && (!brand.trim().equals(""))) {
					try {
						dao.updateData(p.getIDProdotto(), "BRAND", brand);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(prezzo != null ) {
					float prezzoV = Float.parseFloat(prezzo);
					try {
						dao.updatePrice(p.getIDProdotto(), prezzoV);
					} catch (SQLException e) {
						e.printStackTrace();
					}			
				}
				if((categoria != null) && (!categoria.trim().equals(""))){
					try {
						dao.updateData(p.getIDProdotto(), "CATEGORIA", categoria);
					} catch (SQLException e) {
						e.printStackTrace();
					}
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