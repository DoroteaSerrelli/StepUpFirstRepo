package model;

public class IndirizzoDTO {
	private int IDIndirizzo;
	private String username;
	private String via;
	private int numCivico;
	private String città;
	private int cap;
	private String provincia;
	

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
		return "IndirizzoDTO [via=" + via + ", numCivico=" + numCivico + ", città=" + città + ", cap=" + cap
				+ ", provincia=" + provincia + "]";
	}	
}