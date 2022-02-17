package daepoid.stockManager.service;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
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

    //==생성 로직==//
    @Transactional
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    //==조회 로직==//
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public Member findMemberByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findMembersByName(String name) {
        return memberRepository.findByName(name);
    }

    public Member findMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Member> findMembersByGradeType(GradeType gradeType) {
        return memberRepository.findByGradeType(gradeType);
    }

    public List<Member> findMembersByMemberStatus(MemberStatus memberStatus) {
        return memberRepository.findByMemberStatus(memberStatus);
    }

    public List<Member> findMembersByDuty(Duty duty) {
        return memberRepository.findByDuty(duty);
    }

    //==수정 로직==//
    @Transactional
    public void changeName(Long memberId, String name) {
        memberRepository.changeName(memberId, name);
    }

    @Transactional
    public void changePhoneNumber(Long memberId, String phoneNumber) {
        memberRepository.changePhoneNumber(memberId, phoneNumber);
    }

    @Transactional
    public void changePassword(Long memberId, String password) {
        memberRepository.changePassword(memberId, password);
    }

    @Transactional
    public void changeGradeType(Long memberId, GradeType gradeType) {
        memberRepository.changeGradeType(memberId, gradeType);
    }

    @Transactional
    public void changeMemberStatus(Long memberId, MemberStatus memberStatus) {
        memberRepository.changeMemberStatus(memberId, memberStatus);
    }

    @Transactional
    public void changeDuties(Long memberId, List<Duty> duties) {
        memberRepository.changeDuties(memberId, duties);
    }

    @Transactional
    public void addDuty(Long memberId, Duty... duties) {
        memberRepository.addDuty(memberId, duties);
    }

    @Transactional
    public void removeDuty(Long memberId, Duty... duties) {
        memberRepository.removeDuty(memberId, duties);
    }

    //==비즈니스 로직==//

    //==삭제 로직==//
    @Transactional
    public void removeMember(Member member) {
        memberRepository.removeMember(member);
    }

    @Transactional
    public void removeById(Long memberId) {
        memberRepository.removeById(memberId);
    }
}
