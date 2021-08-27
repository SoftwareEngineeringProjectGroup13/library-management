package dto;

public class LoginDTO {
	private String username, password, userType;
	private int id;

	public LoginDTO() {
	}

	public LoginDTO(String username, String password, String userType, int id) {
		this.username = username;
		this.password = password;
		this.userType = userType;
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
