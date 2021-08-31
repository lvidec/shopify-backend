package tvz.videc.zavrsni.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.videc.zavrsni.webshop.model.products.Shoes;
import tvz.videc.zavrsni.webshop.model.products.ShoesDTO;
import tvz.videc.zavrsni.webshop.service.ShoesService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shoes")
@CrossOrigin(origins = "http://localhost:3000")
public class ShoesController {

    ShoesService shoesService;

    public ShoesController(ShoesService shoesService){
        this.shoesService = shoesService;
    }

    @GetMapping
    public List<ShoesDTO> findAll(){
        return shoesService.findAll();
    }

    @GetMapping("/{name}")
    public Optional<ShoesDTO> findByName(@PathVariable String name){
        return shoesService.findByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<ShoesDTO> saveUser(@RequestBody Shoes shoes){
        return shoesService.save(shoes).map(
                shoesDTO -> ResponseEntity.status(HttpStatus.CREATED).body(shoesDTO)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        shoesService.delete(id);
    }


}
