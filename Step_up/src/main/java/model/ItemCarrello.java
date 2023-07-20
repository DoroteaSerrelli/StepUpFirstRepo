package model;

public class ItemCarrello extends ProductDTO{
	
	private int quantità = 0;
	
	public ItemCarrello() {
		
	}

	public int getQuantità() {
		return quantità;
	}

	public void setQuantità(int quantità) {
		this.quantità = quantità;
	}
	
	@Override
	public String toString() {
		return "Prodotto [IDProdotto=\" + IDProdotto + \", NomeProdotto=\" + NomeProdotto + \", Prezzo=\" + Prezzo\r\n"
				+ "\", Categoria=\" + Categoria + \", Brand=\" + Brand + \" quantità=" + quantità + "]";
	}
}