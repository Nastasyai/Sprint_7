package courier;

public class LoginCourier {
    public String login;
    public String password;

    public LoginCourier(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static LoginCourier from(CreateCourier createCourier) {
        return new LoginCourier(createCourier.getLogin(), createCourier.getPassword());
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
