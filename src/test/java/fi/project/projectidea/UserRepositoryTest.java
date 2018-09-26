package fi.project.projectidea;

import fi.project.projectidea.domain.Idea;
import fi.project.projectidea.domain.User;
import fi.project.projectidea.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    //There's already one objects in userRepository created by data.sql file
    @Autowired
    private UserRepository userRepository;

    //Tests the creation and saving of an instance of User class
    @Test
    public void createAndSave() {
        //Creates a new instance of the class User
        User user = new User("Jane", "Doe123", "USER");

        //Saves the created instance to userRepository
        userRepository.save(user);

        //Checks if the values given in the creation of the instance are given correctly
        assertThat(user.getUsername()).isEqualTo("Jane");

        //Checks if the created instance is saved in the repository correctly
        assertThat(userRepository.findByUsername("Jane")).isNotNull();
    }

    //Tests the searching of a repository
    @Test
    public void search() {
        User user = new User("Jane", "Doe123", "USER");
        userRepository.save(user);
        assertThat(userRepository.findByUsername("Jane").getUsername()).isEqualTo("Jane");
    }

    //Tests the delete functionality of the repository
    //Note! The size of the user repo is essentially one
    @Test
    public void delete() {
        User user = new User("Jane", "Doe123", "USER");
        userRepository.save(user);
        userRepository.delete(user);
        List<User> users = (List<User>) userRepository.findAll();
        assertThat(users).hasSize(1);
    }
}
