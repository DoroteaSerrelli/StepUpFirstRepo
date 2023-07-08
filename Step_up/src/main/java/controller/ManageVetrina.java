package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.VetrinaDTO;
import dao.VetrinaDAODataSource;

/**
 * Servlet implementation class ManageVetrina
 */
@WebServlet("/admin/ManageVetrina")
public class ManageVetrina extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageVetrina() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if(action.equals("insert")) {
			int codice = Integer.parseInt(request.getParameter("Codice"));
			String nome = request.getParameter("Nome");
			VetrinaDTO dto = new VetrinaDTO();
			VetrinaDAODataSource dao = new VetrinaDAODataSource();
			String[] selectedProducts = request.getParameterValues("product");

			if (selectedProducts != null) {
				int i = 0;
				for (String product : selectedProducts) {
					int pcode = Integer.parseInt(product);
					if(i == 0) {
						dto.setIDVetrina(codice);
						dto.setNomeVetrina(nome);
						try {
							dao.doSave(dto, pcode);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						i++;
					}try {
						dao.addProduct(dto, pcode);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("Nessun prodotto selezionato");
			}
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/UpdateVetrina.jsp");
		dispatcher.forward(request, response);
	}
}