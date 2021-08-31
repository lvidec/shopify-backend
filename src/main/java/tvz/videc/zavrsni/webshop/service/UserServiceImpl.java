package tvz.videc.zavrsni.webshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.User;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;
import tvz.videc.zavrsni.webshop.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        return this.userRepository.findAll().stream().map(this::mapUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(String username) {
        return this.userRepository.findByUsername(username).map(this::mapUserToUserDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<UserDTO> save(User user) {
        userRepository.save(user);
        return Optional.of(mapUserToUserDTO(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO mapUserToUserDTO(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));

//        return new ModelMapper().map(user, UserDTO.class);
    }
}
