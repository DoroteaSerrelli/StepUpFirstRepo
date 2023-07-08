package model;

import java.util.ArrayList;
import java.util.List;

public class Carrello {

	private List<ItemCarrello> products;
	
	public Carrello() {
		products = new ArrayList<ItemCarrello>();
	}
	
	public void addProduct(ItemCarrello product) {
		if(products == null) {
			products.add(product);
			
		}else {
			boolean exist = false;
			for(ItemCarrello c: products) {
				if(c.getIDProdotto() == product.getIDProdotto()) {
					exist = true;
					ItemCarrello cart2 = c;
					products.remove(c);
					cart2.setQuantità(1+cart2.getQuantità());
					products.add(cart2);
					break;
				}
			}
			
			if(!exist) {
				products.add(product);
			}
		}
	}
	
	public void deleteProduct(ItemCarrello product) {
		for(ItemCarrello prod : products) {
			if(prod.getIDProdotto() == product.getIDProdotto()) {
				products.remove(prod);
				break;
			}
		}
 	}
	
	public List<ItemCarrello> getProducts() {
		return  products;
	}
	
	public int getNumProdotti() {
		return products.size();
	}
}