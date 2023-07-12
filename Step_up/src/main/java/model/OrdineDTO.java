package model;
import java.util.Date;
import java.time.LocalTime;

public class OrdineDTO {
	private int IDOrdine = -1;
	private String username = "";
	private String metSpedizione = "";
	private String metConsegna = "";
	private Date dataOrdine = null;
	private LocalTime oraOrdine = null;
	
	
	public int getIDOrdine() {
		return IDOrdine;
	}
	
	public void setIDOrdine(int iDOrdine) {
		IDOrdine = iDOrdine;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getMetSpedizione() {
		return metSpedizione;
	}
	
	public void setMetSpedizione(String metSpedizione) {
		this.metSpedizione = metSpedizione;
	}
	
	public String getMetConsegna() {
		return metConsegna;
	}
	
	public void setMetConsegna(String metConsegna) {
		this.metConsegna = metConsegna;
	}
	
	public Date getDataOrdine() {
		return dataOrdine;
	}
	
	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	public LocalTime getOraOrdine() {
		return oraOrdine;
	}
	
	public void setOraOrdine(LocalTime oraOrdine) {
		this.oraOrdine = oraOrdine;
	}

	@Override
	public String toString() {
		return "OrdineDTO [IDOrdine=" + IDOrdine + ", username=" + username + ", metSpedizione=" + metSpedizione
				+ ", metConsegna=" + metConsegna + ", dataOrdine=" + dataOrdine + ", oraOrdine=" + oraOrdine + "]";
	}
	
}