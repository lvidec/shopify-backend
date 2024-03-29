package tvz.videc.zavrsni.webshop.model.login;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String username;
    private Set<String> authorities;
    //    private Set<String> clothing;
    //    private Set<String> shoes;

}
