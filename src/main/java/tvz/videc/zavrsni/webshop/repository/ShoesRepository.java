package tvz.videc.zavrsni.webshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tvz.videc.zavrsni.webshop.model.products.Shoes;

@Repository
public interface ShoesRepository extends JpaRepository<Shoes, Long> {

    Optional<Shoes> findByName(String name);

    Optional<Shoes> findById(Long id);

}
