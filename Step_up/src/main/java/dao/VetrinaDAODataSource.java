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

import model.ProductDTO;
import model.VetrinaDTO;

public class VetrinaDAODataSource implements IBeanVetrinaDAO{

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

	private static final String TABLE_NAME = "vetrina";

	@Override
	public synchronized void doSave(VetrinaDTO view, int code) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + VetrinaDAODataSource.TABLE_NAME
				+ " (IDVETRINA, NOMEVETRINA) VALUES (?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, view.getIDVetrina());
			preparedStatement.setString(2, view.getNomeVetrina());

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
		
		String insertSQL2 = "INSERT INTO EVIDENZA (IDPRODOTTO, IDVETRINA) VALUES (?, ?)";
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL2);
			preparedStatement.setInt(1, code);
			preparedStatement.setString(2, view.getNomeVetrina());

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
	public synchronized boolean doDelete(int IDView) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + VetrinaDAODataSource.TABLE_NAME + " WHERE (IDVETRINA = ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, IDView);

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
	public synchronized VetrinaDTO doRetrieveByKey(int IDView) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		VetrinaDTO dto = new VetrinaDTO();

		String selectSQL = "SELECT * FROM " + VetrinaDAODataSource.TABLE_NAME + " WHERE (IDVETRINA = ?) ";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDView);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				dto.setIDVetrina(rs.getInt("IDVETRINA"));
				dto.setNomeVetrina(rs.getString("NOMEVETRINA"));
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
	public synchronized Collection<VetrinaDTO> doRetrieveAll(String order) throws SQLException {	//tutte le vetrine del sito
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<VetrinaDTO> vetrine = new LinkedList<VetrinaDTO>();

		String selectSQL = "SELECT * FROM " + VetrinaDAODataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				VetrinaDTO dto = new VetrinaDTO();

				dto.setIDVetrina(rs.getInt("IDVETRINA"));
				dto.setNomeVetrina(rs.getString("NOMEVETRINA"));
				vetrine.add(dto);
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
		return vetrine;
	}

	@Override
	public void addProduct(VetrinaDTO view, int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ProductDTO product = new ProductDTO();
		ProductDAODataSource productdao = new ProductDAODataSource();
		product = productdao.doRetrieveByKey(code);
		if(product != null) {
			String SQLinsert = "INSERT INTO EVIDENZA(IDPRODOTTO, IDVETRINA) VALUES (?, ?)";
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(SQLinsert);
				
				preparedStatement.setInt(1, product.getIDProdotto());
				preparedStatement.setInt(2, view.getIDVetrina());
				
				preparedStatement.executeUpdate();
				connection.setAutoCommit(false);
				connection.commit();				
			}finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
		}
	}

	@Override
	public void deleteProduct(VetrinaDTO view, int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ProductDTO product = new ProductDTO();
		ProductDAODataSource productdao = new ProductDAODataSource();
		product = productdao.doRetrieveByKey(code);
		if(product != null) {
			String SQLinsert = "DELETE FROM EVIDENZA WHERE (IDVETRINA = ? AND IDPRODOTTO = ?)";
			try {
				connection = ds.getConnection();
				preparedStatement = connection.prepareStatement(SQLinsert);
				
				preparedStatement.setInt(1, view.getIDVetrina());
				preparedStatement.setInt(2, product.getIDProdotto());
				
				preparedStatement.executeUpdate();
				connection.setAutoCommit(false);
				connection.commit();				
			}finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						connection.close();
				}
			}
			
		}
	}

	@Override
	public Collection<ProductDTO> doRetrieveAllProducts(String order, int IDView) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductDTO> products = new LinkedList<ProductDTO>();

		String selectSQL = "SELECT * FROM EVIDENZA WHERE IDVETRINA = ?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDView);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();
				ProductDAODataSource pdao = new ProductDAODataSource();
				dto.setIDProdotto(rs.getInt("IDPRODOTTO"));
				dto = pdao.doRetrieveByKey(dto.getIDProdotto());
				products.add(dto);
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
		return products;
	}
}