package model;

import java.sql.SQLException;
import java.util.Collection;

public interface IBeanDAO<T>{
	public void doSave(T bean) throws SQLException;
	public boolean doDelete(String username) throws SQLException;
	public T doRetrieveByKey(String username) throws SQLException;
	public Collection<T> doRetrieveAll(String telefono) throws SQLException;
}