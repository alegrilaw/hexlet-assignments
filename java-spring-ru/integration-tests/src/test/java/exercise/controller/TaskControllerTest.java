package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

// BEGIN
@SpringBootTest
@AutoConfigureMockMvc
// END
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;


    @Test
    void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }


    private Task generateTask() {
        return Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().word())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();
    }

    // BEGIN
    @Test
    void testCreate() throws Exception {
        var task = generateTask();

        var request = post("/tasks")
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(task));

        var result = mockMvc.perform(request)
            .andExpect(status().isCreated())
            .andReturn();

        var body = result.getResponse().getContentAsString();

        var resultTask = om.readValue(body, Task.class);

        var savedTask = taskRepository.findById(resultTask.getId());
        assertThat(savedTask).isPresent();
        assertThat(savedTask.get().getTitle()).isEqualTo(task.getTitle());
        assertThat(savedTask.get().getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    void testUpdate() throws Exception {
        var id = taskRepository.save(generateTask()).getId();

        var task = generateTask();

        var request = put("/tasks/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(om.writeValueAsString(task));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        var updatedTask = taskRepository.findById(id);
        assertThat(updatedTask).isPresent();
        assertThat(updatedTask.get().getTitle()).isEqualTo(task.getTitle());
        assertThat(updatedTask.get().getDescription()).isEqualTo(task.getDescription());
    }

    @Test
    void testDelete() throws Exception {
        var id = taskRepository.save(generateTask()).getId();

        var request = delete("/tasks/" + id);

        mockMvc.perform(request)
            .andExpect(status().isOk());

        var savedTask = taskRepository.findById(id);
        assertThat(savedTask).isNotPresent();
    }
    // END
}
