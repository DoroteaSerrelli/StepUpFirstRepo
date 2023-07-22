package dao;

import java.sql.Connection;
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

import model.PagamentoDTO;

public class PagamentoDAODataSource implements IBeanPagamentoDAO{
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

	private static final String TABLE_NAME = "pagamento";
	
	@Override
	public void doSave(PagamentoDTO payment) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + PagamentoDAODataSource.TABLE_NAME
				+ " (IDPAGAMENTO, IDORDINE, DATAPAG, ORAPAG, METODOPAG, IMPORTO) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, payment.getIDPagamento());
			preparedStatement.setInt(2, payment.getIDOrdine());
			preparedStatement.setDate(3, new java.sql.Date(payment.getDataPagamento().getTime()));
			preparedStatement.setTime(4, Time.valueOf(payment.getOraPagamento()));
			preparedStatement.setString(5, payment.getMetodoPagamento());
			preparedStatement.setFloat(6, payment.getImporto());

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
	public boolean doDelete(int IDPagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + PagamentoDAODataSource.TABLE_NAME + " WHERE IDPAGAMENTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, IDPagamento);

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
	public PagamentoDTO doRetrieveByKey(int IDPagamento) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PagamentoDTO dto = new PagamentoDTO();

		String selectSQL = "SELECT * FROM " + PagamentoDAODataSource.TABLE_NAME + " WHERE IDPAGAMENTO = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDPagamento);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				dto.setIDOrdine(rs.getInt("IDORDINE"));
				dto.setIDPagamento(rs.getInt("IDPAGAMENTO"));
				dto.setDataPagamento(rs.getDate("DATAPAG"));
				dto.setOraPagamento((rs.getTime("ORAPAG")).toLocalTime());
				dto.setImporto(rs.getFloat("IMPORTO"));
				dto.setMetodoPagamento(rs.getString("METODOPAG"));
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
	public Collection<PagamentoDTO> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<PagamentoDTO> pagamenti = new LinkedList<PagamentoDTO>();

		String selectSQL = "SELECT * FROM " + PagamentoDAODataSource.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				PagamentoDTO dto = new PagamentoDTO();

				dto.setIDOrdine(rs.getInt("IDORDINE"));
				dto.setIDPagamento(rs.getInt("IDPAGAMENTO"));
				dto.setDataPagamento(rs.getDate("DATAPAG"));
				dto.setOraPagamento((rs.getTime("ORAPAG")).toLocalTime());
				dto.setImporto(rs.getFloat("IMPORTO"));
				dto.setMetodoPagamento(rs.getString("METODOPAG"));
				pagamenti.add(dto);
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
		return pagamenti;
	}

	@Override
	public PagamentoDTO doRetrieveByOrderID(int IDOrdine) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		PagamentoDTO pagamento = new PagamentoDTO();

		String selectSQL = "SELECT * FROM " + PagamentoDAODataSource.TABLE_NAME + " WHERE IDORDINE = ?";
		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, IDOrdine);

			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				PagamentoDTO dto = new PagamentoDTO();

				dto.setIDOrdine(rs.getInt("IDORDINE"));
				dto.setIDPagamento(rs.getInt("IDPAGAMENTO"));
				dto.setDataPagamento(rs.getDate("DATAPAG"));
				dto.setOraPagamento((rs.getTime("ORAPAG")).toLocalTime());
				dto.setImporto(rs.getFloat("IMPORTO"));
				dto.setMetodoPagamento(rs.getString("METODOPAG"));
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
		return pagamento;
	}
}