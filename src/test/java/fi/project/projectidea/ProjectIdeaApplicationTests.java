package fi.project.projectidea;

import fi.project.projectidea.web.IdeaController;
import fi.project.projectidea.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

//Smoke test
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectIdeaApplicationTests {

    @Autowired
    private IdeaController ideaController;

    @Autowired
    UserController userController;

    //Tests if application context is creating the controllers
    @Test
    public void contextLoads() {
        assertThat(ideaController).isNotNull();
        assertThat(userController).isNotNull();
    }

}
