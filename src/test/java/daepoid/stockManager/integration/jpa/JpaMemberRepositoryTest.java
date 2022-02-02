package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.repository.jpa.JpaMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaMemberRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaMemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void save() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(member.getId()).isEqualTo(memberId);
        assertThat(member).isEqualTo(em.find(Member.class, memberId));
    }

    @Test
    void findById() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findById(memberId)).isEqualTo(member);
    }

    @Test
    void findByLoginId() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByLoginId(loginId)).isEqualTo(member);
    }

    @Test
    void findAll() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findAll().contains(member)).isEqualTo(true);
    }

    @Test
    void findByName() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByName(name).contains(member)).isEqualTo(true);
    }

    @Test
    void findByPhoneNumber() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByPhoneNumber(phoneNumber)).isEqualTo(member);
    }

    @Test
    void findByGradeType() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByGradeType(gradeType).contains(member)).isEqualTo(true);
    }

    @Test
    void findByMemberStatus() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByMemberStatus(memberStatus).contains(member)).isEqualTo(true);
    }

    @Test
    void findByRoles() {

    }

    @Test
    void changeName() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        memberRepository.changeName(memberId, name + name);
        assertThat(member.getName()).isEqualTo(name + name);
        assertThat(memberRepository.findById(memberId).getName()).isEqualTo(name + name);
        assertThat(memberRepository.findByName(name + name).contains(member)).isEqualTo(true);
    }

    @Test
    void changePassword() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        memberRepository.changePassword(memberId, passwordEncoder.encode(password + password));
        assertThat(passwordEncoder.matches(password + password, member.getPassword())).isEqualTo(true);
        assertThat(passwordEncoder.matches(password + password, memberRepository.findById(memberId).getPassword())).isEqualTo(true);
    }

    @Test
    void changePhoneNumber() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        String newPhoneNumber = "01011112222";
        memberRepository.changePhoneNumber(memberId, newPhoneNumber);
        assertThat(member.getPhoneNumber()).isEqualTo(newPhoneNumber);
        assertThat(memberRepository.findByPhoneNumber(newPhoneNumber)).isEqualTo(member);
    }

    @Test
    void changeGradeType() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        memberRepository.changeGradeType(memberId, GradeType.PART_TIME);
        assertThat(member.getGradeType()).isEqualTo(GradeType.PART_TIME);
        assertThat(memberRepository.findByGradeType(GradeType.PART_TIME).contains(member)).isEqualTo(true);
    }

    @Test
    void changeMemberStatus() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        memberRepository.changeMemberStatus(memberId, MemberStatus.WORK);
        assertThat(member.getMemberStatus()).isEqualTo(MemberStatus.WORK);
        assertThat(memberRepository.findByMemberStatus(MemberStatus.UNDEFINED).contains(member)).isEqualTo(false);
        assertThat(memberRepository.findByMemberStatus(MemberStatus.WORK).contains(member)).isEqualTo(true);
    }

    @Test
    void removeMember() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        memberRepository.removeMember(member);
        assertThat(memberRepository.findById(memberId)).isEqualTo(null);

    }

    @Test
    void removeById() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .build();

        Long memberId = memberRepository.save(member);

        memberRepository.removeById(memberId);
        assertThat(memberRepository.findById(memberId)).isEqualTo(null);
    }
}