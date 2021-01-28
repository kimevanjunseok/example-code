package spring.restdocs.controller;

import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.restdocs.dto.PostResponse;
import spring.restdocs.service.PostService;

// @AutoConfigureMockMvc
// @AutoConfigureRestDocs
@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest
class PostControllerTest {

    @MockBean
    private PostService postService;

    // @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
            RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    void create() throws Exception {
        final PostResponse postResponse = new PostResponse(1L, "title", "content");
        when(postService.create(any())).thenReturn(postResponse);

        mockMvc.perform(RestDocumentationRequestBuilders.post("/posts")
                .content("{\"title\": \"title\", \n\"content\": \"content\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andDo(document("post-create",
                        requestFields(
                                fieldWithPath("title").description("Post 제목"),
                                fieldWithPath("content").description("Post 내용").optional()
                        )
                ));
    }

    @Test
    void findAll() throws Exception {
        final List<PostResponse> postResponses = Lists.newArrayList(
                new PostResponse(1L, "title1", "content1"),
                new PostResponse(2L, "title2", "content2")
        );

        when(postService.findAll()).thenReturn(postResponses);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/posts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-get-all",
                        responseFields(
                                fieldWithPath("[].id").description("Post Id"),
                                fieldWithPath("[].title").description("Post 제목"),
                                fieldWithPath("[].content").description("Post 내용")
                        )
                ));
    }

    @Test
    void findById() throws Exception {
        final PostResponse postResponse = new PostResponse(1L, "title", "content");
        when(postService.findById(anyLong())).thenReturn(postResponse);

        mockMvc.perform(RestDocumentationRequestBuilders.get("/posts/{postId}", postResponse.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-get-one",
                        pathParameters(
                                parameterWithName("postId").description("Post Id")
                        ),
                        responseFields(
                                fieldWithPath("id").description("Post Id"),
                                fieldWithPath("title").description("Post 제목"),
                                fieldWithPath("content").description("Post 내용")
                        )
                ));
    }

    @Test
    void update() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.put("/posts/{postId}", 1L)
                .content("{\"title\": \"turtle\", \n\"content\": \"context\"}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("post-update",
                        pathParameters(
                                parameterWithName("postId").description("Post Id")
                        ),
                        requestFields(
                                fieldWithPath("title").description("Post 제목"),
                                fieldWithPath("content").description("Post 내용")
                        )
                ));
    }

    @Test
    void remove() throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.delete("/posts/{postId}", 1L))
                .andExpect(status().isNoContent())
                .andDo(document("post-delete",
                        pathParameters(
                                parameterWithName("postId").description("Post Id")
                        )
                ));
    }
}