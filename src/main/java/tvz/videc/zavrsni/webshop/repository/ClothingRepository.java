package tvz.videc.zavrsni.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tvz.videc.zavrsni.webshop.model.products.Clothing;

import java.util.Optional;

@Repository
public interface ClothingRepository extends JpaRepository<Clothing, Long> {

    Optional<Clothing> findByName(String name);

    Optional<Clothing> findById(Long id);
}
