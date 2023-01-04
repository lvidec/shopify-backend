package tvz.videc.zavrsni.webshop.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tvz.videc.zavrsni.webshop.model.products.Shoes;
import tvz.videc.zavrsni.webshop.model.products.ShoesDTO;
import tvz.videc.zavrsni.webshop.repository.ShoesRepository;

@Service
public class ShoesServiceImpl implements ShoesService {

    //    @Autowired
    private final ShoesRepository shoesRepository;

    public ShoesServiceImpl(final ShoesRepository shoesRepository) {
        this.shoesRepository = shoesRepository;
    }

    public ShoesDTO mapShoesToShoesDTO(final Shoes shoes) {
        return new ShoesDTO(
          shoes.getId(), shoes.getName(), shoes.getDetails(), shoes.getPrice(), shoes.getImg(), shoes.getBrandName(), shoes.getSex(),
          shoes.getShoesType());
    }

    @Override
    public List<ShoesDTO> findAll() {
        return this.shoesRepository.findAll().stream().map(this::mapShoesToShoesDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ShoesDTO> findByName(final String name) {
        return this.shoesRepository.findByName(name).map(this::mapShoesToShoesDTO);
    }

    @Override
    public Optional<Shoes> findFullById(final Long id) {
        return this.shoesRepository.findById(id);
    }

    @Override
    public Optional<ShoesDTO> save(final Shoes shoes) {
        this.shoesRepository.save(shoes);
        return Optional.of(this.mapShoesToShoesDTO(shoes));
    }

    @Override
    public void delete(final Long id) {
        this.shoesRepository.deleteById(id);
    }

}
