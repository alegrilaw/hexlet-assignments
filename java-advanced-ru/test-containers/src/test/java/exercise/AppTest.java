package exercise;

import exercise.model.Person;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.springframework.http.MediaType;

import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.junit.jupiter.Container;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc

// BEGIN
@Testcontainers
@Transactional
// END
public class AppTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    // BEGIN
    @Container
    private static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres")
        .withDatabaseName("database")
        .withUsername("sa")
        .withPassword("sa")
        .withInitScript("init.sql");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.username", database::getUsername);
        registry.add("spring.datasource.password", database::getPassword);
    }

    @Test
    void testGetPeople() throws Exception {
        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("John", "Smith");
        assertThat(response.getContentAsString()).contains("Jack", "Doe");
        assertThat(response.getContentAsString()).contains("Jassica", "Simpson");
        assertThat(response.getContentAsString()).contains("Robert", "Lock");
    }

    @Test
    void testGetPerson() throws Exception {
        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        var peopleContent = response.getContentAsString();
        List<Person> people = objectMapper.readValue(peopleContent, new TypeReference<List<Person>>() { });
        var id = people.get(0).getId();
        var firstName = people.get(0).getFirstName();
        var lastName = people.get(0).getLastName();

        response = mockMvc
            .perform(get("/people/" + id))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains(firstName, lastName);
    }

    @Test
    void testDeletePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        var peopleContent = response.getContentAsString();
        List<Person> people = objectMapper.readValue(peopleContent, new TypeReference<List<Person>>() { });
        var id = people.get(0).getId();

        response = mockMvc
            .perform(delete("/people/" + id))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        response = mockMvc
            .perform(get("/people/" + id))
            .andReturn()
            .getResponse();

        assertThat(response.getContentAsString()).isEmpty();
    }

    @Test
    void testUpdatePerson() throws Exception {
        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        var peopleContent = response.getContentAsString();
        List<Person> people = objectMapper.readValue(peopleContent, new TypeReference<List<Person>>() { });
        var id = people.get(0).getId();

        response = mockMvc
            .perform(
                patch("/people/" + id)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);

        response = mockMvc
            .perform(get("/people/" + id))
            .andReturn()
            .getResponse();

        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
    // END

    @Test
    void testCreatePerson() throws Exception {
        MockHttpServletResponse responsePost = mockMvc
            .perform(
                post("/people")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\"firstName\": \"Jackson\", \"lastName\": \"Bind\"}")
            )
            .andReturn()
            .getResponse();

        assertThat(responsePost.getStatus()).isEqualTo(200);

        MockHttpServletResponse response = mockMvc
            .perform(get("/people"))
            .andReturn()
            .getResponse();

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.getContentType()).isEqualTo(MediaType.APPLICATION_JSON.toString());
        assertThat(response.getContentAsString()).contains("Jackson", "Bind");
    }
}
