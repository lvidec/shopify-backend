package tvz.videc.zavrsni.webshop.service;

import java.util.List;
import java.util.Optional;

import tvz.videc.zavrsni.webshop.model.login.AppUser;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByUsername(String username);

    AppUser findFullById(Long id);

    Optional<UserDTO> save(AppUser user);

    Optional<UserDTO> addClothingToUser(Long userId, Long clothingId);

    void delete(Long id);
}
