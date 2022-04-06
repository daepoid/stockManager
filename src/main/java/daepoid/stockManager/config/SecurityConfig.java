package daepoid.stockManager.config;

import com.nimbusds.oauth2.sdk.auth.JWTAuthentication;
import daepoid.stockManager.handler.AuthFailureHandler;
import daepoid.stockManager.handler.AuthSuccessHandler;
import daepoid.stockManager.service.SecurityLoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
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
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .antMatchers("resources/**", "/css/**", "/js/**", "/img/**", "/*.ico", "/error", "/images/**", "/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            // 교차출처 리소스 공유(CORS)
            .cors()
                .and()
            // csrf 코튼 검사 비활성화
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/api/**").permitAll() // api 관련 경로는 열어준다.
                .antMatchers("/members/new/**").permitAll() // 회원가입
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/members/**", "/duties/**", "/customer-management/**", "/menu-management/**", "/order-management/**").hasAnyRole("CEO", "MANAGER")
                .antMatchers("/customers/**").hasAnyRole("CUSTOMER")
                .antMatchers("/menus/**", "/my-info").authenticated()
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
        ;
    }
}
