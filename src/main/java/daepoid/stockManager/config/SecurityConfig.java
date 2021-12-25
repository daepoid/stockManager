package daepoid.stockManager.config;

import daepoid.stockManager.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // csrf 코튼 검사 비활성화
            .csrf()
                .disable()
            .authorizeRequests()
                // interceptor에 있는 정보를 가져왔다.
                // .antMatchers("/login", "/signUp")
                .antMatchers("/members/new", "/login", "/logout", "/css/**", "/*.ico", "/error")
                    .permitAll()
                .anyRequest()
                .authenticated()
            .and()
            // form을 통한 로그인 활성화, custom login form page를 보여줗 url을 지정
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
            .and()
            // logout을 csrf와 사용하는 경우 무조건 post를 통해 logout을 해야한다.
            .logout()
                .logoutUrl("/doLogout")
                .logoutSuccessUrl("/login");
    }
}
