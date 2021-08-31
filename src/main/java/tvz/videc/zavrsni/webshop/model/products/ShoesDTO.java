package tvz.videc.zavrsni.webshop.model.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ShoesDTO {

    private Long id;

    private String name;

    private String details;

    private float price;

    private String img;

    private String brandName;

    private Sex sex;

    private ShoesType shoesType;
}
