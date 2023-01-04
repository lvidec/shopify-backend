package tvz.videc.zavrsni.webshop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import tvz.videc.zavrsni.webshop.model.login.AppUser;
import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;
import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.repository.AuthorityRepository;
import tvz.videc.zavrsni.webshop.repository.ClothingRepository;
import tvz.videc.zavrsni.webshop.repository.ShoesRepository;
import tvz.videc.zavrsni.webshop.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    //    @Autowired
    private final UserRepository userRepository;
    private final ClothingRepository clothingRepository;
    private final ShoesRepository shoesRepository;
    private final AuthorityRepository authorityRepository;

    public UserServiceImpl(final UserRepository userRepository, final ClothingRepository clothingRepository, final ShoesRepository shoesRepository, final AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.clothingRepository = clothingRepository;
        this.shoesRepository = shoesRepository;
        this.authorityRepository = authorityRepository;
    }

    public UserDTO mapUserToUserDTO(final AppUser user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getAuthorities().stream().map(Authority::getName).collect(Collectors.toSet()));

        //        return new ModelMapper().map(user, UserDTO.class);
    }

    @Override
    public List<UserDTO> findAll() {
        return this.userRepository.findAll().stream().map(this::mapUserToUserDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findByUsername(final String username) {
        return this.userRepository.findByUsername(username).map(this::mapUserToUserDTO).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public AppUser findFullById(final Long id) {
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<UserDTO> save(final AppUser user) {
        //another one//        userRepository.save(user);
        ////        return Optional.of(mapUserToUserDTO(user));

        final AppUser newUser = new AppUser();
        newUser.setUsername(user.getUsername());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.getAuthorities().addAll(user.getAuthorities().stream().map(authority -> {
            final Authority newAuthority = this.authorityRepository.findById(authority.getId()).orElseThrow(EntityNotFoundException::new);
            newAuthority.getUsers().add(newUser);
            return newAuthority;
        }).collect(Collectors.toSet()));
        //        newUser.getUserClothing()
        //                .addAll(user
        //                        .getUserClothing()
        //                        .stream()
        //                        .map(clothing -> {
        //                            Clothing newClothing = clothingRepository.findById(clothing.getId()).orElseThrow(EntityNotFoundException::new);
        //                            newClothing.getUsersClothing().add(newUser);
        //                            return newClothing;
        //                        }).collect(Collectors.toSet()));
        //        newUser.getUserShoes()
        //                .addAll(user
        //                        .getUserShoes()
        //                        .stream()
        //                        .map(shoes -> {
        //                            Shoes newShoes = shoesRepository.findById(shoes.getId()).orElseThrow(EntityNotFoundException::new);
        //                            newShoes.getUsersShoes().add(newUser);
        //                            return newShoes;
        //                        }).collect(Collectors.toSet()));

        this.userRepository.save(newUser);

        return Optional.of(this.mapUserToUserDTO(newUser));
    }

    @Override
    public Optional<UserDTO> addClothingToUser(final Long userId, final Long clothingId) {
        final AppUser user = this.userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        final Clothing clothing = this.clothingRepository.findById(clothingId).orElseThrow(EntityNotFoundException::new);
        //        user.getUserClothing().add(clothing);
        //another one//        user.setUserClothing(List.of(clothing));

        user.setUsername("isuskrist ");

        //another one//        user.getUserClothing().forEach(x -> System.out.println(x.getName()));

        this.userRepository.save(user);
        return Optional.of(this.mapUserToUserDTO(user));
    }

    @Override
    public void delete(final Long id) {
        this.userRepository.deleteById(id);
    }

}
