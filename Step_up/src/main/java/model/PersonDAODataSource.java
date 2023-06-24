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

import model.PersonDAODataSource;

public class PersonDAODataSource implements IBeanDAO<PersonDTO>{
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
	
	private static final String TABLE_NAME = "user_account";
	
	@Override
	public synchronized void doSave(PersonDTO user_account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PersonDAODataSource.TABLE_NAME
				+ " (USERNAME, USERPASSWORD) VALUES (?, ?);";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user_account.getUsername());
			preparedStatement.setString(2, user_account.getUserPassword());
			/*preparedStatement.setString(3, user_account.getNome());
			preparedStatement.setString(4, user_account.getCognome());
			preparedStatement.setString(5, user_account.getEmail());
			preparedStatement.setString(6, user_account.getTelefono());
			preparedStatement.setString(7, user_account.getSesso());*/

			if(preparedStatement.executeUpdate() == 0) {
				System.out.println("Errore");
			}

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
	public synchronized boolean doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PersonDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";

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
	public synchronized Collection<PersonDTO> doRetrieveAll(String telefono) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PersonDTO> users = new LinkedList<PersonDTO>();

		String selectSQL = "SELECT * FROM " + PersonDAODataSource.TABLE_NAME;

		/*if (telefono != null && !telefono.equals("")) {
			selectSQL += " ORDER BY " + telefono;
		}*/

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				PersonDTO dto = new PersonDTO();

				dto.setUsername(rs.getString("USERNAME"));
				dto.setUserPassword(rs.getString("USERPASSWORD"));
				/*bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setTelefono(rs.getString("TELEFONO"));
				bean.setSesso(rs.getString("SESSO"));*/
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
	
	@Override
	public synchronized PersonDTO doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PersonDTO dto = new PersonDTO();
		String selectSQL = "SELECT * FROM " + PersonDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				dto.setUsername(rs.getString("USERNAME"));
				/*bean.setNome(rs.getString("NOME"));
				bean.setCognome(rs.getString("COGNOME"));
				bean.setEmail(rs.getString("EMAIL"));
				bean.setTelefono(rs.getString("TELEFONO"));
				bean.setSesso(rs.getString("SESSO"));*/
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
	
}
