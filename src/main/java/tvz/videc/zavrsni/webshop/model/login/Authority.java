package tvz.videc.zavrsni.webshop.model.login;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


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
    private List<User> usersAuthority;

    public Authority(Long id, String name, List<User> usersAuthority) {
        this.id = id;
        this.name = name;
        this.usersAuthority = usersAuthority;
    }

    public Authority() { }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<User> getUsers() { return usersAuthority; }

    public void setUsers(List<User> users) { this.usersAuthority = users; }
}
