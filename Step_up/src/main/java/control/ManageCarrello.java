package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAODataSource;
import model.Carrello;
import model.ItemCarrello;
import model.ProductDTO;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/ManageCarrello")
public class ManageCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ManageCarrello() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
		if(cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("Carrello", cart);
		}

		String action = request.getParameter("action");

		if (action != null) {
			if(action.equals("remove")) {
				int id = Integer.parseInt(request.getParameter("codice"));
				ItemCarrello item = new ItemCarrello();
				item.setIDProdotto(id);
				cart.deleteProduct(item);
				request.getSession().setAttribute("Carrello", cart);
				request.setAttribute("Carrello", cart);
			}
			
			if(action.equals("insert")) {
				int id = Integer.parseInt(request.getParameter("codice"));
				ItemCarrello item = new ItemCarrello();
				ProductDAODataSource dao = new ProductDAODataSource();
				ProductDTO product = new ProductDTO();
				try {
					product = dao.doRetrieveByKey(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				item.setIDProdotto(id);
				item.setNomeProdotto(product.getNomeProdotto());
				item.setBrand(product.getBrand());
				item.setCategoria(product.getCategoria());
				item.setDescrizione_breve(product.getDescrizione_breve());
				item.setDescrizione_dettagliata(product.getDescrizione_dettagliata());
				item.setPrezzo(product.getPrezzo());
				item.setTopImage(product.getTopImage());
				item.setQuantità(1);
				cart.addProduct(item);
				request.getSession().setAttribute("Carrello", cart);
				request.setAttribute("Carrello", cart);
			}
			
			if(action.equals("delete")) {
				cart.svuota();
				request.getSession().setAttribute("Carrello", cart);
				request.setAttribute("Carrello", cart);
			}
			
			if(action.equals("updateQuantity")) {
				int id = Integer.parseInt(request.getParameter("codice"));
				ItemCarrello item = new ItemCarrello();
				ProductDAODataSource dao = new ProductDAODataSource();
				ProductDTO product = new ProductDTO();
				try {
					product = dao.doRetrieveByKey(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				item.setIDProdotto(id);
				item.setNomeProdotto(product.getNomeProdotto());
				item.setBrand(product.getBrand());
				item.setCategoria(product.getCategoria());
				item.setDescrizione_breve(product.getDescrizione_breve());
				item.setDescrizione_dettagliata(product.getDescrizione_dettagliata());
				item.setPrezzo(product.getPrezzo());
				item.setTopImage(product.getTopImage());
				item.setQuantità(Integer.parseInt(request.getParameter("quantity")));
				cart.updateProduct(item);
				request.getSession().setAttribute("Carrello", cart);
				request.setAttribute("Carrello", cart);
				
			}
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}