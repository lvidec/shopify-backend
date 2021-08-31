package tvz.videc.zavrsni.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tvz.videc.zavrsni.webshop.model.login.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Only need to specify methods from JpaRepository, not from CrudRepository
    Optional<User> findByUsername(String username);

//    List<User> findAll();

//    User save(User user);
}
