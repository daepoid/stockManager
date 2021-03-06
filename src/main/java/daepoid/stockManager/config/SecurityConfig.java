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
            // ???????????? ????????? ??????(CORS)
            .cors()
                .and()
            // csrf ?????? ?????? ????????????
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/api/**").permitAll() // api ?????? ????????? ????????????.
                .antMatchers("/members/new/**").permitAll() // ????????????
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/members/**", "/duties/**", "/customer-management/**", "/menu-management/**", "/order-management/**").hasAnyRole("CEO", "MANAGER")
                .antMatchers("/customers/**").hasAnyRole("CUSTOMER")
                .antMatchers("/menus/**", "/my-info").authenticated()
                // ????????? ???????????? ????????? ???????????? ????????? ????????? ?????? ????????????.
                .anyRequest()
                    .authenticated()
                .and()
            // form??? ?????? ????????? ?????????, custom login form page??? ????????? url??? ??????
            .formLogin()
                .loginPage("/login")
                .failureHandler(authFailureHandler).permitAll()
//                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/")
                .usernameParameter("loginId")
                .passwordParameter("password")
                .successHandler(authSuccessHandler)
                .and()
            // logout??? csrf??? ???????????? ?????? ????????? post??? ?????? logout??? ????????????.
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .permitAll()
        ;
    }
}
