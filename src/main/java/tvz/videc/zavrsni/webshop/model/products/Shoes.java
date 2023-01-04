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

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @Enumerated(EnumType.STRING)
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
