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
}