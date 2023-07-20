package control;

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
		
		if(action.equals("delete")) {
			int codice = Integer.parseInt(request.getParameter("Codice"));
			VetrinaDAODataSource dao = new VetrinaDAODataSource();
			try {
				dao.doDelete(codice);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if(action.equals("update")) {
			int codice = Integer.parseInt(request.getParameter("Codice"));
			VetrinaDAODataSource dao = new VetrinaDAODataSource();

			try {
				VetrinaDTO dto = dao.doRetrieveByKey(codice);
				String[] deleteProducts = request.getParameterValues("productDelete");
				if(deleteProducts != null) {
					for(String value: deleteProducts){
						dao.deleteProduct(dto, Integer.parseInt(value));
					}
				}

				String[] addProducts = request.getParameterValues("product");
				System.out.println(addProducts);
				if(addProducts != null) {
					for(String value: addProducts){
						dao.addProduct(dto, Integer.parseInt(value));
					}
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/UpdateVetrina.jsp");
		dispatcher.forward(request, response);
	}
}