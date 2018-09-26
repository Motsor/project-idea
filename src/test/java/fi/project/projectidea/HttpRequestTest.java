package fi.project.projectidea;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    /*
    Tests all the unsecured mappings in a random port.
    The Strings that are inside the contains method are taken from
    the tested sites.
     */
    @Test
    public void testHttpRequestUnsecuredMappings() {
        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/api",
                String.class)).contains("http://localhost:" + port + "/api/ideas");

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/ideas",
                String.class)).contains("Implement a cipher");

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/login",
                String.class)).contains("Project Idea is a website where you can find project ideas.");

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/signup",
                String.class)).contains("We'll never share your information with anyone else.");
    }

    /*
    Tests all the secured mappings in a random port.
    The Strings that are inside the contains method are taken from
    the tested sites.
     */

    @Test
    public void testHttpRequestSecuredMappings() {
        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Project Idea is a website where you can find project ideas.");

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/idealist",
                String.class)).contains("Project Idea is a website where you can find project ideas.");

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/myideas",
                String.class)).contains("Project Idea is a website where you can find project ideas.");

        assertThat(testRestTemplate.getForObject("http://localhost:" + port + "/addidea",
                String.class)).contains("Project Idea is a website where you can find project ideas.");
    }

}
