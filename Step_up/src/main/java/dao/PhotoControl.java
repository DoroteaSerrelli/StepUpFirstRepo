package dao;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PhotoControl {
	
	private static DataSource ds;

	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/stepup");

		} catch (NamingException e) {
			System.out.println("Error:" + e.getMessage());
		}
	}
	
	public synchronized static byte[] load(int id) throws SQLException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {
			
			connection = ds.getConnection();
			String sql = "SELECT TOPIMAGE FROM prodotto WHERE IDPRODOTTO = ?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("TOPIMAGE");
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
			finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					connection.close();
			}
		}
		return bt;
	}
	
	public synchronized static void updatePhoto(int idA, InputStream photo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("UPDATE prodotto SET TOPIMAGE = ? WHERE IDPRODOTTO = ?");
			try {
				stmt.setBinaryStream(1, photo, photo.available());
				stmt.setInt(2, idA);	
				stmt.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					con.close();
			}
		}
	}
	
	public synchronized static byte[] loadPhotos(int idP, int idI) throws SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		byte[] bt = null;

		try {

			connection = ds.getConnection();
			String sql = "SELECT IMMAGINE FROM galleriaImmagini WHERE (PRODOTTO = ? AND IDIMMAGINE = ?)";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, idP);
			stmt.setInt(2, idI);
			rs = stmt.executeQuery();

			if (rs.next()) {
				bt = rs.getBytes("IMMAGINE");
			}

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
		finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					connection.close();
			}
		}
		return bt;
	}
	
	public synchronized static LinkedList<Integer> loadGalleryPhotos(int idP) throws SQLException {

		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		LinkedList<Integer> photos = new LinkedList<Integer>();

		try {

			connection = ds.getConnection();
			String sql = "SELECT IDIMMAGINE FROM galleriaImmagini WHERE (PRODOTTO = ?)";
			stmt = connection.prepareStatement(sql);

			stmt.setInt(1, idP);
			rs = stmt.executeQuery();

		    while (rs.next()) {
		      photos.add(rs.getInt("IDIMMAGINE"));
		    }

		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		} 
		finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (connection != null) 
					connection.close();
			}
		}
		return photos;
	}

	public synchronized static void addPhotos(int idA, InputStream photo) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("INSERT INTO galleriaImmagini(PRODOTTO, IMMAGINE) VALUES(?, ?)");
			try {
				stmt.setInt(1, idA);
				stmt.setBinaryStream(2, photo, photo.available());
				stmt.executeUpdate();
				con.setAutoCommit(false);
				con.commit();
			} catch (IOException e) {
				System.out.println(e);
			}
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					con.close();
			}
		}
	}

	public synchronized static void deletePhotos(int idA, int idI) throws SQLException {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = ds.getConnection();
			stmt = con.prepareStatement("DELETE FROM galleriaImmagini WHERE (PRODOTTO = ? AND IDIMMAGINE = ?)");
			stmt.setInt(1, idA);
			stmt.setInt(2, idI);
			stmt.executeUpdate();
			con.setAutoCommit(false);
			con.commit();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException sqlException) {
				System.out.println(sqlException);
			} finally {
				if (con != null)
					con.close();
			}
		}
	}
}