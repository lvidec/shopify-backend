package tvz.videc.zavrsni.webshop.model.login;

import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUser {

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

    @ManyToMany(targetEntity = Authority.class, mappedBy = "usersAuthority")
    @JsonIgnore
    private Set<Authority> authorities;

    //    @ManyToMany(targetEntity = Clothing.class, mappedBy = "usersClothing")
    //    @JsonIgnore
    //    private Set<Clothing> userClothing;
    //
    //    @ManyToMany(targetEntity = Shoes.class, mappedBy = "usersShoes")
    //    @JsonIgnore
    //    private Set<Shoes> userShoes;

    public AppUser(final String username, final String password, final Optional<String> authority, final Set<GrantedAuthority> grantedAuthorities) {}

    public Long getId() {
        return this.id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

}
