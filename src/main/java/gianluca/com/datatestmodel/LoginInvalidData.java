package gianluca.com.datatestmodel;

public class LoginInvalidData {

    private String email;
    private String password;

    // Usato SOLO per messaggi applicativi (backend)
    private String expectedMessage;

    // Usato SOLO per validazioni HTML5 del browser
    private String expectedErrorType;

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

    public String getExpectedErrorType() {
        return expectedErrorType;
    }

    public void setExpectedErrorType(String expectedErrorType) {
        this.expectedErrorType = expectedErrorType;
    }

    public boolean hasExpectedMessage() {
        return expectedMessage != null && !expectedMessage.isBlank();
    }

    public boolean hasExpectedErrorType() {
        return expectedErrorType != null && !expectedErrorType.isBlank();
    }

    @Override
    public String toString() {
        return "LoginInvalidData{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", expectedMessage='" + expectedMessage + '\'' +
                ", expectedErrorType='" + expectedErrorType + '\'' +
                '}';
    }
}
