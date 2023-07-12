package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Carrello;
import model.ItemCarrello;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/ManageCarrello")
public class ManageCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageCarrello() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Carrello cart = (Carrello)request.getSession().getAttribute("Carrello");
		if(cart == null) {
			cart = new Carrello();
			request.getSession().setAttribute("Carrello", cart);
		}

		String action = request.getParameter("action");

		if (action != null) {
			if(action.equals("delete")) {
				int id = Integer.parseInt(request.getParameter("codice"));
				ItemCarrello item = new ItemCarrello();
				item.setIDProdotto(id);
				cart.deleteProduct(item);
			}
			
			if(action.equals("insert")) {
				int id = Integer.parseInt(request.getParameter("codice"));
				ItemCarrello item = new ItemCarrello();
				item.setIDProdotto(id);
				cart.addProduct(item);
			}

		}

		request.getSession().setAttribute("Carrello", cart);
		request.setAttribute("Carrello", cart);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Carrello.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}