package daepoid.stockManager.service;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    public void saveMember_findMember() throws Exception {
        // given
        String loginId = "admin";
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST1;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(loginId, name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberService.join(member);

        // when
        Member findMember = memberService.findMember(member.getId());

        // then
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findMembers() throws Exception {
        // given
        String loginId = "admin";
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST1;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(loginId, name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberService.join(member);

        // when
        List<Member> findMembers = memberService.findMembers();

        // then
        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
        Assertions.assertThat(findMembers.size()).isEqualTo(1);
    }


    @Test
    public void findMemberByName() throws Exception {
        // given
        String loginId = "admin";
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST1;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(loginId, name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberService.join(member);

        // when
        List<Member> findMember = memberService.findMembersByName(name);

        // then
        Assertions.assertThat(findMember.contains(member)).isEqualTo(true);
        Assertions.assertThat(findMember.size()).isEqualTo(1);
    }

    @Test
    public void findMemberByPhoneNumber() throws Exception {
        // given
        String loginId = "admin";
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST1;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(loginId, name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberService.join(member);

        // when
        Member findMember = memberService.findMemberByPhoneNumber(phoneNumber);

        // then
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findMembersByGradeType() throws Exception {
        // given
        String loginId = "admin";
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST1;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(loginId, name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberService.join(member);

        // when
        List<Member> findMember = memberService.findMembersByGradeType(gradeType);

        // then
        Assertions.assertThat(findMember.contains(member)).isEqualTo(true);
        Assertions.assertThat(findMember.size()).isEqualTo(1);
    }
}