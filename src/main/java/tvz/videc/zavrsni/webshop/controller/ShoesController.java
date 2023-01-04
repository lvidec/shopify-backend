package tvz.videc.zavrsni.webshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tvz.videc.zavrsni.webshop.model.products.Shoes;
import tvz.videc.zavrsni.webshop.model.products.ShoesDTO;
import tvz.videc.zavrsni.webshop.service.ShoesService;

@RestController
@RequestMapping("shoes")
@CrossOrigin(origins = "${cross.origin}")
public class ShoesController {

    ShoesService shoesService;

    public ShoesController(final ShoesService shoesService) {
        this.shoesService = shoesService;
    }

    @GetMapping
    public List<ShoesDTO> findAll() {
        return this.shoesService.findAll();
    }

    // @GetMapping("/{name}")
    // public Optional<ShoesDTO> findByName(@PathVariable String name){
    //     return shoesService.findByName(name);
    // }

    @GetMapping("/{id}")
    public Optional<Shoes> findFullById(@PathVariable final Long id) {
        return this.shoesService.findFullById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ShoesDTO> saveShoes(@RequestBody final Shoes shoes) {
        return this.shoesService
          .save(shoes)
          .map(shoesDTO -> ResponseEntity.status(HttpStatus.CREATED).body(shoesDTO))
          .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        this.shoesService.delete(id);
    }

}
