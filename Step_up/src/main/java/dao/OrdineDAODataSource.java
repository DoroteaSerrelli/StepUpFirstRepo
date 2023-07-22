package dao;

import model.ItemCarrello;
import model.OrdineDTO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Collection;
import java.util.LinkedList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDAODataSource implements IBeanOrdineDAO{
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

	private static final String TABLE_NAME = "ordine";

	@Override
	public synchronized void doSave(OrdineDTO order) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + OrdineDAODataSource.TABLE_NAME
				+ " (IDORDINE, USERNAME, METODOSPEDIZIONE, METODOCONSEGNA, DATAORDINE, ORA) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, order.getIDOrdine());
			preparedStatement.setString(2, order.getUsername());
			preparedStatement.setString(3, order.getMetSpedizione());
			preparedStatement.setString(4, order.getMetConsegna());
			preparedStatement.setDate(5, new java.sql.Date(order.getDataOrdine().getTime()));
			preparedStatement.setTime(6, Time.valueOf(order.getOraOrdine()));
			
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
	public synchronized OrdineDTO doRetrieveByKey(int IDOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineDTO dto = new OrdineDTO();

		String selectSQL = "SELECT * FROM " + OrdineDAODataSource.TABLE_NAME + " WHERE IDORDINE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDOrdine);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				dto.setIDOrdine(rs.getInt("IDORDINE"));
				dto.setUsername(rs.getString("USERNAME"));
				dto.setMetSpedizione(rs.getString("METODOSPEDIZIONE"));
				dto.setMetConsegna(rs.getString("METODOCONSEGNA"));
				dto.setDataOrdine(rs.getDate("DATAORDINE"));
				dto.setOraOrdine((rs.getTime("ORA")).toLocalTime());
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
	public synchronized boolean doDelete(int IDOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAODataSource.TABLE_NAME + " WHERE IDORDINE = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, IDOrdine);

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
	public synchronized Collection<OrdineDTO> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineDTO> ordini = new LinkedList<OrdineDTO>();

		String selectSQL = "SELECT * FROM " + OrdineDAODataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;	//ordine degli ordini
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				OrdineDTO dto = new OrdineDTO();

				dto.setIDOrdine(rs.getInt("IDORDINE"));
				dto.setUsername(rs.getString("USERNAME"));
				dto.setMetSpedizione(rs.getString("METODOSPEDIZIONE"));
				dto.setMetConsegna(rs.getString("METODOCONSEGNA"));
				dto.setDataOrdine(rs.getDate("DATAORDINE"));
				dto.setOraOrdine((rs.getTime("ORA")).toLocalTime());
				ordini.add(dto);
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
		return ordini;
	}

	@Override
	public synchronized Collection<OrdineDTO> doRetrieveForDate(Date startDate, Date endDate) throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    Collection<OrdineDTO> ordini = new LinkedList<OrdineDTO>();

	    String selectSQL = "SELECT * FROM " + OrdineDAODataSource.TABLE_NAME + " WHERE (DATAORDINE BETWEEN ? AND ?)";
	    System.out.println("START: "+ startDate);
	    try {
	        connection = ds.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        preparedStatement.setDate(1, startDate);
	        preparedStatement.setDate(2, endDate);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            OrdineDTO dto = new OrdineDTO();

	            dto.setIDOrdine(rs.getInt("IDORDINE"));
	            dto.setUsername(rs.getString("USERNAME"));
	            dto.setMetSpedizione(rs.getString("METODOSPEDIZIONE"));
	            dto.setMetConsegna(rs.getString("METODOCONSEGNA"));
	            dto.setDataOrdine(rs.getDate("DATAORDINE"));
	            dto.setOraOrdine((rs.getTime("ORA")).toLocalTime());
	            ordini.add(dto);
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
	    return ordini;
	}


	@Override
	public synchronized Collection<OrdineDTO> doRetrieveForUser(String user) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineDTO> ordini = new LinkedList<OrdineDTO>();

		String selectSQL = "SELECT * FROM " + OrdineDAODataSource.TABLE_NAME + " WHERE (USERNAME = ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, user);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				OrdineDTO dto = new OrdineDTO();

				dto.setIDOrdine(rs.getInt("IDORDINE"));
				dto.setUsername(rs.getString("USERNAME"));
				dto.setMetSpedizione(rs.getString("METODOSPEDIZIONE"));
				dto.setMetConsegna(rs.getString("METODOCONSEGNA"));
				dto.setDataOrdine(rs.getDate("DATAORDINE"));
				dto.setOraOrdine((rs.getTime("ORA")).toLocalTime());
				ordini.add(dto);
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
		return ordini;
	}

	@Override
	public synchronized Collection<ItemCarrello> doRetrieveAllProducts(int IDOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ItemCarrello> prodotti = new LinkedList<ItemCarrello>();

		String selectSQL = "SELECT * FROM COMPOSIZIONE NATURAL JOIN PRODOTTO WHERE (IDORDINE = ?)";
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDOrdine);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				ItemCarrello dto = new ItemCarrello();

				dto.setIDProdotto(rs.getInt("IDPRODOTTO"));
				dto.setNomeProdotto(rs.getString("NOMEPRODOTTO"));
				dto.setPrezzo(rs.getFloat("PREZZOACQUISTO"));
				dto.setQuantità(rs.getInt("QUANTITà"));
				dto.setCategoria(rs.getString("CATEGORIA"));
				dto.setBrand(rs.getString("BRAND"));
				prodotti.add(dto);
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
		return prodotti;
	}

	@Override
	public synchronized boolean addProducttoOrder(int IDOrdine, ItemCarrello product) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ProductDAODataSource productdao = new ProductDAODataSource();
		String selectSQL = "INSERT INTO COMPOSIZIONE (IDORDINE, IDPRODOTTO, QUANTITà, PREZZOACQUISTO) VALUES(?, ?, ?, ?)";
		
		if(productdao.doRetrieveByKey(product.getIDProdotto()) == null)
			return false;
		
		int rs = 0;
		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDOrdine);
			preparedStatement.setInt(2, product.getIDProdotto());
			preparedStatement.setInt(3, product.getQuantità());
			preparedStatement.setFloat(4, product.getPrezzo());

			rs = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (rs!=0);
	}

	@Override
	public synchronized Collection<OrdineDTO> searching(String searchTerm) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<OrdineDTO> doRetrieveForDate(java.util.Date startDate, java.util.Date endDate)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}