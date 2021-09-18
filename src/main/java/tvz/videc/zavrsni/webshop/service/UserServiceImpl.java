package tvz.videc.zavrsni.webshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.User;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;
import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;
import tvz.videc.zavrsni.webshop.model.products.Shoes;
import tvz.videc.zavrsni.webshop.repository.AuthorityRepository;
import tvz.videc.zavrsni.webshop.repository.ClothingRepository;
import tvz.videc.zavrsni.webshop.repository.ShoesRepository;
import tvz.videc.zavrsni.webshop.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    //    @Autowired
    private final UserRepository userRepository;
    private final ClothingRepository clothingRepository;
    private final ShoesRepository shoesRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(UserRepository userRepository, ClothingRepository clothingRepository, ShoesRepository shoesRepository, AuthorityRepository authorityRepository){
        this.userRepository = userRepository;
        this.clothingRepository = clothingRepository;
        this.shoesRepository = shoesRepository;
        this.authorityRepository = authorityRepository;
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
    public User findFullById(Long id){
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<UserDTO> save(User user) {
//        userRepository.save(user);
//        return Optional.of(mapUserToUserDTO(user));

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.getAuthorities()
                .addAll(user
                        .getAuthorities()
                        .stream()
                        .map(authority -> {
                            Authority newAuthority = authorityRepository.findById(authority.getId()).orElseThrow(EntityNotFoundException::new);
                            newAuthority.getUsers().add(newUser);
                            return newAuthority;
                        }).collect(Collectors.toSet()));
        newUser.getUserClothing()
                .addAll(user
                        .getUserClothing()
                        .stream()
                        .map(clothing -> {
                            Clothing newClothing = clothingRepository.findById(clothing.getId()).orElseThrow(EntityNotFoundException::new);
                            newClothing.getUsersClothing().add(newUser);
                            return newClothing;
                        }).collect(Collectors.toSet()));
        newUser.getUserShoes()
                .addAll(user
                        .getUserShoes()
                        .stream()
                        .map(shoes -> {
                            Shoes newShoes = shoesRepository.findById(shoes.getId()).orElseThrow(EntityNotFoundException::new);
                            newShoes.getUsersShoes().add(newUser);
                            return newShoes;
                        }).collect(Collectors.toSet()));

        userRepository.save(newUser);

        return Optional.of(mapUserToUserDTO(newUser));
    }

    @Override
    public Optional<UserDTO> addClothingToUser(Long userId, Long clothingId) {
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        Clothing clothing = clothingRepository.findById(clothingId).orElseThrow(EntityNotFoundException::new);
        user.getUserClothing().add(clothing);
//        user.setUserClothing(List.of(clothing));

        user.setUsername("isuskrist ");

        user.getUserClothing().forEach(x -> System.out.println(x.getName()));

        userRepository.save(user);
        return Optional.of(mapUserToUserDTO(user));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO mapUserToUserDTO(User user){
        return new UserDTO(user.getId(), user.getUsername(), user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()), user.getUserClothing().stream().map(Clothing::getName).collect(Collectors.toSet()), user.getUserShoes().stream().map(Shoes::getName).collect(Collectors.toSet()));

//        return new ModelMapper().map(user, UserDTO.class);
    }
}
