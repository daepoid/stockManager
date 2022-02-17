package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.repository.jpa.JpaCustomerRepository;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityLoginService implements UserDetailsService {

    private final JpaMemberRepository memberRepository;
    private final JpaCustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("userName = {}", username);
        Member member = memberRepository.findByLoginId(username);
        log.info("member = {}", member);
        if(member != null) {
            String role = "ROLE_" + member.getGradeType().toString();
            return new User(member.getLoginId(), member.getPassword(), List.of(new SimpleGrantedAuthority(role)));
        }

        Customer customer = customerRepository.findByLoginId(username);
        log.info("customer = {}", customer);
        if(customer != null) {
            String role = "ROLE_CUSTOMER";
            return new User(customer.getLoginId(), customer.getPassword(), List.of(new SimpleGrantedAuthority(role)));
        }

        throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
    }
}
