package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProfileDTO;
import model.ProfileDAODataSource;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/UpdateProfile")
public class UpdateProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = (String) request.getSession().getAttribute("username");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String telefono = request.getParameter("telefono");
		String sesso = request.getParameter("sesso");
		
		ProfileDTO dto = new ProfileDTO();
		ProfileDAODataSource dao = new ProfileDAODataSource();
		dto.setUsername(username);
		dto.setNome(nome);
		dto.setCognome(cognome);
		dto.setTelefono(telefono);
		dto.setSesso(sesso);
		try {
			dao.doSave(dto);
		} catch (SQLException e) {
			try {
				if(dao.doRetrieveByKey(username) != null) {
					dao.doDelete(username);
					dao.doSave(dto);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcherToPersonalAreaPage = request.getRequestDispatcher("common/AreaRiservata.jsp");
		dispatcherToPersonalAreaPage.forward(request, response);
	}
}