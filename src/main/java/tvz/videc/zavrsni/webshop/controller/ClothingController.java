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

import tvz.videc.zavrsni.webshop.model.products.Clothing;
import tvz.videc.zavrsni.webshop.model.products.ClothingDTO;
import tvz.videc.zavrsni.webshop.service.ClothingService;

@RestController
@RequestMapping("clothing")
@CrossOrigin(origins = "${cross.origin}")
public class ClothingController {

    ClothingService clothingService;

    public ClothingController(ClothingService clothingService){
        this.clothingService = clothingService;
    }

    @GetMapping
    public List<ClothingDTO> findAll(){
//        clothingService.save(new Clothing(4L, "Clothing 4", "detalji", 42069, "isus", "Clothing", Sex.MALE
//        , new ClothingType(2L, "")));
        return clothingService.findAll();
    }

//    @GetMapping("/{name}")
//    public Optional<ClothingDTO> findByName(@PathVariable String name){
//        return clothingService.findByName(name);
//    }

    @GetMapping("/{id}")
    public Optional<Clothing> findFullById(@PathVariable Long id){
        return clothingService.findFullById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<ClothingDTO> saveClothing(@RequestBody Clothing clothing){
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
