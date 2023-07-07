package dao;

import java.sql.SQLException;
import java.util.Collection;

import model.WishlistDTO;
import model.ProductDTO;

public interface IBeanWishlistDAO {
	public void doSaveWishlist(WishlistDTO ws) throws SQLException;
	public boolean doDeleteWishlist(String username) throws SQLException;
	public WishlistDTO doRetrieveWishlistByKey(String username) throws SQLException;
	public void doSaveProduct(ProductDTO bean, WishlistDTO ws) throws SQLException;
	public boolean doDeleteProduct(int IDProduct, WishlistDTO ws) throws SQLException;
	public ProductDTO doRetrieveProductByKey(int IDProduct, WishlistDTO ws) throws SQLException;
	public Collection<ProductDTO> doRetrieveAll(String order, WishlistDTO ws) throws SQLException;
}
