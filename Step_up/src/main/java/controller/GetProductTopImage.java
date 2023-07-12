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
 * Servlet implementation class GetProductTopImage
 */
@WebServlet("/GetProductTopImage")
public class GetProductTopImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GetProductTopImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		String id = (String) request.getParameter("Codice");
		if (id != null) 
		{
			int codice = Integer.parseInt(id);
			byte[] bt = null;
			try {
				bt = PhotoControl.load(codice);
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