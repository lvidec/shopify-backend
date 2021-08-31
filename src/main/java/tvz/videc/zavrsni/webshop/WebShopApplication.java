package tvz.videc.zavrsni.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class WebShopApplication{
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WebShopApplication.class, args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println(passwordEncoder.encode("Lima"));
//        System.out.println(passwordEncoder.encode("Edi"));
//        System.out.println(passwordEncoder.encode("Siti"));
//    }

}
