package tvz.videc.zavrsni.webshop.service;

import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;

import java.util.List;
import java.util.Optional;

public interface ClothingService {

    List<ClothingDTO> findAll();

    Optional<ClothingDTO> findByName(String name);

    Optional<ClothingDTO> save(Clothing clothing);

    void delete(Long id);
}
