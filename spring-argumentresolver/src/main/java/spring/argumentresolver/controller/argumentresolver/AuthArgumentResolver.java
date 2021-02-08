package spring.argumentresolver.controller.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import spring.argumentresolver.controller.annotation.Login;
import spring.argumentresolver.domain.User;
import spring.argumentresolver.dto.LoginUser;
import spring.argumentresolver.service.UserService;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserService userService;

    public AuthArgumentResolver(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Login.class);
    }

    @Override
    public Object resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        final String userId = webRequest.getHeader("userId");
        try {
            final User user = userService.findByIdentification(userId);
            return new LoginUser(user.getId(), user.getIdentification());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
