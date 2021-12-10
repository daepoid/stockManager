package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public void saveMember(Member member) {
        memberRepository.saveMember(member);
    }

    public Member findMember(Long memberId) {
        return memberRepository.findMember(memberId);
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public List<Member> findMemberByName(String name) {
        return memberRepository.findByName(name);
    }

    public List<Member> findMemberByPhoneNumber(String phoneNumber) {
        return memberRepository.findByPhoneNumber(phoneNumber);
    }

    public List<Member> findMembersByGradeType(GradeType gradeType) {
        return memberRepository.findByGradeType(gradeType);
    }

}
