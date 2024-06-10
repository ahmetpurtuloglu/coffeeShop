package tr.com.priper.coffeeshop;

import java.io.Serializable;

public class User implements Serializable{
	
	private String role;
	private String username;
	private String password;
	
	public User(String role, String username, String password) {
		this.role = role;
		this.username = username;
		this.password = password;
	}
	
	public boolean isWorker() {
		if (role.equals("Worker")) return true;
		return false;
	}
	
	public boolean isOwner() {
		if (role.equals("Owner")) return true;
		return false;
	}
	
	public boolean isCustomer() {
		if (role.equals("Customer")) return true;
		return false;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
