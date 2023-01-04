package tvz.videc.zavrsni.webshop.model.login;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Authority {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "authority_id") },
            inverseJoinColumns = {@JoinColumn(name = "user_id") }
    )
    @JsonIgnore
    private List<AppUser> usersAuthority;

    public Authority(Long id, String name, List<AppUser> usersAuthority) {
        this.id = id;
        this.name = name;
        this.usersAuthority = usersAuthority;
    }

    public Authority() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<AppUser> getUsers() { return usersAuthority; }

    public void setUsers(List<AppUser> users) { this.usersAuthority = users; }
}
