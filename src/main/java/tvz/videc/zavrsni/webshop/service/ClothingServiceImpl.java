package tvz.videc.zavrsni.webshop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;
import tvz.videc.zavrsni.webshop.repository.ClothingRepository;

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
    public Optional<Clothing> findFullById(Long id) {
        return clothingRepository.findById(id);
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
        return new ClothingDTO(clothing.getId(), clothing.getName(), clothing.getDetails(), clothing.getPrice(), clothing.getImg(), clothing.getBrandName(), clothing.getSex(), clothing.getClothingType());
    }
}
