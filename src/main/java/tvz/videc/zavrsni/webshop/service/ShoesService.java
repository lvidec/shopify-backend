package tvz.videc.zavrsni.webshop.service;

import tvz.videc.zavrsni.webshop.model.products.Shoes;
import tvz.videc.zavrsni.webshop.model.products.ShoesDTO;

import java.util.List;
import java.util.Optional;

public interface ShoesService {

    List<ShoesDTO> findAll();

    Optional<ShoesDTO> findByName(String name);

    Optional<Shoes> findFullById(Long id);

    Optional<ShoesDTO> save(Shoes Shoes);

    void delete(Long id);
}
