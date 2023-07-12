package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WishlistDTO;
import model.ProductDTO;
import dao.WishlistDAODataSource;
import dao.ProductDAODataSource;

/**
 * Servlet implementation class ManageWishlist
 */
@WebServlet("/common/ManageWishlist")
public class ManageWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageWishlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if(action!= null) {
			if(action.equals("insert")) {
				String user = (String) request.getSession().getAttribute("username");
				WishlistDAODataSource dao = new WishlistDAODataSource();
				
				ProductDAODataSource pdao = new ProductDAODataSource();
				int codice = Integer.parseInt(request.getParameter("codice"));
				ProductDTO pdto;
				try {
					WishlistDTO dto = new WishlistDTO();
					dto.setUsername(user);
					pdto = pdao.doRetrieveByKey(codice);
					dao.doSaveProduct(pdto, dto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(action.equals("delete")) {
				WishlistDAODataSource dao = new WishlistDAODataSource();
				String user = (String) request.getSession().getAttribute("username");
				int codice = Integer.parseInt(request.getParameter("codice"));
				try {
					WishlistDTO dto = dao.doRetrieveWishlistByKey(user);
					dao.doDeleteProduct(codice, dto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/common/Wishlist.jsp");
		rd.forward(request, response);
	}
}