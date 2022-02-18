package daepoid.stockManager.service;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.repository.jpa.JpaDutyRepository;
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
    DutyService dutyService;

    @Autowired
    EntityManager em;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Member createMember() {
        String memberLoginId = "loginId";
        String memberPassword = "123";
        String memberName = "member name";
        String memberPhoneNumber = "01012341234";
        GradeType memberGradeType = GradeType.UNDEFINED;
        MemberStatus memberMemberStatus = MemberStatus.UNDEFINED;

        return Member.builder()
                .loginId(memberLoginId)
                .password(passwordEncoder.encode(memberPassword))
                .userName(memberName)
                .phoneNumber(memberPhoneNumber)
                .gradeType(memberGradeType)
                .memberStatus(memberMemberStatus)
                .build();
    }

    @Test
    void saveDuty() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

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
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

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
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findDuties().contains(duty)).isTrue();
        assertThat(dutyService.findDuties().stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByName() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findByName(name).contains(duty)).isTrue();
        assertThat(dutyService.findByName(name).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findByMember() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findByMember(member).contains(duty)).isTrue();
        assertThat(dutyService.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findIncentive() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findByIncentive(incentive).contains(duty)).isTrue();
        assertThat(dutyService.findByIncentive(incentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findUnderIncentive() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findUnderIncentive(incentive).contains(duty)).isTrue();
        assertThat(dutyService.findUnderIncentive(incentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void findOverIncentive() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        assertThat(dutyService.findOverIncentive(incentive).contains(duty)).isTrue();
        assertThat(dutyService.findOverIncentive(incentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void changeName() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        String newName = "new duty name";
        dutyService.changeName(dutyId, newName);

        assertThat(dutyService.findDuty(dutyId).getName()).isEqualTo(newName);
        assertThat(dutyService.findByName(newName).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
        assertThat(dutyService.findByName(name).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void changeMembers() {
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        Member member = createMember();
        em.persist(member);

        Set<Member> newMembers = new HashSet<>();
        newMembers.add(member);
        dutyService.changeMembers(dutyId, newMembers);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isTrue();
        assertThat(dutyService.findDuty(dutyId).getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(dutyService.findByMember(member).contains(duty)).isTrue();
        assertThat(dutyService.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void addMember() {
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        Member member = createMember();
        em.persist(member);
        dutyService.addMember(dutyId, member);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isTrue();
        assertThat(dutyService.findDuty(dutyId).getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(dutyService.findByMember(member).contains(duty)).isTrue();
        assertThat(dutyService.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    void removeMember() {
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        Member member = createMember();
        em.persist(member);
        dutyService.addMember(dutyId, member);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isTrue();
        assertThat(dutyService.findDuty(dutyId).getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNotNull();

        assertThat(dutyService.findByMember(member).contains(duty)).isTrue();
        assertThat(dutyService.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();

        dutyService.removeMember(dutyId, member);

        assertThat(dutyService.findDuty(dutyId).getMembers().contains(member)).isFalse();
        assertThat(dutyService.findDuty(dutyId).getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNull();

        assertThat(dutyService.findByMember(member).contains(duty)).isFalse();
        assertThat(dutyService.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    void changeIncentive() {
        Member member = createMember();
        em.persist(member);

        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();
        members.add(member);

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        Long dutyId = dutyService.saveDuty(duty);

        double newIncentive = 4.56;
        dutyService.changeIncentive(dutyId, newIncentive);

        assertThat(duty.getIncentive()).isEqualTo(newIncentive);
        assertThat(dutyService.findDuty(dutyId).getIncentive()).isEqualTo(newIncentive);
        assertThat(dutyService.findByIncentive(newIncentive).contains(duty)).isTrue();
        assertThat(dutyService.findByIncentive(newIncentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }
}