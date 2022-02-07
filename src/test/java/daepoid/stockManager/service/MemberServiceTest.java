package daepoid.stockManager.service;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.repository.MemberRepository;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    JpaMemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void join() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberId).isEqualTo(member.getId());
    }

    @Test
    void findMember() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMember(memberId)).isEqualTo(member);
    }

    @Test
    void findMemberByLoginId() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMemberByLoginId(loginId)).isEqualTo(member);
    }

    @Test
    void findMembers() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembers().contains(member)).isTrue();
    }

    @Test
    void findMembersByName() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembersByName(name).contains(member)).isTrue();
    }

    @Test
    void findMemberByPhoneNumber() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMemberByPhoneNumber(phoneNumber)).isEqualTo(member);
    }

    @Test
    void findMembersByGradeType() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembersByGradeType(gradeType).contains(member)).isTrue();
    }

    @Test
    void changeName() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        memberService.changeName(memberId, name + name);
        assertThat(memberService.findMember(memberId).getName()).isEqualTo(name + name);
        assertThat(memberService.findMembersByName(name + name).contains(member)).isTrue();
    }

    @Test
    void changePhoneNumber() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        String newPhoneNumber = "01011112222";
        memberService.changePhoneNumber(memberId, newPhoneNumber);
        assertThat(memberService.findMember(memberId).getPhoneNumber()).isEqualTo(newPhoneNumber);
        assertThat(memberService.findMemberByPhoneNumber(newPhoneNumber)).isEqualTo(member);
    }

    @Test
    void changePassword() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        String newPassword = "456";
        memberService.changePassword(memberId, passwordEncoder.encode(newPassword));
        assertThat(passwordEncoder.matches(newPassword, memberService.findMember(memberId).getPassword())).isTrue();
        assertThat(passwordEncoder.matches(newPassword, member.getPassword())).isTrue();
    }

    @Test
    void changeGradeType() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        GradeType newGradeType = GradeType.CHEF;
        memberService.changeGradeType(memberId, newGradeType);
        assertThat(memberService.findMember(memberId).getGradeType()).isEqualTo(newGradeType);
        assertThat(memberService.findMembersByGradeType(newGradeType).contains(member)).isTrue();
        assertThat(member.getGradeType()).isEqualTo(newGradeType);
    }

    @Test
    void changeMemberStatus() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        MemberStatus newMemberStatus = MemberStatus.RETIRE;
        memberService.changeMemberStatus(memberId, newMemberStatus);
        assertThat(member.getMemberStatus()).isEqualTo(newMemberStatus);
        assertThat(memberService.findMember(memberId).getMemberStatus()).isEqualTo(newMemberStatus);
    }
}