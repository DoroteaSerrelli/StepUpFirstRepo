package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.OrdineDTO;
import model.ProductDTO;


public class ProductDAODataSource implements IBeanIntDAO<ProductDTO>{
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

	@Override
	public synchronized Collection<ProductDTO> searching(String searchTerm) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductDTO> products = new LinkedList<ProductDTO>();

		String selectSQLName = "SELECT * FROM " + ProductDAODataSource.TABLE_NAME + " WHERE NOMEPRODOTTO LIKE ?";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQLName);
			preparedStatement.setString(1, "%" + searchTerm + "%");
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

		String selectSQLDescription = "SELECT * FROM " + ProductDAODataSource.TABLE_NAME + " WHERE ((DESCRIZIONE_BREVE LIKE ?) OR (DESCRIZIONE_DETTAGLIATA LIKE ?))";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQLDescription);
			preparedStatement.setString(1, "%" + searchTerm + "%");
			preparedStatement.setString(2, "%" + searchTerm + "%");
			
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

	public boolean updateData(int idProdotto, String campo, String valore) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;
		String updateSQL = "UPDATE " + ProductDAODataSource.TABLE_NAME;
		if(campo.equals("NOMEPRODOTTO")) {
			updateSQL +=  " SET NOMEPRODOTTO = ? WHERE IDPRODOTTO = ?";
		}
		if(campo.equals("DESCRIZIONE_BREVE")) {
			updateSQL +=  " SET DESCRIZIONE_BREVE = ? WHERE IDPRODOTTO = ?";
		}
		if(campo.equals("DESCRIZIONE_DETTAGLIATA")) {
			updateSQL +=  " SET DESCRIZIONE_DETTAGLIATA = ? WHERE IDPRODOTTO = ?";
		}
		if(campo.equals("BRAND")) {
			updateSQL +=  " SET BRAND = ? WHERE IDPRODOTTO = ?";
		}
		if(campo.equals("CATEGORIA")) {
			updateSQL +=  " SET CATEGORIA = ? WHERE IDPRODOTTO = ?";
		}
		
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, valore);
			preparedStatement.setInt(2, idProdotto);

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
	
	public boolean updatePrice(int idProdotto, float price) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "UPDATE " + ProductDAODataSource.TABLE_NAME + " SET PREZZO = ? WHERE IDPRODOTTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setFloat(1, price);
			preparedStatement.setInt(2, idProdotto);

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
	public Collection<OrdineDTO> doRetrieveForDate(Date startDate, Date endDate) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}