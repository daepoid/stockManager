package daepoid.stockManager.integration.jpa;

import daepoid.stockManager.domain.duty.Duty;
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

import java.util.ArrayList;
import java.util.List;

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

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findById(memberId)).isEqualTo(member);
        assertThat(memberRepository.findById(memberId).getId()).isEqualTo(member.getId());
    }

    @Test
    void findByLoginId() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByLoginId(loginId)).isEqualTo(member);
        assertThat(memberRepository.findByLoginId(loginId).getId()).isEqualTo(memberId);
    }

    @Test
    void findAll() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findAll().contains(member)).isTrue();
        assertThat(memberRepository.findAll().stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByName() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByName(name).contains(member)).isTrue();
        assertThat(memberRepository.findByName(name).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByPhoneNumber() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByGradeType(gradeType).contains(member)).isTrue();
        assertThat(memberRepository.findByGradeType(gradeType).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByMemberStatus() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        assertThat(memberRepository.findByMemberStatus(memberStatus).contains(member)).isTrue();
        assertThat(memberRepository.findByMemberStatus(memberStatus).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
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

        Long memberId = memberRepository.save(member);

        String newName = "new Name";
        memberRepository.changeName(memberId, newName);
        assertThat(member.getName()).isEqualTo(newName);
        assertThat(memberRepository.findById(memberId).getName()).isEqualTo(newName);
        assertThat(memberRepository.findByName(newName).contains(member)).isTrue();
        assertThat(memberRepository.findByName(newName).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changePassword() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        String newPassword = "456";
        memberRepository.changePassword(memberId, passwordEncoder.encode(newPassword));
        assertThat(passwordEncoder.matches(newPassword, member.getPassword())).isTrue();
        assertThat(passwordEncoder.matches(newPassword, memberRepository.findById(memberId).getPassword())).isTrue();
    }

    @Test
    void changePhoneNumber() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        String newPhoneNumber = "01011112222";
        memberRepository.changePhoneNumber(memberId, newPhoneNumber);
        assertThat(member.getPhoneNumber()).isEqualTo(newPhoneNumber);
        assertThat(memberRepository.findByPhoneNumber(newPhoneNumber)).isEqualTo(member);
        assertThat(memberRepository.findByPhoneNumber(newPhoneNumber).getId()).isEqualTo(memberId);
    }

    @Test
    void changeGradeType() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        GradeType newGradeType = GradeType.PART_TIME;
        memberRepository.changeGradeType(memberId, newGradeType);
        assertThat(member.getGradeType()).isEqualTo(newGradeType);
        assertThat(memberRepository.findByGradeType(newGradeType).contains(member)).isTrue();
        assertThat(memberRepository.findByGradeType(newGradeType).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeMemberStatus() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        MemberStatus newMemberStatus = MemberStatus.WORK;
        memberRepository.changeMemberStatus(memberId, newMemberStatus);
        assertThat(member.getMemberStatus()).isEqualTo(newMemberStatus);
        assertThat(memberRepository.findByMemberStatus(memberStatus).contains(member)).isFalse();
        assertThat(memberRepository.findByMemberStatus(newMemberStatus).contains(member)).isTrue();
        assertThat(memberRepository.findByMemberStatus(newMemberStatus).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void removeMember() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        memberRepository.removeMember(member);
        assertThat(memberRepository.findById(memberId)).isNull();
    }

    @Test
    void removeById() {
        String loginId = "member";
        String password = "123";
        String name = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
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

        Long memberId = memberRepository.save(member);

        memberRepository.removeById(memberId);
        assertThat(memberRepository.findById(memberId)).isNull();
    }
}