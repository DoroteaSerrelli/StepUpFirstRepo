package control;

import java.io.IOException;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.OrdineDAODataSource;
import dao.PagamentoDAODataSource;
import model.Carrello;
import model.ItemCarrello;
import model.OrdineDTO;
import model.PagamentoDTO;

/**
 * Servlet implementation class ManagePagamento
 */
@WebServlet("/common/ManagePagamento")
public class ManagePagamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ManagePagamento() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Creazione Ordine
		Carrello cart = (Carrello) request.getSession().getAttribute("Carrello");
		int idOrdine = Integer.parseInt(request.getParameter("IDOrdine"));
		String username = (String) request.getSession().getAttribute("username");
		
		OrdineDAODataSource ordineDao = new OrdineDAODataSource();
		OrdineDTO ordine = new OrdineDTO();
		ordine.setIDOrdine(idOrdine);
		ordine.setUsername(username);
		ordine.setDataOrdine(Date.from(Instant.now()));
		ordine.setOraOrdine(LocalTime.now());
		ordine.setMetSpedizione(request.getParameter("MetSpedizione"));
		ordine.setMetConsegna(request.getParameter("MetConsegna"));
		
		float importo = 0;

		List<ItemCarrello> prodotti = cart.getProducts();

		try {
			ordineDao.doSave(ordine);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(ItemCarrello i : prodotti) {
			try {
				ordineDao.addProducttoOrder(idOrdine, i);
				importo += i.getPrezzo()*i.getQuantità();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}

		//Creazione Pagamento
		PagamentoDTO pagamento = new PagamentoDTO();
		String metodo = request.getParameter("MetPagamento");
		if(metodo != null) {
			if(metodo.equals("Carta di credito") || metodo.equals("Carta prepagata")) {
				String numeroCarta = request.getParameter("NumeroCarta");
				metodo = metodo + " n. "+ numeroCarta;
				pagamento.setMetodoPagamento(metodo);
				pagamento.setDataPagamento(Date.from(Instant.now()));
				pagamento.setOraPagamento(LocalTime.now());
			}

			if(metodo.equals("Paypal")) {
				pagamento.setMetodoPagamento(metodo);
				pagamento.setDataPagamento(Date.from(Instant.now()));
				pagamento.setOraPagamento(LocalTime.now());
			}
			
			String metSpedizione = request.getParameter("MetSpedizione");
			
			if(metodo.equals("Contanti alla consegna")) {
				pagamento.setMetodoPagamento(metodo);
				
				if(metSpedizione.equals("Spedizione standard")) {
					//impiega 5 giorni
					LocalDate localDate = LocalDate.now().plusDays(5);
					Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					pagamento.setDataPagamento(date);
					LocalTime time = LocalTime.of(11, 30);
					pagamento.setOraPagamento(time);
					
				}
				if(metSpedizione.equals("Spedizione assicurata")) {
					//impiega 5 giorni
					LocalDate localDate = LocalDate.now().plusDays(5);
					Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					pagamento.setDataPagamento(date);
					LocalTime time = LocalTime.of(11, 30);
					pagamento.setOraPagamento(time);
				}
				if(metSpedizione.equals("Spedizione premium")) {

					//impiega 3 giorni
					LocalDate localDate = LocalDate.now().plusDays(3);
					Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
					pagamento.setDataPagamento(date);
					LocalTime time = LocalTime.of(12, 00);
					pagamento.setOraPagamento(time);
				}

			}
			//Impostare l'importo
			if(metSpedizione.equals("Spedizione premium"))
				pagamento.setImporto(importo + 10);  //la spedizione premium richiede 10 euro di commissione
			if(metSpedizione.equals("Spedizione assicurata"))
				pagamento.setImporto(importo + 8); //la spedizione assicurata richiede 8 euro di commissione
			if(metSpedizione.equals("Spedizione standard")) 
				pagamento.setImporto(importo + 5); //la spedizione standard richiede 5 euro di commissione
			
			//Associazione Ordine-Pagamento
			pagamento.setIDOrdine(idOrdine);
			PagamentoDAODataSource paymentdao = new PagamentoDAODataSource();
			try {
				paymentdao.doSave(pagamento);
				request.getSession().removeAttribute("Carrello");
				RequestDispatcher rd = getServletContext().getRequestDispatcher("/common/SuccessoPagamento.jsp");
				rd.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rdfailure = getServletContext().getRequestDispatcher("/common/FallimentoPagamento.jsp");
		rdfailure.forward(request, response);
	}

}
