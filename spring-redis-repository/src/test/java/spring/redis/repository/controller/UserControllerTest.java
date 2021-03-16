package spring.redis.repository.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import spring.redis.repository.domain.User;
import spring.redis.repository.dto.UserResponse;
import spring.redis.repository.service.UserService;

@AutoConfigureMockMvc
@WebMvcTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void create() throws Exception {
        final UserResponse userResponse = UserResponse.of(new User("1234", "호랑이", 13));
        when(userService.save(any())).thenReturn(userResponse);

        this.mockMvc.perform(post("/api/v1/users")
                .content("{\"name\": \"호랑이\", \"age\":\"13\"}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void findById() throws Exception {
        final UserResponse userResponse = UserResponse.of(new User("1234", "호랑이", 13));
        when(userService.findById(any())).thenReturn(userResponse);

        this.mockMvc.perform(get("/api/v1/users/{userId}", userResponse.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Is.is("호랑이")))
                .andExpect(jsonPath("$.age", Is.is(13)))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        final UserResponse userResponse = UserResponse.of(new User("1234", "호랑이", 13));
        when(userService.findById(any())).thenReturn(userResponse);

        this.mockMvc.perform(delete("/api/v1/users/{userId}", userResponse.getId()))
                .andExpect(status().isNoContent())
                .andDo(print());
    }
}