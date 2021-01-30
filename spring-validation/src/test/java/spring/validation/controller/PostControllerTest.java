package spring.validation.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void create_success() throws Exception {
        this.mockMvc.perform(post("/posts")
                .content("{\"title\": \"title\", \n\"content\": \"content\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void create_fail() throws Exception {
        this.mockMvc.perform(post("/posts")
                .content("{\"title\": \"\", \n\"content\": \"content\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                // .andExpect(jsonPath("$.title", Is.is("must not be blank")));
                .andExpect(jsonPath("$.title", Is.is("Post's title must not be blank")));
    }
}