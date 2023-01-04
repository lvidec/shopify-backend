package tvz.videc.zavrsni.webshop.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import tvz.videc.zavrsni.webshop.model.login.JwtToken;
import tvz.videc.zavrsni.webshop.model.login.LoginDTO;
import tvz.videc.zavrsni.webshop.security.jwt.JwtFilter;
import tvz.videc.zavrsni.webshop.security.jwt.TokenProvider;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cross.origin}")
public class LoginController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public LoginController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    @PostMapping("/authenticate")
    @Secured("permitAll")
    public ResponseEntity<ReturningObject> authenticate(@Valid @RequestBody LoginDTO login) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken( login.getUsername(), login.getPassword() );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new ReturningObject(new JwtToken(jwt), login.getUsername(), login.getPassword()), httpHeaders, HttpStatus.OK);
    }


    static class ReturningObject{
        private JwtToken jwtToken;
        private String username;
        private String password;

        public ReturningObject(JwtToken jwtToken, String username, String password) {
            this.jwtToken = jwtToken;
            this.username = username;
            this.password = password;
        }

        public JwtToken getJwtToken() {
            return jwtToken;
        }

        public void setJwtToken(JwtToken jwtToken) {
            this.jwtToken = jwtToken;
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
