package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PersonDTO{

	private String username;
	private String userpassword;
	//private String nome, cognome, telefono, email, sesso;
	
	public PersonDTO() {
		this.username = "";
		this.userpassword = "";
		//nome = "";
		//cognome = "";
		//telefono = "";
		//email = "";
		//sesso = "";
	}
	
	public void setUsername(String username) {
		this.username = username;
		
	}
	public void setUserPassword(String pwd) {
		this.userpassword = hashPassword(pwd);
		
	}
	
	private String hashPassword(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
	/*public void setNome(String name) {
		this.nome = name;
		
	}
	public void setCognome(String surname) {
		this.cognome = surname;
		
	}
	public void setEmail(String email) {
		this.email = email;
		
	}
	public void setTelefono(String tel) {
		this.telefono = tel;
		
	}
	public void setSesso(String sex) {
		this.sesso = sex;
		
	}*/
	public String getUsername() {
		return username;
	}
	public String getUserPassword() {
		return userpassword;
	}
	
	/*public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getEmail() {
		return email;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getSesso() {
		return sesso;
	}
	
	public String toString() {
		return nome + " " + cognome + " (" + username + "), " + userpassword + " " + email + " " + telefono + " " + sesso;
	}
	*/
}
