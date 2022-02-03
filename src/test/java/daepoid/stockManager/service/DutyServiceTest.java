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

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class DutyServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    DutyService dutyService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void saveDuty() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(duty.getId()).isEqualTo(dutyId);
    }

    @Test
    void findDuty() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findDuty(dutyId)).isEqualTo(duty);
    }

    @Test
    void findDuties() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findDuties().contains(duty)).isEqualTo(true);
    }

    @Test
    void findByName() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findByName(name).contains(duty)).isEqualTo(true);
    }

    @Test
    void findByMember() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Member member = Member.builder()
                .loginId("member")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();

        em.persist(member);

        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findByMember(member).contains(duty)).isEqualTo(true);
    }

    @Test
    void findIncentive() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findByIncentive(incentive).contains(duty)).isEqualTo(true);
    }

    @Test
    void findUnderIncentive() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);
        
        assertThat(dutyService.findUnderIncentive(incentive).contains(duty)).isEqualTo(true);
    }

    @Test
    void findOverIncentive() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findOverIncentive(incentive).contains(duty)).isEqualTo(true);
    }

    @Test
    void changeName() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        dutyService.changeName(dutyId, name + name);

        assertThat(dutyService.findDuty(dutyId).getName()).isEqualTo(name + name);
    }

    @Test
    void changeMembers() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        Set<Member> newMembers = new HashSet<>();

        Member member = Member.builder()
                .loginId("member")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();

        em.persist(member);

        newMembers.add(member);
        dutyService.changeMembers(dutyId, newMembers);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isEqualTo(true);
        assertThat(dutyService.findByMember(member).contains(duty)).isEqualTo(true);
    }

    @Test
    void addMember() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        Member member = Member.builder()
                .loginId("member")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();

        em.persist(member);
        dutyService.addMember(dutyId, member);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isEqualTo(true);
        assertThat(dutyService.findByMember(member).contains(duty)).isEqualTo(true);
    }

    @Test
    void removeMember() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        Member member = Member.builder()
                .loginId("member")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();

        em.persist(member);
        dutyService.addMember(dutyId, member);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isEqualTo(true);
        assertThat(dutyService.findByMember(member).contains(duty)).isEqualTo(true);

        dutyService.removeMember(dutyId, member);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isEqualTo(false);
        assertThat(dutyService.findByMember(member).contains(duty)).isEqualTo(false);
    }

    @Test
    void changeIncentive() {
        String name = "name";
        double incentive = 12.3;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        dutyService.changeIncentive(dutyId, incentive * 100);

        assertThat(dutyService.findDuty(dutyId).getIncentive()).isEqualTo(incentive * 100);
        assertThat(dutyService.findByIncentive(incentive * 100).contains(duty)).isEqualTo(true);
        assertThat(duty.getIncentive()).isEqualTo(incentive * 100);
    }
}