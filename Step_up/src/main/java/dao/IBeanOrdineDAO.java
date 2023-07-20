package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import model.ItemCarrello;
import model.OrdineDTO;

public interface IBeanOrdineDAO extends IBeanIntDAO<OrdineDTO>{
	public Collection<OrdineDTO> doRetrieveForDate(Date startDate,Date endDate) throws SQLException;	
	public Collection<OrdineDTO> doRetrieveForUser(String user) throws SQLException;
	public Collection<ItemCarrello> doRetrieveAllProducts(int IDOrdine) throws SQLException;
	public boolean addProducttoOrder(int IDOrdine, ItemCarrello product) throws SQLException;
}
