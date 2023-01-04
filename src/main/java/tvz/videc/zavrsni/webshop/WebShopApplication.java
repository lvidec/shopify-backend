package tvz.videc.zavrsni.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebShopApplication {
    //    @Autowired
    //    private PasswordEncoder passwordEncoder;

    public static void main(final String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

    //    @Override
    //    public void run(String... args) throws Exception {
    //        System.out.println(passwordEncoder.encode("Lima"));
    //        System.out.println(passwordEncoder.encode("Edi"));
    //        System.out.println(passwordEncoder.encode("Siti"));
    //    }

}
