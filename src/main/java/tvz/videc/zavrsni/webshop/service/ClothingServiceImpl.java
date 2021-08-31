package tvz.videc.zavrsni.webshop.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;
import tvz.videc.zavrsni.webshop.repository.ClothingRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClothingServiceImpl implements ClothingService {

//    @Autowired
    private final ClothingRepository clothingRepository;

    public ClothingServiceImpl(ClothingRepository clothingRepository){
        this.clothingRepository = clothingRepository;
    }

    @Override
    public List<ClothingDTO> findAll() {
        return this.clothingRepository.findAll().stream().map(this::mapClothingToClothingDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ClothingDTO> findByName(String name) {
        return clothingRepository.findByName(name).map(this::mapClothingToClothingDTO);
    }

    @Override
    public Optional<ClothingDTO> save(Clothing clothing) {
        clothingRepository.save(clothing);
        return Optional.of(mapClothingToClothingDTO(clothing));
    }

    @Override
    public void delete(Long id) {
        clothingRepository.deleteById(id);
    }

    public ClothingDTO mapClothingToClothingDTO(Clothing clothing){
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(clothing, ClothingDTO.class);
//        return new ModelMapper().map(user, UserDTO.class);
    }
}
