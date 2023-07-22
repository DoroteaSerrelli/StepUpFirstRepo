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
import model.WishlistDTO;

public class WishlistDAODataSource implements IBeanWishlistDAO{
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
	
	private static final String TABLE_NAME = "wishlist";
	
	
	@Override
	public synchronized void doSaveWishlist(WishlistDTO ws) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + WishlistDAODataSource.TABLE_NAME
				+ " (USERNAME, NUMPRODOTTI) VALUES (?, ?);";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ws.getUsername());
			preparedStatement.setInt(2, ws.getNumProdotti());

			if(preparedStatement.executeUpdate() == 0) {
				System.out.println("Errore");
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
		
	}


	@Override
	public synchronized boolean doDeleteWishlist(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + WishlistDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";

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
	public synchronized WishlistDTO doRetrieveWishlistByKey(String username) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		WishlistDTO dto = new WishlistDTO();
		String selectSQL = "SELECT * FROM " + WishlistDAODataSource.TABLE_NAME + " WHERE USERNAME = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				dto.setUsername(rs.getString("USERNAME"));
				dto.setNumProdotti(rs.getInt("NUMPRODOTTI"));
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
	public synchronized void doSaveProduct(ProductDTO bean, WishlistDTO ws) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		if(((doRetrieveWishlistByKey(ws.getUsername())).getUsername()).equals("")) {
			doSaveWishlist(ws);
		}
		
		String insertSQL = "INSERT INTO COSTITUITA (USERNAME, IDPRODOTTO) VALUES (?, ?);";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ws.getUsername());
			preparedStatement.setInt(2, bean.getIDProdotto());

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
		
		preparedStatement = null;
		String updateSQL = "UPDATE " + WishlistDAODataSource.TABLE_NAME+ " SET NUMPRODOTTI = ? WHERE USERNAME = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			System.out.println("NumeroProdotti add: "+(ws.getNumProdotti()+1));
			WishlistDTO wdto2 = doRetrieveWishlistByKey(ws.getUsername());
			preparedStatement.setInt(1, wdto2.getNumProdotti()+1);
			preparedStatement.setString(2, wdto2.getUsername());

			if(preparedStatement.executeUpdate() == 0) {
				System.out.println("Errore stringa");
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
	}

	@Override
	public synchronized boolean doDeleteProduct(int IDProduct, WishlistDTO ws) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(((doRetrieveWishlistByKey(ws.getUsername()).getUsername()).equals(""))) {
			return false;
		}
		
		if(ws.getNumProdotti() == 1)
			doDeleteWishlist(ws.getUsername());

		int result = 0;

		String deleteSQL = "DELETE FROM COSTITUITA WHERE (USERNAME = ? AND IDPRODOTTO = ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, ws.getUsername());
			preparedStatement.setInt(2, IDProduct);

			result = preparedStatement.executeUpdate();

			if(result == 0) {
				return false;
			}

		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		String updateSQL = "UPDATE " + WishlistDAODataSource.TABLE_NAME + " SET NUMPRODOTTI = ? WHERE USERNAME = ?";
		WishlistDTO wsUpdate = doRetrieveWishlistByKey(ws.getUsername());
		Connection connection2 = null;
		try {
			connection2 = ds.getConnection();
			preparedStatement = connection2.prepareStatement(updateSQL);
			preparedStatement.setInt(1, wsUpdate.getNumProdotti()-1);
			preparedStatement.setString(2, wsUpdate.getUsername());

			result = preparedStatement.executeUpdate();

			if(result == 0) {
				return false;
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection2.close();
			}
		}
		return (result != 0);
	}

	@Override
	public synchronized Collection<ProductDTO> doRetrieveAll(String order, WishlistDTO ws) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductDTO> products = new LinkedList<ProductDTO>();

		String selectSQL = "SELECT * FROM COSTITUITA NATURAL JOIN PRODOTTO WHERE USERNAME = ?";
		
		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1,  ws.getUsername());

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductDTO dto = new ProductDTO();

				dto.setIDProdotto(rs.getInt("IDPRODOTTO"));
				dto.setNomeProdotto(rs.getString("NOMEPRODOTTO"));
				dto.setDescrizione_breve(rs.getString("DESCRIZIONE_BREVE"));
				dto.setDescrizione_dettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));
				dto.setPrezzo(rs.getFloat("PREZZO"));
				dto.setCategoria(rs.getString("CATEGORIA"));
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
	public synchronized ProductDTO doRetrieveProductByKey(int IDProduct, WishlistDTO ws) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ProductDTO dto = new ProductDTO();
		String selectSQL = "SELECT * FROM COSTITUITA NATURAL JOIN PRODOTTO WHERE (USERNAME = ? AND IDProdotto = ?)";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, ws.getUsername());
			preparedStatement.setInt(2, IDProduct);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				dto.setIDProdotto(rs.getInt("IDPRODOTTO"));
				dto.setNomeProdotto(rs.getString("NOMEPRODOTTO"));
				dto.setDescrizione_breve(rs.getString("DESCRIZIONE_BREVE"));
				dto.setDescrizione_dettagliata(rs.getString("DESCRIZIONE_DETTAGLIATA"));
				dto.setPrezzo(rs.getFloat("PREZZO"));
				dto.setCategoria(rs.getString("CATEGORIA"));
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