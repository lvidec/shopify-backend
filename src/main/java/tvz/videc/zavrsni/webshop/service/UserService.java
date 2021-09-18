package tvz.videc.zavrsni.webshop.service;

import tvz.videc.zavrsni.webshop.model.login.User;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findByUsername(String username);

    User findFullById(Long id);

    Optional<UserDTO> save(User user);

    Optional<UserDTO> addClothingToUser(Long userId, Long clothingId);

    void delete(Long id);
}
