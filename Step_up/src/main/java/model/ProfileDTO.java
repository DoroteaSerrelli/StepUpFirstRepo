package model;

public class ProfileDTO{

	private String username;
	private String nome, cognome, telefono;
	private String email;
	
	private String sesso;
	
	public ProfileDTO() {
		this.username = "";
		this.nome = "";
		this.cognome = "";
		this.telefono = "";
		this.sesso = "";
		this.email = "";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public void setEmail(String email) {
		this.email = email;
		
	}
	public String getEmail() {
		return email;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	
	@Override
	public String toString() {
		return "" + nome + " " + cognome;
	}
}