package tvz.videc.zavrsni.webshop.model.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "id_clothing_type", nullable = false)
    private ClothingType clothingType;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_clothing",
//            joinColumns = { @JoinColumn(name = "clothing_id") },
//            inverseJoinColumns = {@JoinColumn(name = "user_id") }
//    )
//    @JsonIgnore
//    private List<User> usersClothing;

}
