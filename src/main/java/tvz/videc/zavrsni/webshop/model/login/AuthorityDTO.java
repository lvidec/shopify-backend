package tvz.videc.zavrsni.webshop.model.login;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityDTO {

    private Long id;

    private String name;

    private Set<String> username;

}
