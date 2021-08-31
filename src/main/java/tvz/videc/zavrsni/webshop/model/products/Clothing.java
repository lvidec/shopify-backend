package tvz.videc.zavrsni.webshop.model.products;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
