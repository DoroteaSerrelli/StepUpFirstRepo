package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserAccountDAODataSource;
import model.UserAccountDTO;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
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
		// TODO Auto-generated method stub
		String user = request.getParameter("username");
		String pwd = request.getParameter("password");
		List<String> errors = new ArrayList<>();	//elenco degli errori riscontrati
		RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("Login.jsp");
		
		
		//Errori di compilazione del form per il login
		if(user == null || user.trim().isEmpty()) {
			errors.add("Il campo username non può essere vuoto!");
		}
		if(pwd == null || pwd.trim().isEmpty()) {
			errors.add("Il campo password non può essere vuoto!");
		}
		if (!errors.isEmpty()) {
        	request.setAttribute("errors", errors);
        	dispatcherToLoginPage.forward(request, response);
        	return;
        }
		
		
		//Verifica corrispondenza credenziali inserite con credenziali memorizzate nel database
		
		UserAccountDAODataSource p = new UserAccountDAODataSource();
		UserAccountDTO p1 = null;
		try {
			p1 = p.doRetrieveByKey(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//le corrette credenziali dell'amministratore
		
		UserAccountDTO p2 = new UserAccountDTO();
		p2.setUserPassword(pwd);
		
		if(user.equals("admin") && (p2.getUserPassword()).equals(p1.getUserPassword())){ //admin
			request.getSession().setAttribute("isAdmin", Boolean.TRUE); //inserisco il token nella sessione
			request.getSession().setAttribute("username", p1.getUsername());
			response.sendRedirect("admin/DashboardAdmin.jsp");
		} else if ((!p1.equals(null)) && (p2.getUserPassword()).equals(p1.getUserPassword())){ //se esiste user e coincide la password
			request.getSession().setAttribute("isAdmin", Boolean.FALSE); //inserisco il token nella sessione
			request.getSession().setAttribute("username", p1.getUsername());
			response.sendRedirect("common/AreaRiservata.jsp");
		} else {
			errors.add("Username o password non validi!");
			request.setAttribute("errors", errors);
			dispatcherToLoginPage.forward(request, response);
		}
	}
}