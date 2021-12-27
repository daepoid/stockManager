package daepoid.stockManager.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Getter
@Setter
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private String loginIdName;
    private String loginPasswordName;
    private String errorMessageName;
    private String defaultFailureUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        loginIdName = request.getParameter("loginId");
        log.info("loginId = {}", loginIdName);
        loginPasswordName = request.getParameter("password");

        response.sendRedirect("/members/failLogin");
    }
}
