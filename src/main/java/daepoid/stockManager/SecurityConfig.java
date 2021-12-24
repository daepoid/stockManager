package daepoid.stockManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // csrf 코튼 검사 비활성화
                .csrf()
                .disable()
                // form을 통한 로그인 활성화, custom login form page를 보여줗 url을 지정
                .formLogin()
                .loginPage("/login")
                .usernameParameter("loginId");
    }
}
