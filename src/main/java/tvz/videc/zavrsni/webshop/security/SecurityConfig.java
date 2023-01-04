package tvz.videc.zavrsni.webshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tvz.videc.zavrsni.webshop.security.jwt.JwtAuthenticationEntryPoint;
import tvz.videc.zavrsni.webshop.security.jwt.JwtFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtFilter jwtFilter){
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/api/authenticate")
                .antMatchers("**")
                .permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/h2-console/**");}

}
