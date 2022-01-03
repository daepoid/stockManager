package daepoid.stockManager.handler;

import daepoid.stockManager.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        //requestURI로 다시 보내주기 위해 URI가 잘 넘어오는지 확인해야한다.
        String requestURI = request.getRequestURI();
        log.info("onAuthenticationSuccess requestURI = {}", requestURI);

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.SECURITY_LOGIN, authentication.getName());
        response.sendRedirect("/");
    }
}
