package tvz.videc.zavrsni.webshop.service;


import org.springframework.stereotype.Service;
import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.AuthorityDTO;
import tvz.videc.zavrsni.webshop.model.login.User;
import tvz.videc.zavrsni.webshop.model.login.UserDTO;
import tvz.videc.zavrsni.webshop.repository.AuthorityRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityServiceImpl implements AuthorityService{

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }

    @Override
    public List<AuthorityDTO> getAllAuthority(){
        return authorityRepository.findAll().stream().map(this::mapAuthorityToAuthorityDTO).collect(Collectors.toList());
    }

    public AuthorityDTO mapAuthorityToAuthorityDTO(Authority authority) {
        return new AuthorityDTO(authority.getId(), authority.getName(), authority.getUsers().stream().map(User::getUsername).collect(Collectors.toSet()));

    }
}
