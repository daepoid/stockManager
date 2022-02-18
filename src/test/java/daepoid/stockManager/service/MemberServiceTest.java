package daepoid.stockManager.service;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Duty createDuty() {
        String dutyName = "dutyName";
        double dutyIncentive = 4.56;
        Set<Member> dutyMembers = new HashSet<>();

        return Duty.builder()
                .name(dutyName)
                .incentive(dutyIncentive)
                .members(dutyMembers)
                .build();
    }

    @Test
    void join() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
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
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMember(memberId)).isEqualTo(member);
        assertThat(memberService.findMember(memberId).getId()).isEqualTo(member.getId());
    }

    @Test
    void findMemberByLoginId() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMemberByLoginId(loginId)).isEqualTo(member);
        assertThat(memberService.findMemberByLoginId(loginId).getId()).isEqualTo(memberId);
    }

    @Test
    void findMembers() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembers().contains(member)).isTrue();
        assertThat(memberService.findMembers().stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findMembersByName() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(member.getUserName()).isEqualTo(userName);
        assertThat(memberService.findMembersByUserName(userName).contains(member)).isTrue();
        assertThat(memberService.findMembersByUserName(userName).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findMemberByPhoneNumber() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(member.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(memberService.findMemberByPhoneNumber(phoneNumber)).isEqualTo(member);
        assertThat(memberService.findMemberByPhoneNumber(phoneNumber).getId()).isEqualTo(memberId);
    }

    @Test
    void findMembersByGradeType() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembersByGradeType(gradeType).contains(member)).isTrue();
        assertThat(memberService.findMembersByGradeType(gradeType).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findMemberByMemberStatus() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembersByMemberStatus(memberStatus).contains(member)).isTrue();
        assertThat(memberService.findMembersByMemberStatus(memberStatus).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findMembersByDuty() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
        List<Duty> duties = new ArrayList<>();

        Duty duty = createDuty();
        em.persist(duty);
        duties.add(duty);

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        assertThat(memberService.findMembersByDuty(duty).contains(member)).isTrue();
        assertThat(memberService.findMembersByDuty(duty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeName() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        String newName = "new member name";
        memberService.changeUserName(memberId, newName);

        assertThat(member.getUserName()).isEqualTo(newName);
        assertThat(memberService.findMember(memberId).getUserName()).isEqualTo(newName);
        assertThat(memberService.findMembersByUserName(newName).contains(member)).isTrue();
        assertThat(memberService.findMembersByGradeType(gradeType).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changePhoneNumber() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
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
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
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
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        GradeType newGradeType = GradeType.CHEF;
        memberService.changeGradeType(memberId, newGradeType);

        assertThat(member.getGradeType()).isEqualTo(newGradeType);
        assertThat(memberService.findMember(memberId).getGradeType()).isEqualTo(newGradeType);
        assertThat(memberService.findMembersByGradeType(newGradeType).contains(member)).isTrue();
        assertThat(memberService.findMembersByGradeType(newGradeType).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeMemberStatus() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        MemberStatus memberStatus = MemberStatus.LEAVE;
        List<Duty> duties = new ArrayList<>();

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
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

    @Test
    void changeDuties() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
        List<Duty> duties = new ArrayList<>();

        Duty duty = createDuty();
        em.persist(duty);

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        List<Duty> newDuties = new ArrayList<>();
        Duty newDuty = createDuty();
        em.persist(newDuty);
        newDuties.add(newDuty);
        memberService.changeDuties(memberId, newDuties);

        assertThat(member.getDuties().contains(newDuty)).isTrue();
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(newDuty.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNull();

        assertThat(memberService.findMember(memberId).getDuties().contains(newDuty)).isTrue();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(newDuty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMember(memberId).getDuties().contains(duty)).isFalse();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNull();

        assertThat(memberService.findMembersByDuty(newDuty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMembersByDuty(duty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void addDuty() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
        List<Duty> duties = new ArrayList<>();

        Duty duty = createDuty();
        em.persist(duty);

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        Duty newDuty = createDuty();
        em.persist(newDuty);

        memberService.addDuty(memberId, duty, newDuty);

        assertThat(member.getDuties().contains(newDuty)).isTrue();
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(newDuty.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMember(memberId).getDuties().contains(newDuty)).isTrue();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(newDuty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMember(memberId).getDuties().contains(duty)).isTrue();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMembersByDuty(newDuty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMembersByDuty(duty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void removeDuty() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
        List<Duty> duties = new ArrayList<>();

        Duty duty = createDuty();
        em.persist(duty);

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        Duty newDuty = createDuty();
        em.persist(newDuty);

        memberService.addDuty(memberId, duty, newDuty);

        assertThat(member.getDuties().contains(newDuty)).isTrue();
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(newDuty.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMember(memberId).getDuties().contains(newDuty)).isTrue();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(newDuty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMember(memberId).getDuties().contains(duty)).isTrue();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMembersByDuty(newDuty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();

        assertThat(memberService.findMembersByDuty(duty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNotNull();

        memberService.removeDuty(memberId, duty);
        assertThat(member.getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNull();
        assertThat(memberService.findMember(memberId).getDuties().stream()
                .filter(d -> d.getId().equals(duty.getId()))
                .findFirst().orElse(null)).isNull();
        assertThat(memberService.findMembersByDuty(duty).stream()
                .filter(m -> m.getId().equals(memberId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void removeMember() {
        String loginId = "member";
        String password = "123";
        String userName = "name";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.UNDEFINED;
        MemberStatus memberStatus = MemberStatus.UNDEFINED;
        List<Duty> duties = new ArrayList<>();

        Duty duty = createDuty();
        em.persist(duty);

        Member member = Member.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .userName(userName)
                .phoneNumber(phoneNumber)
                .gradeType(gradeType)
                .memberStatus(memberStatus)
                .duties(duties)
                .build();

        Long memberId = memberService.join(member);

        memberService.removeMember(memberId);
        assertThat(memberService.findMember(memberId)).isNull();
    }
}