package model;

public class IndirizzoDTO {
	private int IDIndirizzo = 0;
	private String via = "";
	private String numCivico = "";
	private String città = "";
	private String cap = "";
	private String provincia = "";
	

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
	
	public String getNumCivico() {
		return numCivico;
	}
	
	public void setNumCivico(String numCivico) {
		this.numCivico = numCivico;
	}
	
	public String getCittà() {
		return città;
	}
	
	public void setCittà(String città) {
		this.città = città;
	}
	
	public String getCap() {
		return cap;
	}
	
	public void setCap(String string) {
		this.cap = string;
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