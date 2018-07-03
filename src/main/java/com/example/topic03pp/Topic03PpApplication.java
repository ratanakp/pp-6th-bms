package com.example.topic03pp;

import com.example.topic03pp.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Topic03PpApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Topic03PpApplication.class, args);

        UserRepository userRepository = context.getBean(UserRepository.class);

        System.out.println(userRepository.loadUserByUsername("somnak"));


        PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);

        String encodedPassword = passwordEncoder.encode("sreypov");

        System.out.println(passwordEncoder.matches("somnak1", "$2a$10$31nbipPyQk1v1PloWRDYw.riGP52tX7gW2SFAcI.5BoDEryIjurPW"));

        System.out.println(encodedPassword);




    }
}
