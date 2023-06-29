package model;

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

public class ProfileDAODataSource implements IBeanDAO<ProfileDTO> {

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

	private static final String TABLE_NAME = "dati_anagrafici";

	@Override
	public synchronized void doSave(ProfileDTO user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProfileDAODataSource.TABLE_NAME
				+ " (USERNAME, NOME, COGNOME, TELEFONO, SESSO) VALUES (?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getCognome());
			preparedStatement.setString(4, user.getTelefono());
			preparedStatement.setString(5, user.getSesso());

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
	public synchronized ProfileDTO doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProfileDTO dto = new ProfileDTO();

		String selectSQL = "SELECT * FROM " + ProfileDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dto.setUsername(rs.getString("USERNAME"));
				dto.setNome(rs.getString("NOME"));
				dto.setCognome(rs.getString("COGNOME"));
				dto.setTelefono(rs.getString("TELEFONO"));
				dto.setSesso(rs.getString("SESSO"));
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
	public synchronized boolean doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProfileDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, username);

			result = preparedStatement.executeUpdate();

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
	public synchronized Collection<ProfileDTO> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProfileDTO> users = new LinkedList<ProfileDTO>();

		String selectSQL = "SELECT * FROM " + ProfileDAODataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProfileDTO dto = new ProfileDTO();

				dto.setUsername(rs.getString("USERNAME"));
				dto.setNome(rs.getString("NOME"));
				dto.setCognome(rs.getString("COGNOME"));
				dto.setTelefono(rs.getString("TELEFONO"));
				dto.setSesso(rs.getString("SESSO"));
				users.add(dto);
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
		return users;
	}
}