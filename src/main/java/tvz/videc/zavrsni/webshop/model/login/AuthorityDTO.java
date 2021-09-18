package tvz.videc.zavrsni.webshop.model.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor
public class AuthorityDTO {

    private Long id;

    private String name;

    private Set<String> username;
}
