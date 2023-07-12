package dao;

import java.sql.SQLException;

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

			ds = (DataSource) envCtx.lookup("jdbc/users");

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
}