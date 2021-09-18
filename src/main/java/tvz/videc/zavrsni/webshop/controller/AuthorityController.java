package tvz.videc.zavrsni.webshop.controller;


import org.springframework.web.bind.annotation.*;
import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.AuthorityDTO;
import tvz.videc.zavrsni.webshop.service.AuthorityServiceImpl;

import java.util.List;

@RestController
@RequestMapping("authority")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorityController {

    private final AuthorityServiceImpl authorityService;

    public AuthorityController(AuthorityServiceImpl authorityService){
        this.authorityService = authorityService;
    }

    @GetMapping/*("/{username}")*/
    public List<AuthorityDTO> getAll(/*@PathVariable String username*/){
        return authorityService.getAllAuthority(/*username*/);
    }

}
