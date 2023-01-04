package tvz.videc.zavrsni.webshop.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import tvz.videc.zavrsni.webshop.model.login.AppUser;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;
import tvz.videc.zavrsni.webshop.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "${cross.origin}")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> findAll() {
        return this.userService.findAll();
    }

    //    @GetMapping("/{username}")
    //    public UserDTO findByUsername(@PathVariable final String username) {
    //        return userService.findByUsername(username);
    //    }

    @GetMapping("/{id}")
    public AppUser findFullById(@PathVariable final Long id) {
        return this.userService.findFullById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<UserDTO> saveUser(@RequestBody final AppUser user) {
        return this.userService
          .save(user)
          .map(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO))
          .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PutMapping("{userId}/save/{clothingId}")
    public ResponseEntity<UserDTO> saveUser(@PathVariable final Long userId, @PathVariable final Long clothingId) {
        return this.userService
          .addClothingToUser(userId, clothingId)
          .map(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO))
          .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable final Long id) {
        this.userService.delete(id);
    }

}
