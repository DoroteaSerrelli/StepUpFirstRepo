package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserAccountDAODataSource;
import model.UserAccountDTO;
/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//Verificare che non esista un account con l'username passato nel DB
		
		UserAccountDTO utente = new UserAccountDTO();
		utente.setUsername(username);
		utente.setUserPassword(password);
		
		UserAccountDAODataSource utentedao = new UserAccountDAODataSource();
		try {
			utentedao.doSave(utente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("common/AreaRiservata.jsp").forward(request, response);
	}

}
