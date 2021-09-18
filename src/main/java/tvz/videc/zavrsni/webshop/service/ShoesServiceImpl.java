package tvz.videc.zavrsni.webshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tvz.videc.zavrsni.webshop.model.login.User;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;
import tvz.videc.zavrsni.webshop.model.products.Shoes;
import tvz.videc.zavrsni.webshop.model.products.ShoesDTO;
import tvz.videc.zavrsni.webshop.repository.ShoesRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoesServiceImpl implements ShoesService {

//    @Autowired
    private final ShoesRepository shoesRepository;

    public ShoesServiceImpl(ShoesRepository shoesRepository){
        this.shoesRepository = shoesRepository;
    }

    @Override
    public List<ShoesDTO> findAll() {
        return this.shoesRepository.findAll().stream().map(this::mapShoesToShoesDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ShoesDTO> findByName(String name) {
        return shoesRepository.findByName(name).map(this::mapShoesToShoesDTO);
    }

    @Override
    public Optional<ShoesDTO> save(Shoes shoes) {
        shoesRepository.save(shoes);
        return Optional.of(mapShoesToShoesDTO(shoes));
    }

    @Override
    public void delete(Long id) {
        shoesRepository.deleteById(id);
    }

    public ShoesDTO mapShoesToShoesDTO(Shoes shoes){
        return new ShoesDTO(shoes.getId(), shoes.getName(), shoes.getDetails(), shoes.getPrice(), shoes.getImg(), shoes.getBrandName(), shoes.getSex(), shoes.getShoesType(), shoes.getUsersShoes().stream().map(User::getUsername).collect(Collectors.toSet()));
    }
}
