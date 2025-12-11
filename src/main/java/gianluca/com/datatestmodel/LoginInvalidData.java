package gianluca.com.datatestmodel;

public class LoginInvalidData {

	private String email;
	private String password;
	private String expectedMessage;

	public LoginInvalidData() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExpectedMessage() {
		return expectedMessage;
	}

	public void setExpectedMessage(String expectedMessage) {
		this.expectedMessage = expectedMessage;
	}

	@Override
	public String toString() {
		return "LoginInvalidData{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", expectedMessage='"
				+ expectedMessage + '\'' + '}';
	}
}
