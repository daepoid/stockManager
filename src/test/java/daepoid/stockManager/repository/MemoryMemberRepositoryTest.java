package daepoid.stockManager.repository;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import daepoid.stockManager.repository.memory.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    public void saveMemberTest_findMember_성공() throws Exception {
        // given
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberRepository.save(member);
        Member fakeMember = Member.createMember("", "", "", GradeType.PART_TIME, MemberStatus.RETIRE);

        // when
        Member findMember = memberRepository.findById(member.getId()).orElse(fakeMember);

        // then
        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void findAll() throws Exception {
        // given
        String name = "user";
        String password = "1234";
        String phoneNumber1 = "01012341234";
        String phoneNumber2 = "01012345678";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member1 = Member.createMember(name, password, phoneNumber1, gradeType, memberStatus, roleType);
        Member member2 = Member.createMember(name, password, phoneNumber2, gradeType, memberStatus, roleType);
        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> members = memberRepository.findAll();

        // then
        Assertions.assertThat(members.size()).isEqualTo(2);
    }

    @Test
    public void findByName_성공() throws Exception {
        // given
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberRepository.save(member);
        // when
        List<Member> findMembers = memberRepository.findByName(name);

        // then
        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
    }

    @Test
    public void findByPhoneNumber_성공() throws Exception {
        // given
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberRepository.save(member);

        // when
        List<Member> findMembers = memberRepository.findByPhoneNumber(phoneNumber);

        // then
        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
    }

    @Test
    public void findByGradeType_성공() throws Exception {
        // given
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST;
        MemberStatus memberStatus = MemberStatus.WORK;

        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
        memberRepository.save(member);

        // when
        List<Member> findMembers = memberRepository.findByGradeType(gradeType);

        // then
        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
    }
}