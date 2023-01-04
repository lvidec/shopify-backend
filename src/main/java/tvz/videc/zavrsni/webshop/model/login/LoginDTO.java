package tvz.videc.zavrsni.webshop.model.login;

import javax.validation.constraints.NotNull;

public class LoginDTO {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public String getUsername() {return this.username;}

    public void setUsername(final String username) {this.username = username;}

    public String getPassword() {return this.password;}

    public void setPassword(final String password) {this.password = password;}

}
