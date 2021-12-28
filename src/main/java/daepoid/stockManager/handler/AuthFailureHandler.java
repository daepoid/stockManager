package daepoid.stockManager.handler;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Getter
@Setter
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
//        request.getParameter("loginId");
        String message = "Invalid UserId or Password";

        if(exception instanceof DisabledException) {
            message = "DisabledException";
        } else if(exception instanceof CredentialsExpiredException) {
            message = "CredentialsExpiredException";
        } else if(exception instanceof BadCredentialsException) {
            message = "BadCredentialsException";
        }

        setDefaultFailureUrl("/login?error=true&exception=" + message);

        super.onAuthenticationFailure(request, response, exception);
    }
}
