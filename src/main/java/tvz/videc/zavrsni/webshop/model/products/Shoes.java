package tvz.videc.zavrsni.webshop.model.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tvz.videc.zavrsni.webshop.model.login.User;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Shoes {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String details;

    @Column
    private float price;

    @Column
    private String img;

    @Column
    private String brandName;

    @Column
    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "id_shoes_type", nullable = false)
    private ShoesType shoesType;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_shoes",
//            joinColumns = { @JoinColumn(name = "shoes_id") },
//            inverseJoinColumns = {@JoinColumn(name = "user_id") }
//    )
//    private List<User> usersShoes;

}
