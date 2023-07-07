package model;

public class WishlistDTO{
	private String username;
	private int numProdotti;
	
	public WishlistDTO() {
		this.username = "";
		this.numProdotti = 0;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public void setNumProdotti(int numProdotti) {
		this.numProdotti = numProdotti;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getNumProdotti() {
		return numProdotti;
	}
}