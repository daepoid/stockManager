package daepoid.stockManager.jpa;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.repository.jpa.JpaDutyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class JpaDutyRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    JpaDutyRepository dutyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void save() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long savedId = dutyRepository.save(duty);

        // then
        assertThat(duty.getId()).isEqualTo(savedId);
    }

    @Test
    public void findById_실패() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long savedId = dutyRepository.save(duty);

        // then
        assertThat(dutyRepository.findById(123123L)).isNull();
    }

    @Test
    public void findById_성공() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        // then
        assertThat(dutyRepository.findById(dutyId)).isEqualTo(duty);
        assertThat(dutyRepository.findById(dutyId).getId()).isEqualTo(duty.getId());
    }

    @Test
    public void findAll() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName1";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        // when
        for(int i = 0; i < count; i++){
            Duty duty = Duty.builder()
                    .name(name + i)
                    .incentive(incentive + i)
                    .members(new HashSet<>())
                    .build();
            Long savedId = dutyRepository.save(duty);
        }

        // then
        assertThat(dutyRepository.findAll().size() + size).isEqualTo(size + count);
    }

    @Test
    public void findByName() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        // when
        String name1 = "dutyName1";
        double incentive1 = 1.21;
        Set<Member> members1 = new HashSet<>();

        Duty duty1 = Duty.builder()
                .name(name1)
                .incentive(incentive1)
                .members(members1)
                .build();

        String name2 = "dutyName2";
        double incentive2 = 1.22;
        Set<Member> members2 = new HashSet<>();

        Duty duty2 = Duty.builder()
                .name(name2)
                .incentive(incentive2)
                .members(members2)
                .build();

        Long dutyId1 = dutyRepository.save(duty1);
        Long dutyId2 = dutyRepository.save(duty2);

        // then
        assertThat(dutyRepository.findByName(name1).contains(duty1)).isTrue();
        assertThat(dutyRepository.findByName(name2).stream()
                .filter(d -> d.getId().equals(dutyId2))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByMember() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Member member = Member.builder()
                .loginId("loginId")
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

        // when
        Long dutyId = dutyRepository.save(duty);

        // then
        assertThat(dutyRepository.findByMember(member).contains(duty)).isTrue();
        assertThat(dutyRepository.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findByIncentive() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        // then
        assertThat(dutyRepository.findByIncentive(incentive).contains(duty)).isTrue();
        assertThat(dutyRepository.findByIncentive(incentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findUnderIncentive() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        // then
        assertThat(dutyRepository.findUnderIncentive(incentive).contains(duty)).isTrue();
        assertThat(dutyRepository.findUnderIncentive(incentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void findOverIncentive() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        // then
        assertThat(dutyRepository.findOverIncentive(incentive).contains(duty)).isTrue();
        assertThat(dutyRepository.findOverIncentive(incentive).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void changeName() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        String newName = "newDutyName";
        dutyRepository.changeName(dutyId, newName);
        // then

        assertThat(duty.getName()).isEqualTo(newName);
        assertThat(dutyRepository.findByName(newName).contains(duty)).isTrue();
        assertThat(dutyRepository.findByName(newName).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void changeMembers() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);

        Set<Member> newMembers = new HashSet<>();
        newMembers.add(member);
        dutyRepository.changeMembers(dutyId, newMembers);

        // then
        assertThat(duty.getMembers().contains(member)).isTrue();
        assertThat(dutyRepository.findById(dutyId).getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNotNull();
        assertThat(dutyRepository.findByMember(member).contains(duty)).isTrue();
        assertThat(dutyRepository.findByMember(member).stream()
                .filter(d -> d.getId().equals(dutyId))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void addMember() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);
        dutyRepository.addMember(dutyId, member);

        // then
        assertThat(duty.getMembers().contains(member)).isTrue();
        assertThat(duty.getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNotNull();
    }

    @Test
    public void removeMember() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);
        dutyRepository.addMember(dutyId, member);

        // then
        assertThat(duty.getMembers().contains(member)).isTrue();
        assertThat(duty.getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNotNull();

        dutyRepository.removeMember(dutyId, member);
        assertThat(duty.getMembers().contains(member)).isFalse();
        assertThat(duty.getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNull();
        assertThat(dutyRepository.findById(dutyId).getMembers().stream()
                .filter(m -> m.getId().equals(member.getId()))
                .findFirst().orElse(null)).isNull();
    }

    @Test
    public void changeIncentive() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 1.23;
        Set<Member> members = new HashSet<>();

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .members(members)
                .build();

        // when
        Long dutyId = dutyRepository.save(duty);

        assertThat(duty.getIncentive()).isEqualTo(incentive);
        assertThat(dutyRepository.findById(dutyId).getIncentive()).isEqualTo(incentive);

        double newIncentive = 4.56;
        dutyRepository.changeIncentive(dutyId, newIncentive);

        // then
        assertThat(duty.getIncentive()).isEqualTo(newIncentive);
        assertThat(dutyRepository.findById(dutyId).getIncentive()).isEqualTo(newIncentive);
    }
}