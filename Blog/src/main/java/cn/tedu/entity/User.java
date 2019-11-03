package cn.tedu.entity;

public class User {
	private int oId;
	private String username;
	private String password;
	private String email;
	public int getoId() {
		return oId;
	}
	public void setoId(int oId) {
		this.oId = oId;
	}
	public String getUsername() {
		return username;
	}
	public void setUserName(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [oId=" + oId + ", userName=" + username + ", password=" + password + ", email=" + email + "]";
	}
	public User(int oId, String username, String password, String email) {
		super();
		this.oId = oId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
}
