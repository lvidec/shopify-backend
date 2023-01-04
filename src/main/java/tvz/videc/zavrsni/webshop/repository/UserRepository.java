package tvz.videc.zavrsni.webshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tvz.videc.zavrsni.webshop.model.login.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    //Only need to specify methods from JpaRepository, not from CrudRepository
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findById(Long id);

}

