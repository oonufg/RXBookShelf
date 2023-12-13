package ru.pablo.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.pablo.Spring.Security.JWT.JWTUtils;

@PropertySource("classpath:application.yml")
@ComponentScan("ru.pablo")
@SpringBootApplication
public class RXBookShelf {
    public static void main(String[] args) {
        SpringApplication.run(RXBookShelf.class, args);
        /*
        System.out.println(JWTUtils.signToken(
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9",
                "eyJhdXRob3JpdGllcyI6W3sicm9sZSI6IlVTRVIifV0sImV4cCI6NjAwMTcwMjQwNDczMSwidXNlcklkIjoyLCJpYXAiOjE3MDI0MDQ3MzF9",
                "7nFXppcVmvNxL6YJsbFmSPLDodpXu99ymCTBDHFhs6t33gzkXUwJkoI2WhpEzyO7"
        ));
        */

    }
}