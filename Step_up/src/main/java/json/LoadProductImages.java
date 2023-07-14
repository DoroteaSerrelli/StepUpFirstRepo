package json;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.PhotoControl;

/**
 * Servlet implementation class LoadProductImages
 */
@WebServlet("/admin/LoadProductImages")
public class LoadProductImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoadProductImages() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codice = Integer.parseInt(request.getParameter("Codice"));
		try {
			LinkedList<Integer> list = PhotoControl.loadGalleryPhotos(codice);
			JSONArray jsonArray = new JSONArray();
			for(int i : list) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("IDIMMAGINE", i);
				jsonArray.add(jsonObject);
			}
			System.out.println(jsonArray);
			String jsonImages = jsonArray.toString();
			response.setContentType("application/json");
			response.getWriter().write(jsonImages);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
