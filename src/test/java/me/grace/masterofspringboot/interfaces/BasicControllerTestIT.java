package me.grace.masterofspringboot.interfaces;

import me.grace.masterofspringboot.MasterofspringbootApplication;
import me.grace.masterofspringboot.bean.WelcomeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MasterofspringbootApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerTestIT {
    private static final String LOCAL_HOST = "http://localhost:";

    @LocalServerPort
    private int port;

    private TestRestTemplate template = new TestRestTemplate();

    private String createURL(String uri) {
        return LOCAL_HOST + port + uri;
    }

    @Test
    public void welcome() throws Exception {
        ResponseEntity<String> response = template.getForEntity(createURL("/welcome"), String.class);
        assertThat(response.getBody(), is("Hello World"));
    }

    @Test
    public void welcomeWithObject() throws Exception {
        ResponseEntity<String> response = template.getForEntity(createURL("/welcome-with-object"), String.class);
        assertThat(response.getBody(), is(containsString("Hello World")));
    }

    @Test
    public void welcomeWithPathVar() throws Exception {
        ResponseEntity<String> response = template.getForEntity(createURL("/welcome-with-parameter/name/grace"), String.class);
        assertThat(response.getBody(), is(containsString("Hello World, grace!")));
    }


}