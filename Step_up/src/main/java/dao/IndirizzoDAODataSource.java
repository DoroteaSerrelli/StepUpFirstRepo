package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.IndirizzoDTO;

public class IndirizzoDAODataSource implements IBeanIndirizzoDAO{
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

	private static final String TABLE_NAME = "indirizzo";

	@Override
	public synchronized void doSave(IndirizzoDTO address, String username) throws SQLException {

		Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    String insertSQL = "INSERT INTO " + IndirizzoDAODataSource.TABLE_NAME
	            + "(IDINDIRIZZO, VIA, NUMCIVICO, CITTA, CAP, PROVINCIA) VALUES (?, ?, ?, ?, ?, ?)";

	    try {
	        connection = ds.getConnection();
	        connection.setAutoCommit(false);
	        preparedStatement = connection.prepareStatement(insertSQL);
	        preparedStatement.setInt(1, address.getIDIndirizzo());
	        preparedStatement.setString(2, address.getVia());
	        preparedStatement.setString(3, address.getNumCivico());
	        preparedStatement.setString(4, address.getCittà());
	        preparedStatement.setString(5, address.getCap());
	        preparedStatement.setString(6, address.getProvincia());

	        preparedStatement.executeUpdate();

	        connection.commit();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }

	    String insertSQL2 = "INSERT INTO RISIEDE(USERNAME, IDINDIRIZZO) VALUES (?, ?)";

	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(insertSQL2);
	        preparedStatement.setString(1, username);
	        preparedStatement.setInt(2, address.getIDIndirizzo());
	        preparedStatement.executeUpdate();

	        connection.setAutoCommit(false);
	        connection.commit();
	    } finally {
	        try {
	            if (preparedStatement != null)
	                preparedStatement.close();
	        } finally {
	            if (connection != null)
	                connection.close();
	        }
	    }	
	}

	@Override
	public synchronized IndirizzoDTO doRetrieveByKey(int IDIndirizzo, String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		IndirizzoDTO dto = new IndirizzoDTO();

		String selectSQL = "SELECT * FROM " + IndirizzoDAODataSource.TABLE_NAME + " WHERE (USERNAME = ? AND IDINDIRIZZO = ?) ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, IDIndirizzo);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dto.setIDIndirizzo(rs.getInt("IDINDIRIZZO"));
				dto.setVia(rs.getString("VIA"));
				dto.setNumCivico(rs.getString("NUMCIVICO"));
				dto.setCittà(rs.getString("CITTA"));
				dto.setCap(rs.getString("CAP"));
				dto.setProvincia(rs.getString("PROVINCIA"));
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return dto;
	}

	@Override
	public synchronized boolean doDeleteAddress(int IDIndirizzo, String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM RISIEDE WHERE (USERNAME = ? AND IDINDIRIZZO = ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, IDIndirizzo);

			result = preparedStatement.executeUpdate();
			doDelete(IDIndirizzo);

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	@Override
	public synchronized boolean doDelete(int IDIndirizzo) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet risultato = null;

		int result = 0;

		String selectSQL = "SELECT * FROM RISIEDE WHERE (IDINDIRIZZO = ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDIndirizzo);

			risultato = preparedStatement.executeQuery();
			if(risultato.next()) {
				result = 1;
			}
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		
		if(result == 1) {
			String deleteSQL = "DELETE FROM "+IndirizzoDAODataSource.TABLE_NAME+ " WHERE (IDINDIRIZZO = ?)";
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, IDIndirizzo);
			
			result = preparedStatement.executeUpdate();
		}
		
		return (result != 0);
	}

	@Override
	public synchronized Collection<IndirizzoDTO> doRetrieveAll(String order, String username) throws SQLException {	//lista degli indirizzi dell'utente
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<IndirizzoDTO> addresses = new LinkedList<IndirizzoDTO>();

		String selectSQL = "SELECT * FROM (" + IndirizzoDAODataSource.TABLE_NAME + " NATURAL JOIN RISIEDE) WHERE USERNAME = ?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				IndirizzoDTO dto = new 	IndirizzoDTO();

				dto.setIDIndirizzo(rs.getInt("IDINDIRIZZO"));
				dto.setVia(rs.getString("VIA"));
				dto.setNumCivico(rs.getString("NUMCIVICO"));
				dto.setCittà(rs.getString("CITTA"));
				dto.setCap(rs.getString("CAP"));
				dto.setProvincia(rs.getString("PROVINCIA"));
				addresses.add(dto);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return addresses;
	}
}