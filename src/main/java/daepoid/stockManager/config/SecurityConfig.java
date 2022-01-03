package daepoid.stockManager.config;

import daepoid.stockManager.handler.AuthFailureHandler;
import daepoid.stockManager.handler.AuthSuccessHandler;
import daepoid.stockManager.service.SecurityLoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityLoginService securityLoginService;
    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

//    @Bean
//    public AuthSuccessHandler authSuccessHandler() {
//        log.info("loginSuccessHandler");
//        return new AuthSuccessHandler();
//    }
//
//    @Bean
//    public AuthFailureHandler authFailureHandler() {
//        log.info("loginFailureHandler");
//        return new AuthFailureHandler();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityLoginService)
                .passwordEncoder(passwordEncoder());
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
                .antMatchers("/members/new").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                // 나머지 요청들은 종류에 상관없이 권한이 있어야 접근 가능하다.
                .anyRequest()
                    .authenticated()
            .and()
            // form을 통한 로그인 활성화, custom login form page를 보여줗 url을 지정
            .formLogin()
                .loginPage("/login")
                .failureHandler(authFailureHandler).permitAll()
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler(authSuccessHandler)
                .and()
            // logout을 csrf와 사용하는 경우 무조건 post를 통해 logout을 해야한다.
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .permitAll()
            .and()
                .sessionManagement()
                .maximumSessions(1);

    }
}
