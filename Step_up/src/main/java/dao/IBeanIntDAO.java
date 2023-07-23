package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;

import model.OrdineDTO;
import model.ProductDTO;

public interface IBeanIntDAO <T>{
	public void doSave(T product) throws SQLException;
	boolean doDelete(int IDProduct) throws SQLException;
	public T doRetrieveByKey(int IDProduct) throws SQLException;
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	public Collection<T> searching(String searchTerm) throws SQLException;
	public Collection<OrdineDTO> doRetrieveForDate(Date startDate, Date endDate) throws SQLException;
	public Collection<ProductDTO> doRetrieveAllExistent(String order) throws SQLException;
	Collection<ProductDTO> doRetrieveAllDeleted(String order) throws SQLException;	
}