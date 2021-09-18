package tvz.videc.zavrsni.webshop.model.products;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tvz.videc.zavrsni.webshop.model.login.User;

import javax.persistence.*;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Clothing {

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
    @JoinColumn(name = "id_clothing_type", nullable = false)
    private ClothingType clothingType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_clothing",
            joinColumns = { @JoinColumn(name = "clothing_id") },
            inverseJoinColumns = {@JoinColumn(name = "user_id") }
    )
    @JsonIgnore
    private List<User> usersClothing;

}
