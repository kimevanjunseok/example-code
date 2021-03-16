package spring.redis.repository.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import spring.redis.repository.domain.User;

@Repository
public interface UserRepository  extends CrudRepository<User, Long> {
}
