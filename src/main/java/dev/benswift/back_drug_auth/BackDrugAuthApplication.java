package dev.benswift.back_drug_auth;
import dev.benswift.back_drug_auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class BackDrugAuthApplication implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(BackDrugAuthApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //System.out.println(roleRepository.findAll().toString());
    }
}
