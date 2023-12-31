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
       
   
    public UpdateProfile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String username = (String) request.getSession().getAttribute("username");

		if(action.equals("updateProfile")) {

			String nome = request.getParameter("Nome");
			String cognome = request.getParameter("Cognome");
			String telefono = request.getParameter("Telefono");
			String sesso = request.getParameter("Sesso");
			String email = request.getParameter("Email");

			ProfileDTO dto = new ProfileDTO();
			ProfileDAODataSource dao = new ProfileDAODataSource();
			dto.setUsername(username);
			dto.setEmail(email);
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
		}
		if(action.equals("updateAddress")) {
			IndirizzoDTO address = new IndirizzoDTO();
			IndirizzoDAODataSource daoI = new IndirizzoDAODataSource();
			int min = 1;
			int max =java.lang.Integer.MAX_VALUE;

			Random random = new Random();
			
			System.out.println("Numero indirizzi: "+ Integer.parseInt(request.getParameter("numIndirizzi")));
			for(int i = 1; i <= Integer.parseInt(request.getParameter("numIndirizzi")); i++) {
				int IdAddress = random.nextInt(max - min + 1) + min;
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

					e.printStackTrace();
				}
			}

			String deleteAddress = request.getParameter("deleteAddress");
			if(deleteAddress.equals("") == false) {
				IndirizzoDAODataSource daoI2 = new IndirizzoDAODataSource();
				try {
					daoI2.doDeleteAddress(Integer.parseInt(deleteAddress), username);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		RequestDispatcher dispatcherToPersonalAreaPage = getServletContext().getRequestDispatcher("/common/AreaRiservata.jsp");
		dispatcherToPersonalAreaPage.forward(request, response);
	}
}