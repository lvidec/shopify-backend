package tvz.videc.zavrsni.webshop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tvz.videc.zavrsni.webshop.model.login.AuthorityDTO;
import tvz.videc.zavrsni.webshop.service.AuthorityServiceImpl;

@RestController
@RequestMapping("authority")
@CrossOrigin(origins = "${cross.origin}")
public class AuthorityController {

    private final AuthorityServiceImpl authorityService;

    public AuthorityController(final AuthorityServiceImpl authorityService) {
        this.authorityService = authorityService;
    }

    @GetMapping/*("/{username}")*/ public List<AuthorityDTO> getAll(/*@PathVariable String username*/) {
        return this.authorityService.getAllAuthority(/*username*/);
    }

}
