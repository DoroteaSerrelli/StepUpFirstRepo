package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.ProductDTO;

public interface IBeanProductDAO {
	public void doSave(ProductDTO product) throws SQLException;
	boolean doDelete(int IDProduct) throws SQLException;
	public ProductDTO doRetrieveByKey(int IDProduct) throws SQLException;
	public Collection<ProductDTO> doRetrieveAll(String order) throws SQLException;	
}