package dao;

import java.sql.SQLException;
import java.util.Collection;

public interface IBeanIntDAO <T>{
	public void doSave(T product) throws SQLException;
	boolean doDelete(int IDProduct) throws SQLException;
	public T doRetrieveByKey(int IDProduct) throws SQLException;
	public Collection<T> doRetrieveAll(String order) throws SQLException;
	Collection<T> searching(String searchTerm) throws SQLException;	
}