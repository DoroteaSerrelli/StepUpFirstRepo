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


public class ProductDAODataSource implements IBeanProductDAO{
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

	private static final String TABLE_NAME = "prodotto";

	@Override
	public synchronized void doSave(ProductDTO product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductDAODataSource.TABLE_NAME
				+ " (IDPRODOTTO, NOMEPRODOTTO, DESCRIZIONE_BREVE, DESCRIZIONE_DETTAGLIATA, PREZZO, CATEGORIA, BRAND, TOPIMAGE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getIDProdotto());
			preparedStatement.setString(2, product.getNomeProdotto());
			preparedStatement.setString(3, product.getDescrizione_breve());
			preparedStatement.setString(4, product.getDescrizione_dettagliata());
			preparedStatement.setFloat(5, product.getPrezzo());
			preparedStatement.setString(6, product.getCategoria());
			preparedStatement.setString(7, product.getBrand());
			preparedStatement.setBytes(8, product.getTopImage());

			
			System.out.println("Righe aggiornate: " + preparedStatement.executeUpdate());
			
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
	public synchronized ProductDTO doRetrieveByKey(int IDProduct) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		ProductDTO dto = new ProductDTO();

		String selectSQL = "SELECT * FROM " + ProductDAODataSource.TABLE_NAME + " WHERE IDPRODOTTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDProduct);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				dto.setIDProdotto(rs.getInt("IDPRODOTTO"));
				dto.setNomeProdotto(rs.getString("NOMEPRODOTTO"));
				dto.setDescrizione_breve(rs.getString("DESCRIZIONE_BREVE"));
				dto.setDescrizione_dettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));
				dto.setPrezzo(rs.getFloat("PREZZO"));
				dto.setCategoria(rs.getString("CATEGORIA"));
				dto.setBrand(rs.getString("BRAND"));
				dto.setTopImage(rs.getBytes("TOPIMAGE"));
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
	public synchronized boolean doDelete(int IDProduct) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDAODataSource.TABLE_NAME + " WHERE IDPRODOTTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, IDProduct);

			result = preparedStatement.executeUpdate();
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
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProductDTO> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductDTO> products = new LinkedList<ProductDTO>();

		String selectSQL = "SELECT * FROM " + ProductDAODataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;	//ordine dei prodotti per nome
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ProductDTO dto = new ProductDTO();

				dto.setIDProdotto(rs.getInt("IDPRODOTTO"));
				dto.setNomeProdotto(rs.getString("NOMEPRODOTTO"));
				dto.setDescrizione_breve(rs.getString("DESCRIZIONE_BREVE"));
				dto.setDescrizione_dettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));
				dto.setPrezzo(rs.getFloat("PREZZO"));
				dto.setCategoria(rs.getString("CATEGORIA"));
				dto.setBrand(rs.getString("BRAND"));
				dto.setTopImage(rs.getBytes("TOPIMAGE"));
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