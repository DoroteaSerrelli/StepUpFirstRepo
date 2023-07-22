package model;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserAccountDTO{

	private String username;
	private String userpassword;
	
	public UserAccountDTO() {
		this.username = "";
		this.userpassword = "";
	}
	
	public void setUsername(String username) {
		this.username = username;
		
	}
	public void setUserPassword(String pwd) {
		this.userpassword = hashPassword(pwd);
	}
	
	private String hashPassword(String password) {
        String hashString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            hashString = "";
            for (int i = 0; i < bytes.length; i++) {
                hashString += Integer.toHexString((bytes[i] & 0xFF) | 0x100).toLowerCase().substring(1,3);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return hashString;
    }
	
	public void setUserPasswordRetrieve(String hashpwd) {
		this.userpassword = hashpwd;
		
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getUserPassword() {
		return userpassword;
	}
	
}
