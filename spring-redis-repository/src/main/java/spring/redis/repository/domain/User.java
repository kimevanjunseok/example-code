package spring.redis.repository.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("User")
public class User {

    @Id
    private Long id;
    private String email;
    private String password;
    private String name;
}
