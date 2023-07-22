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

import dao.IndirizzoDAODataSource;
import dao.ProfileDAODataSource;
import model.IndirizzoDTO;
import model.OrdineDTO;
import model.ProfileDTO;

/**
 * Servlet implementation class ManageOrdine
 */
@WebServlet("/common/ManageOrdine")
public class ManageOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ManageOrdine() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("Email");
		String username = (String) request.getSession().getAttribute("username");
		ProfileDAODataSource profiledao = new ProfileDAODataSource();
		ProfileDTO profile = new ProfileDTO();
		OrdineDTO ordine = new OrdineDTO();
		try {
			profile = profiledao.doRetrieveByKey(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(profile == null) {
			//Aggiungo i dati inseriti nell'area riservata
			String nome = request.getParameter("Nome");
			String cognome = request.getParameter("Cognome");
			String telefono = request.getParameter("Telefono");
			String sesso = request.getParameter("Sesso");

			ProfileDTO dto = new ProfileDTO();
			ProfileDAODataSource dao = new ProfileDAODataSource();
			dto.setUsername(username);
			dto.setEmail(email);
			dto.setNome(nome);
			dto.setCognome(cognome);
			dto.setTelefono(telefono);
			dto.setSesso(sesso);
			try {
				if(dao.doRetrieveByKey(email) != null) {
					dao.doDelete(email);
					dao.doSave(dto);
				}else
					dao.doSave(dto);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			IndirizzoDTO address = new IndirizzoDTO();
			IndirizzoDAODataSource daoI = new IndirizzoDAODataSource();
			int min = 1;
			int max =java.lang.Integer.MAX_VALUE;

			Random random = new Random();
			int IdAddress = random.nextInt(max - min + 1) + min;
				String via = request.getParameter("Via1");
				String civico = request.getParameter("Civico1");
				String citta = request.getParameter("Citta1");
				String provincia = request.getParameter("Provincia1");
				String cap = request.getParameter("Cap1");

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
		
		/*Creazione della pratica per l'ordine*/

		//Creazione IDOrdine
		int min = 1;
		int max =java.lang.Integer.MAX_VALUE;;

		Random random = new Random();
		int idOrdine = random.nextInt(max - min + 1) + min;

		ordine.setIDOrdine(idOrdine);
		ordine.setUsername(username);
		
		request.setAttribute("PraticaOrdine", ordine);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/common/Pagamento.jsp");
		rd.forward(request, response);
	}
}