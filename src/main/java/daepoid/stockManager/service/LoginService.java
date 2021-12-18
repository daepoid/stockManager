package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.MemberRepository;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    // 주의: bean config 파일 설정 방법 숙지 후 사용하는 것으로 수정하기
    private final JpaMemberRepository memberRepository;

    /**
     * 간편 로그인을 개발해야하는 경우 로그인 부분을 따로 분리하여 개발 가능
     * @param loginId
     * @param password
     * @return
     */
    public boolean login(String loginId, String password) {
        Member findMember = memberRepository.findByLoginId(loginId);
        log.info("findMember = {}", findMember);

        return findMember != null && findMember.getPassword().equals(password);
    }

}