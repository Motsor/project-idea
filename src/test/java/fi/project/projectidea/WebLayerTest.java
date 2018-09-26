package fi.project.projectidea;

import fi.project.projectidea.web.IdeaController;
import fi.project.projectidea.web.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//Specifies the needed controllers and disables security while testing
@WebMvcTest(controllers = {IdeaController.class, UserController.class}, secure = false)
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IdeaController ideaController;

    @MockBean
    private UserController userController;

    //Tests all the sites while security is disabled for eg. data binding exceptions and such..
    @Test
    public void testSites() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Project Idea is a website where you can find project ideas.")));

        mockMvc.perform(get("/idealist")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Idea List")));

        mockMvc.perform(get("/myideas")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("My Ideas")));

        mockMvc.perform(get("/addidea")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Add Idea")));

        mockMvc.perform(get("/signup")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("We'll never share your information with anyone else.")));
    }

}
