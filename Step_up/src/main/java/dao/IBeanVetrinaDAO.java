package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.ProductDTO;
import model.VetrinaDTO;

public interface IBeanVetrinaDAO {
	public void addProduct(VetrinaDTO view, int code) throws SQLException;
	public void deleteProduct(VetrinaDTO view, int code) throws SQLException;
	public void doSave(VetrinaDTO view, int code) throws SQLException;
	boolean doDelete(int IDView) throws SQLException;
	public VetrinaDTO doRetrieveByKey(int IDView) throws SQLException;
	public Collection<ProductDTO> doRetrieveAllProducts(String order, int IDView) throws SQLException;	//tutti i prodotti di una vetrina
	public Collection<VetrinaDTO> doRetrieveAll(String order) throws SQLException;	//tutte le vetrine
	
}
