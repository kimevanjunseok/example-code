package spring.etag.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@AutoConfigureMockMvc
@SpringBootTest
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        create();

        String url = "/posts/etag";

        MvcResult mvcResult = this.mockMvc.perform(get(url))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists("ETag"))
                .andReturn();

        String etag = mvcResult.getResponse().getHeader("ETag");

        mvcResult = this.mockMvc.perform(get(url).header("If-None-Match", etag))
                .andDo(print())
                .andExpect(status().isNotModified())
                .andExpect(header().exists("ETag"))
                .andReturn();

        update();

        etag = mvcResult.getResponse().getHeader("ETag");

        this.mockMvc.perform(get(url).header("If-None-Match", etag))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().exists("ETag"))
                .andReturn();
    }

    private void create() throws Exception {
        this.mockMvc.perform(post("/posts")
                .content("{\"title\": \"title\", \n\"content\": \"content\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    private void update() throws Exception {
        this.mockMvc.perform(put("/posts/1")
                .content("{\"title\": \"turtle\", \n\"content\": \"context\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}