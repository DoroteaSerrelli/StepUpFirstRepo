package json;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoControl;

/**
 * Servlet implementation class DeleteImages
 */
@WebServlet("/admin/DeleteProductImages")
public class DeleteProductImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public DeleteProductImages() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(request.getParameter("Codice") != null) {
			
			int codice = Integer.parseInt(request.getParameter("Codice"));
			String[] images = request.getParameterValues("chooseImage");
			for(String i : images) {
				int codeImage = Integer.parseInt(i);
				try {
					PhotoControl.deletePhotos(codice, codeImage);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/admin/UpdateCatalogo.jsp");
		rd.forward(request, response);
	}

}
