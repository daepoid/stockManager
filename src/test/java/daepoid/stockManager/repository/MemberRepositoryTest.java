package daepoid.stockManager.repository;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.RoleType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void saveMemberTest_findMember_성공() throws Exception {
        // given
        String name = "user";
        String password = "1234";
        String phoneNumber = "01012341234";
        GradeType gradeType = GradeType.CEO;
        RoleType roleType = RoleType.TEST;

        Member member = Member.createMember(name, password, phoneNumber, gradeType, roleType);
        memberRepository.saveMember(member);

        // when
        Member findMember = memberRepository.findMember(member.getId());

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

        Member member1 = Member.createMember(name, password, phoneNumber1, gradeType, roleType);
        Member member2 = Member.createMember(name, password, phoneNumber2, gradeType, roleType);
        memberRepository.saveMember(member1);
        memberRepository.saveMember(member2);

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

        Member member = Member.createMember(name, password, phoneNumber, gradeType, roleType);
        memberRepository.saveMember(member);
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

        Member member = Member.createMember(name, password, phoneNumber, gradeType, roleType);
        memberRepository.saveMember(member);

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

        Member member = Member.createMember(name, password, phoneNumber, gradeType, roleType);
        memberRepository.saveMember(member);

        // when
        List<Member> findMembers = memberRepository.findByGradeType(gradeType);

        // then
        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
    }
}