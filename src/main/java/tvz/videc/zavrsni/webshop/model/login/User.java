package tvz.videc.zavrsni.webshop.model.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class User {

    @javax.persistence.Id
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany(targetEntity = Authority.class, mappedBy = "users")
    private Set<Authority> authorities;


    public User(String username, String password, Optional<String> authority, Set<GrantedAuthority> grantedAuthorities) { }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
