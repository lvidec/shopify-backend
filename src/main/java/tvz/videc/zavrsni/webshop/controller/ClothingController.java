package tvz.videc.zavrsni.webshop.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;
import tvz.videc.zavrsni.webshop.service.ClothingService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clothing")
@CrossOrigin(origins = "http://localhost:3000")
public class ClothingController {

    ClothingService clothingService;

    public ClothingController(ClothingService clothingService){
        this.clothingService = clothingService;
    }

    @GetMapping
    public List<ClothingDTO> findAll(){
        return clothingService.findAll();
    }

    @GetMapping("/{name}")
    public Optional<ClothingDTO> findByName(@PathVariable String name){
        return clothingService.findByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<ClothingDTO> saveUser(@RequestBody Clothing clothing){
        return clothingService.save(clothing).map(
                clothingDTO -> ResponseEntity.status(HttpStatus.CREATED).body(clothingDTO)
        ).orElseGet(
                () -> ResponseEntity.status(HttpStatus.CONFLICT).build()
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        clothingService.delete(id);
    }


}
