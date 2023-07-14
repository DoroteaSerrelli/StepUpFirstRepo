package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PhotoControl;

/**
 * Servlet implementation class GetProductImages
 */
@WebServlet("/GetProductImages")
public class GetProductImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetProductImages() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String id = (String) request.getParameter("CodiceP");
		String id2 = (String) request.getParameter("CodiceI");
		if (id != null && id2 != null) 
		{
			int codiceP = Integer.parseInt(id);
			int codiceI = Integer.parseInt(id2);
			byte[] bt = null;
			try {
				bt = PhotoControl.loadPhotos(codiceP, codiceI);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			ServletOutputStream out = response.getOutputStream();
			if (bt != null) 
			{
				out.write(bt);
				response.setContentType("image/jpeg");
			}
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}