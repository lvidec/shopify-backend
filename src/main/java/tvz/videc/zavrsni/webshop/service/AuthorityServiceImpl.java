package tvz.videc.zavrsni.webshop.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import tvz.videc.zavrsni.webshop.model.login.AppUser;
import tvz.videc.zavrsni.webshop.model.login.Authority;
import tvz.videc.zavrsni.webshop.model.login.AuthorityDTO;
import tvz.videc.zavrsni.webshop.repository.AuthorityRepository;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(final AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public AuthorityDTO mapAuthorityToAuthorityDTO(final Authority authority) {
        return new AuthorityDTO(
          authority.getId(), authority.getName(), authority.getUsers().stream().map(AppUser::getUsername).collect(Collectors.toSet()));

    }

    @Override
    public List<AuthorityDTO> getAllAuthority() {
        return this.authorityRepository.findAll().stream().map(this::mapAuthorityToAuthorityDTO).collect(Collectors.toList());
    }

}
