package fi.project.projectidea;

import fi.project.projectidea.domain.Idea;
import fi.project.projectidea.domain.IdeaRepository;
import fi.project.projectidea.domain.User;
import fi.project.projectidea.domain.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectIdeaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectIdeaApplication.class, args);
    }

    // The following bean adds sample data to our repositories
    @Bean
    public CommandLineRunner sampleData(IdeaRepository ideaRepository, UserRepository userRepository) {
        return args -> {
            ideaRepository.save(new Idea("Implement a cipher", "Build a tool that takes a string of text" +
                    " as input and encrypts it using a cipher, such as the Caesar cipher.", "Intermediate"));

            ideaRepository.save(new Idea("Web scraper", "Build a tool that takes a URL as input" +
                    " and returns the content of the URL as HTML or XML.", "Intermediate"));

            //Password of admin is "password" hashed with BCrypt
            User admin = new User("admin", "$2a$10$09lF3qCzxvo0yZSonrQrZOkTb1pT7tNoLzPMG8VhDs55B1u90ulfe", "ADMIN");

            userRepository.save(admin);
        };
    }
}

