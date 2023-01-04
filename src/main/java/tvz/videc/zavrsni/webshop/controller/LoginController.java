package tvz.videc.zavrsni.webshop.controller;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tvz.videc.zavrsni.webshop.model.login.JwtToken;
import tvz.videc.zavrsni.webshop.model.login.LoginDTO;
import tvz.videc.zavrsni.webshop.security.jwt.JwtFilter;
import tvz.videc.zavrsni.webshop.security.jwt.TokenProvider;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cross.origin}")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public LoginController(final TokenProvider tokenProvider, final AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<ReturningObject> authenticate(@Valid @RequestBody final LoginDTO login) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());

        final Authentication authentication = this.authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String jwt = this.tokenProvider.createToken(authentication);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new ReturningObject(new JwtToken(jwt), login.getUsername(), login.getPassword()), httpHeaders, HttpStatus.OK);
    }

    static class ReturningObject {

        private JwtToken jwtToken;
        private String username;
        private String password;

        public ReturningObject(final JwtToken jwtToken, final String username, final String password) {
            this.jwtToken = jwtToken;
            this.username = username;
            this.password = password;
        }

        public JwtToken getJwtToken() {
            return this.jwtToken;
        }

        public void setJwtToken(final JwtToken jwtToken) {
            this.jwtToken = jwtToken;
        }

        public String getUsername() {
            return this.username;
        }

        public void setUsername(final String username) {
            this.username = username;
        }

        public String getPassword() {
            return this.password;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

    }

    //    /**
    //     * Return jwt token in body because Angular has problems with parsing plain string response entity
    //     */
    //    static class JWTToken {
    //        private String token;
    //
    //        public JWTToken(String token) {
    //            this.token = token;
    //        }
    //
    //        public String getToken() {
    //            return token;
    //        }
    //
    //        public void setToken(String token) {
    //            this.token = token;
    //        }
    //    }
    //

}
