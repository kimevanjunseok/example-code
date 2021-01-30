package spring.validation.dto;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PostRequestTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void input_success() {
        PostRequest postRequest = new PostRequest("title", "content");
        Set<ConstraintViolation<PostRequest>> violations = validator.validate(postRequest);

        assertThat(violations).isEmpty();
    }

    @Test
    void input_WrongTitle_Validation() {
        PostRequest postRequest = new PostRequest("", "content");
        Set<ConstraintViolation<PostRequest>> violations = validator.validate(postRequest);

        assertThat(violations).isNotEmpty();
    }
}