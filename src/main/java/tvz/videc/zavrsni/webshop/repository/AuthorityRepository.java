package tvz.videc.zavrsni.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tvz.videc.zavrsni.webshop.model.login.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    //    List<Authority> findAll();

}
