package daepoid.stockManager.service;

import daepoid.stockManager.domain.users.GradeType;
import daepoid.stockManager.domain.users.Member;
import daepoid.stockManager.domain.search.MemberSearch;
import daepoid.stockManager.domain.users.MemberStatus;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    private final PasswordEncoder passwordEncoder;
    private final LoginUtilService loginUtilService;

    //==생성 로직==//
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    //==조회 로직==//
    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findMembers(int maxResult) {
        return memberRepository.findAll(maxResult);
    }

    public List<Member> findMembers(int firstResult, int maxResult) {
        return memberRepository.findAll(firstResult, maxResult);
    }

    public Optional<Member> findMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public List<Member> findMembersByUserName(String userName) {
        return memberRepository.findByUserName(userName);
    }

    public List<Member> findMembersByGradeType(GradeType gradeType) {
        return memberRepository.findByGradeType(gradeType);
    }

    public Optional<Member> findMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Member> findMembersByMemberStatus(MemberStatus memberStatus) {
        return memberRepository.findByMemberStatus(memberStatus);
    }

    public List<Member> findByMemberSearch(MemberSearch memberSearch) {
        return memberRepository.findByMemberSearch(memberSearch);
    }

    //==수정 로직==//
    @Transactional
    public boolean changeLoginId(Long memberId, String loginId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            member.get().changeLoginId(loginId);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changePassword(Long memberId, String password) {
        if(loginUtilService.createPasswordValid(password)) {
            Optional<Member> member = memberRepository.findById(memberId);
            if(member.isPresent()) {
                member.get().changePassword(passwordEncoder.encode(password));
                return true;
            }
        }
        return false;
    }

    @Transactional
    public boolean changeUserName(Long memberId, String userName) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            member.get().changeUserName(userName);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeGradeType(Long memberId, GradeType gradeType) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            member.get().changeGradeType(gradeType);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changePhoneNumber(Long memberId, String phoneNumber) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            member.get().changePhoneNumber(phoneNumber);
            return true;
        }
        return false;
    }

    @Transactional
    public boolean changeMemberStatus(Long memberId, MemberStatus memberStatus) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            member.get().changeMemberStatus(memberStatus);
            return true;
        }
        return false;
    }

    //==비즈니스 로직==//

    //==삭제 로직==//
    @Transactional
    public boolean removeMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            memberRepository.remove(memberId);
            return true;
        }
        return false;
    }
}
