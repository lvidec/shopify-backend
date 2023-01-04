package tvz.videc.zavrsni.webshop.security;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import tvz.videc.zavrsni.webshop.model.login.AppUser;
import tvz.videc.zavrsni.webshop.repository.UserRepository;

@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DomainUserDetailsService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(final AppUser user) {
        final List<GrantedAuthority> grantedAuthorities = user
          .getAuthorities()
          .stream()
          .map(authority -> new SimpleGrantedAuthority(authority.getName()))
          .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {

        return this.userRepository
          .findByUsername(username)
          .map(this::createSpringSecurityUser)
          .orElseThrow(() -> new UsernameNotFoundException("User " + username + " was not found in the database"));
    }

}
