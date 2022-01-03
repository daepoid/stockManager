package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.DataJpaMemberRepository;
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

//    private final DataJpaMemberRepository memberRepository;
    private final JpaMemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByLoginId(username);
        if(member == null) {
            log.info("SecurityLoginService loadUserByUsername 로그인 오류");
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        String role = "ROLE_" + member.getGradeType().toString();
        return new User(member.getLoginId(), member.getPassword(), List.of(new SimpleGrantedAuthority(role)));
    }
}
