package daepoid.stockManager.config;

import daepoid.stockManager.handler.LoginFailureHandler;
import daepoid.stockManager.handler.LoginSuccessHandler;
import daepoid.stockManager.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MemberService memberService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**", "/*.ico", "/error");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // csrf 코튼 검사 비활성화
            .csrf()
                .disable()
            .authorizeRequests()
                // .antMatchers("/login", "/signUp")
                .antMatchers("/members/new", "/login", "/signup")
                    .permitAll()
                // 나머지 요청들은 종류에 상관없이 권한이 있어야 접근 가능하다.
                .anyRequest()
                    .authenticated()
            .and()
            // form을 통한 로그인 활성화, custom login form page를 보여줗 url을 지정
            .formLogin()
                .loginPage("/login")
                .failureHandler(new LoginFailureHandler())
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler(new LoginSuccessHandler())
            .and()
            // logout을 csrf와 사용하는 경우 무조건 post를 통해 logout을 해야한다.
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true);
    }
}
