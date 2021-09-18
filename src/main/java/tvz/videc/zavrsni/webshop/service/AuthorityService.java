package tvz.videc.zavrsni.webshop.service;

import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.AuthorityDTO;

import java.util.List;

public interface AuthorityService {

    List<AuthorityDTO> getAllAuthority();
}
