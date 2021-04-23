package spring.cache;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import spring.cache.dto.PostRequest;
import spring.cache.dto.PostResponse;
import spring.cache.service.PostService;

@SpringBootTest
class CacheTest {

    @Autowired
    private PostService postService;

    @Autowired
    private CacheManager cacheManager;

    @Test
    void cacheManager() {
        final PostResponse postResponse = postService.create(new PostRequest("title1", "content1"));
        postService.create(new PostRequest("title2", "content2"));
        postService.create(new PostRequest("title3", "content3"));

        postService.findAll();

        assertThat(cacheManager.getCacheNames()).hasSize(1);
        assertThat(cacheManager.getCache("Post")).isNotNull();

        postService.findById(postResponse.getId());

        assertThat(cacheManager.getCacheNames()).hasSize(2);
        assertThat(cacheManager.getCache("PostDetail")).isNotNull();
        assertThat(cacheManager.getCache("PostDetail").get(1L)).isNotNull();

        postService.update(1L, new PostRequest("new_title", "new_content"));

        assertThat(cacheManager.getCacheNames()).hasSize(2);
        // Debug로 확인해보면 Cache key로 Post는 남아있지만 value값은 삭제되었다.
        assertThat(cacheManager.getCache("Post")).isNotNull();
        assertThat(cacheManager.getCache("PostDetail")).isNotNull();
        assertThat(cacheManager.getCache("PostDetail").get(1L)).isNotNull();

        postService.delete(1L);

        assertThat(cacheManager.getCacheNames()).hasSize(2);
        assertThat(cacheManager.getCache("Post")).isNotNull();
        assertThat(cacheManager.getCache("PostDetail")).isNotNull();
        assertThat(cacheManager.getCache("PostDetail").get(1L)).isNull();
    }
}
