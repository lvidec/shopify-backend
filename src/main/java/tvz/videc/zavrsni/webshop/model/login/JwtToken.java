package tvz.videc.zavrsni.webshop.model.login;

public class JwtToken {

    private String token;

    public JwtToken(final String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

}
