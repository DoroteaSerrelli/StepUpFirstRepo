package model;
import java.util.Date;
import java.time.LocalTime;

public class PagamentoDTO {
	private int IDPagamento;
	private int IDOrdine;
	private Date DataPagamento;
	private LocalTime oraPagamento;
	private String MetodoPagamento;
	private float Importo;
	
	
	public int getIDPagamento() {
		return IDPagamento;
	}
	
	public void setIDPagamento(int iDPagamento) {
		IDPagamento = iDPagamento;
	}
	
	public int getIDOrdine() {
		return IDOrdine;
	}
	
	public void setIDOrdine(int iDOrdine) {
		IDOrdine = iDOrdine;
	}
	
	public Date getDataPagamento() {
		return DataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		DataPagamento = dataPagamento;
	}
	
	public LocalTime getOraPagamento() {
		return oraPagamento;
	}
	
	public void setOraPagamento(LocalTime oraPagamento) {
		this.oraPagamento = oraPagamento;
	}
	
	public String getMetodoPagamento() {
		return MetodoPagamento;
	}
	
	public void setMetodoPagamento(String metodoPagamento) {
		MetodoPagamento = metodoPagamento;
	}
	
	public float getImporto() {
		return Importo;
	}
	
	public void setImporto(float importo) {
		Importo = importo;
	}

	@Override
	public String toString() {
		return "PagamentoDTO [IDPagamento=" + IDPagamento + ", IDOrdine=" + IDOrdine + ", DataPagamento="
				+ DataPagamento + ", oraPagamento=" + oraPagamento + ", MetodoPagamento=" + MetodoPagamento
				+ ", Importo=" + Importo + "]";
	}
}
