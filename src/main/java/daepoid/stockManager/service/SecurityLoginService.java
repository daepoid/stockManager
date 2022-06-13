package daepoid.stockManager.service;

import daepoid.stockManager.domain.users.Member;
import daepoid.stockManager.domain.users.Customer;
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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityLoginService implements UserDetailsService {

    private final JpaMemberRepository memberRepository;
    private final JpaCustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("===================================================");
        log.info("user name = {}", username);
        log.info("===================================================");
        Optional<Member> member = memberRepository.findByLoginId(username);
        if(member.isPresent()) {
            String role = "ROLE_" + member.get().getGradeType().toString();
            return new User(member.get().getLoginId(), member.get().getPassword(), List.of(new SimpleGrantedAuthority(role)));
        }

        Optional<Customer> customer = customerRepository.findByLoginId(username);
        if(customer.isPresent()) {
            String role = "ROLE_CUSTOMER";
            return new User(customer.get().getLoginId(), customer.get().getPassword(), List.of(new SimpleGrantedAuthority(role)));
        }

        throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
    }
}
