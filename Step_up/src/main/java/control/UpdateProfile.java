package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProfileDAODataSource;
import dao.IndirizzoDAODataSource;
import model.IndirizzoDTO;
import model.ProfileDTO;

/**
 * Servlet implementation class UpdateProfile
 */
@WebServlet("/common/UpdateProfile")
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
		String nome = request.getParameter("Nome");
		String cognome = request.getParameter("Cognome");
		String telefono = request.getParameter("Telefono");
		String sesso = request.getParameter("Sesso");
		
		ProfileDTO dto = new ProfileDTO();
		ProfileDAODataSource dao = new ProfileDAODataSource();
		dto.setUsername(username);
		dto.setNome(nome);
		dto.setCognome(cognome);
		dto.setTelefono(telefono);
		dto.setSesso(sesso);
		try {
			if(dao.doRetrieveByKey(username) != null) {
				dao.doDelete(username);
				dao.doSave(dto);
			}else
				dao.doSave(dto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int min = 1;
        int max =java.lang.Integer.MAX_VALUE;;

        Random random = new Random();
        int IdAddress = random.nextInt(max - min + 1) + min;

		System.out.println(IdAddress);
		IndirizzoDTO address = new IndirizzoDTO();
		IndirizzoDAODataSource daoI = new IndirizzoDAODataSource();
		address.setIDIndirizzo(IdAddress);
		address.setVia("Onofrio De Filippis");
		address.setNumCivico("16");
		address.setCittà("Santa Lucia di Serino");
		address.setProvincia("Avellino");
		address.setCap("83100");
		try {
			daoI.doSave(address, dto.getUsername());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i = 1; request.getParameter("address"+i) != null; i++) {
			String via = request.getParameter("Via"+i);
			String civico = request.getParameter("Civico"+i);
			String citta = request.getParameter("Citta"+i);
			String provincia = request.getParameter("Provincia"+i);
			String cap = request.getParameter("Cap"+i);
			
			address.setIDIndirizzo(IdAddress);
			address.setVia(via);
			address.setNumCivico(civico);
			address.setCittà(citta);
			address.setProvincia(provincia);
			address.setCap(cap);
			try {
				daoI.doSave(address, username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String deleteAddress = request.getParameter("deleteAddress");
		if(deleteAddress.equals("") == false) {
			IndirizzoDAODataSource daoI2 = new IndirizzoDAODataSource();
			try {
				daoI2.doDeleteAddress(Integer.parseInt(deleteAddress), username);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dispatcherToPersonalAreaPage = getServletContext().getRequestDispatcher("/common/AreaRiservata.jsp");
		dispatcherToPersonalAreaPage.forward(request, response);
	}
}