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

import dao.UserAccountDAODataSource;
import model.UserAccountDTO;

public class UserAccountDAODataSource implements IBeanDAO<UserAccountDTO>{
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
	public synchronized void doSave(UserAccountDTO user_account) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + UserAccountDAODataSource.TABLE_NAME
				+ " (USERNAME, USERPASSWORD, EMAIL) VALUES (?, ?, ?);";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user_account.getUsername());
			preparedStatement.setString(2, user_account.getUserPassword());
			preparedStatement.setString(3, user_account.getEmail());

			if(preparedStatement.executeUpdate() == 0) {
				System.out.println("Errore");
			}
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
	public synchronized boolean doDelete(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UserAccountDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";

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
	public synchronized Collection<UserAccountDTO> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserAccountDTO> users = new LinkedList<UserAccountDTO>();

		String selectSQL = "SELECT * FROM " + UserAccountDAODataSource.TABLE_NAME;
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UserAccountDTO dto = new UserAccountDTO();

				dto.setUsername(rs.getString("USERNAME"));
				dto.setUserPassword(rs.getString("USERPASSWORD"));
				dto.setEmail(rs.getString("EMAIL"));
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
	public synchronized UserAccountDTO doRetrieveByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserAccountDTO dto = new UserAccountDTO();
		String selectSQL = "SELECT * FROM " + UserAccountDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				dto.setUsername(rs.getString("USERNAME"));
				dto.setUserPasswordRetrieve(rs.getString("USERPASSWORD"));
				dto.setEmail(rs.getString("EMAIL"));
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