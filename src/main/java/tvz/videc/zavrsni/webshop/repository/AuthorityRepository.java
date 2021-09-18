package tvz.videc.zavrsni.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tvz.videc.zavrsni.webshop.model.login.Authority;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

//    List<Authority> findAll();

}
