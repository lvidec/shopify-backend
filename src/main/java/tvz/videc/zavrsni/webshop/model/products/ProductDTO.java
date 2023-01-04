package tvz.videc.zavrsni.webshop.model.products;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDTO {

    private String name;
    private String details;
    private float price;
    private String img;

}
