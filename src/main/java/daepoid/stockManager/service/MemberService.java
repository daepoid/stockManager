package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    /**
     * 서비스 계층에서 데이터의 유효성을 추가적으로 검증하는 것이 좋은가?
     * 아니면 데이터과 가까운 리포지토리의 위치에서 데이터의 유효성을 검사하는 것이 좋은가?
     */

    private final JpaMemberRepository memberRepository;
//    private final MemoryMemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {

        // 중복확인 로직 보류
//        // 전화번호 중복인 경우 가입 불가
//        // 전화번호, 이름 등 필수값들 확인은 이전에 완료
//        List<Member> findMembers = memberRepository.findByPhoneNumber(member.getPhoneNumber());
//        if(findMembers.size() > 0) {
//            throw new IllegalStateException("사용자 전화번호가 중복입니다.");
//        }
        memberRepository.save(member);
        return member.getId();
    }

    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).orElse(null);
    }

    public Member findMemberByLoginId(String loginId) {
        List<Member> findMembers = memberRepository.findByLoginId(loginId);
        if(findMembers.size() != 1) {
            return null;
        }
        return findMembers.get(0);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findMembersByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> findMembersByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Member> findMembersByGradeType(GradeType gradeType) {
        return memberRepository.findByGradeType(gradeType);
    }

    /**
     * 간편 로그인을 개발해야하는 경우 로그인 부분을 따로 분리하여 개발 가능
     * @param loginId
     * @param password
     * @return
     */
    public Member login(String loginId, String password) {
        List<Member> findMembers = memberRepository.findByLoginId(loginId);
        if(findMembers.size() != 1) {
            return null;
        }

        Member member = findMembers.get(0);
        if(member.getPassword().equals(password)) {
            return member;
        }
        return null;
    }
}
