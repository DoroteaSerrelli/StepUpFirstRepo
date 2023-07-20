package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.PagamentoDTO;

public interface IBeanPagamentoDAO {
	public void doSave(PagamentoDTO payment) throws SQLException;
	boolean doDelete(int IDPagamento) throws SQLException;
	public PagamentoDTO doRetrieveByKey(int IDPagamento) throws SQLException;
	public Collection<PagamentoDTO> doRetrieveAll(String order) throws SQLException;
}
