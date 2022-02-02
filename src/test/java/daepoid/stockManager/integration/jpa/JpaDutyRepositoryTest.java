package daepoid.stockManager.integration.jpa;

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
        double incentive = 0.0;

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
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
        double incentive = 0.0;

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .build();

        // when
        Long savedId = dutyRepository.save(duty);
        Duty findDuty = dutyRepository.findById(123123L);

        // then
        assertThat(findDuty).isEqualTo(null);
    }

    @Test
    public void findById_성공() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 0.0;

        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive)
                .build();

        // when
        Long savedId = dutyRepository.save(duty);
        Duty findDuty = dutyRepository.findById(savedId);

        // then
        assertThat(duty).isEqualTo(findDuty);
        assertThat(duty.getId()).isEqualTo(savedId);
    }

    @Test
    public void findAll() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName";
        double incentive = 0.0;

        // when
        for(int i = 0; i < count; i++){
            Duty duty = Duty.builder()
                    .name(name + i)
                    .incentive(incentive + i)
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

        String name = "dutyName";
        double incentive = 0.0;

        // when
        Duty duty1 = Duty.builder()
                .name(name + "1")
                .incentive(incentive + 1.0)
                .build();
        Long saved1Id = dutyRepository.save(duty1);

        Duty duty2 = Duty.builder()
                .name(name + "2")
                .incentive(incentive + 2.0)
                .build();
        Long saved2Id = dutyRepository.save(duty2);

        // then
        assertThat(dutyRepository.findByName(name + "2").contains(duty2)).isEqualTo(true);
        assertThat(dutyRepository.findByName(name + "2").stream().findFirst().get()).isEqualTo(duty2);
        assertThat(dutyRepository.findByUniqueName(name + "2")).isEqualTo(duty2);
    }

    @Test
    public void findByMember() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName";
        double incentive = 0.0;

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);

        Set<Member> members = new HashSet<>();
        members.add(member);

        // when
        Duty duty1 = Duty.builder()
                .name(name + "1")
                .incentive(incentive + 1.0)
                .members(members)
                .build();
        Long saved1Id = dutyRepository.save(duty1);

        Duty duty2 = Duty.builder()
                .name(name + "2")
                .incentive(incentive + 2.0)
                .members(new HashSet<Member>())
                .build();
        Long saved2Id = dutyRepository.save(duty2);

        // then
        assertThat(dutyRepository.findByMember(member).contains(duty1)).isEqualTo(true);

    }

    @Test
    public void findByIncentive() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName";
        double incentive = 0.0;

        // when
        Duty duty1 = Duty.builder()
                .name(name + "1")
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty1);

        Duty duty2 = Duty.builder()
                .name(name + "2")
                .incentive(incentive + 2.0)
                .members(new HashSet<Member>())
                .build();
        Long saved2Id = dutyRepository.save(duty2);

        // then
        assertThat(dutyRepository.findByIncentive(incentive + 1.0).contains(duty1)).isEqualTo(true);
    }

    @Test
    public void findUnderIncentive() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName";
        double incentive = 0.0;

        // when
        Duty duty1 = Duty.builder()
                .name(name + "1")
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty1);

        Duty duty2 = Duty.builder()
                .name(name + "2")
                .incentive(incentive + 2.0)
                .members(new HashSet<Member>())
                .build();
        Long saved2Id = dutyRepository.save(duty2);

        // then
        assertThat(dutyRepository.findUnderIncentive(incentive + 1.0).contains(duty1)).isEqualTo(true);
    }

    @Test
    public void findOverIncentive() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName";
        double incentive = 0.0;

        // when
        Duty duty1 = Duty.builder()
                .name(name + "1")
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty1);

        Duty duty2 = Duty.builder()
                .name(name + "2")
                .incentive(incentive + 2.0)
                .members(new HashSet<Member>())
                .build();
        Long saved2Id = dutyRepository.save(duty2);

        // then
        assertThat(dutyRepository.findOverIncentive(incentive + 1.0).contains(duty1)).isEqualTo(true);
    }

    @Test
    public void changeId() throws Exception {
        // given
        int size = dutyRepository.findAll().size();
        int count = 2;

        String name = "dutyName";
        double incentive = 0.0;

        Long changeNumber = 123123L;

        // when
        Duty duty = Duty.builder()
                .name(name + "1")
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty);

        dutyRepository.changeId(duty.getId(), changeNumber);

        // then
        assertThat(duty.getId()).isEqualTo(changeNumber);

        // id를 변경하면 기존의 것은 깨지고 새로운 영속성이 만들어지는가?
//        assertThat(duty).isEqualTo(dutyRepository.findById(changeNumber));
    }

    @Test
    public void changeName() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 0.0;

        // when
        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty);

        dutyRepository.changeName(duty.getId(), name + name);

        // then
        assertThat(duty.getName()).isEqualTo(name + name);
        assertThat(dutyRepository.findByName(name + name).contains(duty)).isEqualTo(true);
    }

    @Test
    public void changeMembers() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 0.0;

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);

        Set<Member> members = new HashSet<>();
        members.add(member);

        // when
        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty);

        dutyRepository.changeMembers(duty.getId(), members);

        // then
        assertThat(duty.getMembers()).isEqualTo(members);
        assertThat(duty.getMembers().containsAll(members)).isEqualTo(true);
    }

    @Test
    public void addMember() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 0.0;

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);

        // when
        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty);

        dutyRepository.addMember(duty.getId(), member);

        // then
        assertThat(duty.getMembers().contains(member)).isEqualTo(true);
    }

    @Test
    public void removeMember() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 0.0;

        Member member = Member.builder()
                .loginId("loginId")
                .password(passwordEncoder.encode("123"))
                .name("name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.UNDEFINED)
                .build();
        em.persist(member);

        // when
        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty);

        dutyRepository.addMember(duty.getId(), member);
        assertThat(duty.getMembers().contains(member)).isEqualTo(true);

        // then
        dutyRepository.removeMember(duty.getId(), member);
        assertThat(duty.getMembers().contains(member)).isEqualTo(false);
    }

    @Test
    public void changeIncentive() throws Exception {
        // given
        String name = "dutyName";
        double incentive = 0.0;

        // when
        Duty duty = Duty.builder()
                .name(name)
                .incentive(incentive + 1.0)
                .members(new HashSet<Member>())
                .build();
        Long saved1Id = dutyRepository.save(duty);

        assertThat(duty.getIncentive()).isEqualTo(incentive + 1.0);

        // then
        dutyRepository.changeIncentive(duty.getId(), incentive + 11.0);
        assertThat(duty.getIncentive()).isEqualTo(incentive + 11.0);
    }
}