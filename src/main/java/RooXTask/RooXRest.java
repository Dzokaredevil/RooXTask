package RooXTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class RooXRest {

    public static void main(String[] args) {
        SpringApplication.run(RooXRest.class, args);
    }
}
