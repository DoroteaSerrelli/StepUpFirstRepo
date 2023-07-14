package model;

public class ProductDTO{
	private int IDProdotto;
	private String NomeProdotto;
	private String Descrizione_breve;
	private String Descrizione_dettagliata;
	private float Prezzo;
	private String Categoria;
	private String Brand;
	private byte[] TopImage;

	public ProductDTO() {

		IDProdotto = 0;
		NomeProdotto = "";
		Descrizione_breve = "";
		Descrizione_dettagliata = "";
		Prezzo = 0;
		Categoria = "";
		Brand = "";
		TopImage = null;
	}
	
	
	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}

	public byte[] getTopImage() {
		return TopImage;
	}

	public void setTopImage(byte[] photo) {
		this.TopImage = photo;
	}
	
	public String getCategoria() {
		return Categoria;
	}

	public int getIDProdotto() {
		return IDProdotto;
	}
	
	public void setIDProdotto(int iDProdotto) {
		this.IDProdotto = iDProdotto;
	}
	
	public String getNomeProdotto() {
		return NomeProdotto;
	}
	
	public void setNomeProdotto(String nomeProdotto) {
		this.NomeProdotto = nomeProdotto;
	}
	
	public String getDescrizione_breve() {
		return Descrizione_breve;
	}
	
	public void setDescrizione_breve(String descrizione_breve) {
		this.Descrizione_breve = descrizione_breve;
	}
	
	public String getDescrizione_dettagliata() {
		return Descrizione_dettagliata;
	}
	
	public void setDescrizione_dettagliata(String descrizione_dettagliata) {
		this.Descrizione_dettagliata = descrizione_dettagliata;
	}
	
	public float getPrezzo() {
		return Prezzo;
	}
	
	public void setPrezzo(float prezzo) {
		this.Prezzo = prezzo;
	}
	
	public void setCategoria(String categoria) {
		this.Categoria = categoria;
	}
}