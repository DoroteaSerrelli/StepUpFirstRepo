package model;

public class IndirizzoDTO {
	private int IDIndirizzo;
	private String via;
	private int numCivico;
	private String città;
	private int cap;
	private String provincia;
	


	public int getIDIndirizzo() {
		return IDIndirizzo;
	}

	public void setIDIndirizzo(int iDIndirizzo) {
		IDIndirizzo = iDIndirizzo;
	}

	public String getVia() {
		return via;
	}
	
	public void setVia(String via) {
		this.via = via;
	}
	
	public int getNumCivico() {
		return numCivico;
	}
	
	public void setNumCivico(int numCivico) {
		this.numCivico = numCivico;
	}
	
	public String getCittà() {
		return città;
	}
	
	public void setCittà(String città) {
		this.città = città;
	}
	
	public int getCap() {
		return cap;
	}
	
	public void setCap(int cap) {
		this.cap = cap;
	}
	
	public String getProvincia() {
		return provincia;
	}
	
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	@Override
	public String toString() {
		return "Indirizzo [via=" + via + ", numCivico=" + numCivico + ", città=" + città + ", cap=" + cap
				+ ", provincia=" + provincia + "]";
	}	
}