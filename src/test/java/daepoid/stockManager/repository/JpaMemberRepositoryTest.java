//package daepoid.stockManager.repository;
//
//import daepoid.stockManager.domain.member.GradeType;
//import daepoid.stockManager.domain.member.Member;
//import daepoid.stockManager.domain.member.MemberStatus;
//import daepoid.stockManager.domain.member.RoleType;
//import daepoid.stockManager.repository.jpa.JpaMemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@SpringBootTest
//@Transactional
//class JpaMemberRepositoryTest {
//
//    @Autowired
//    private JpaMemberRepository memberRepository;
//
//    @Test
//    public void saveMemberTest_findMember_성공() throws Exception {
//        // given
//        String name = "user";
//        String password = "1234";
//        String phoneNumber = "01012341234";
//        GradeType gradeType = GradeType.CEO;
//        RoleType roleType = RoleType.TEST;
//        MemberStatus memberStatus = MemberStatus.WORK;
//
//        Member member = Member.createMember(name, password, phoneNumber);
//        memberRepository.save(member);
//        Member fakeMember = Member.createMember("", "", "");
//
//        // when
//        Member findMember = memberRepository.findById(member.getId()).orElse(fakeMember);
//
//        // then
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
//
//    @Test
//    public void findAll() throws Exception {
//        // given
//        String name = "user";
//        String password = "1234";
//        String phoneNumber1 = "01012341234";
//        String phoneNumber2 = "01012345678";
//        GradeType gradeType = GradeType.CEO;
//        RoleType roleType = RoleType.TEST;
//        MemberStatus memberStatus = MemberStatus.WORK;
//
//        Member member1 = Member.createMember(name, password, phoneNumber1, gradeType, memberStatus, roleType);
//        Member member2 = Member.createMember(name, password, phoneNumber2, gradeType, memberStatus, roleType);
//        memberRepository.save(member1);
//        memberRepository.save(member2);
//
//        // when
//        List<Member> members = memberRepository.findAll();
//
//        // then
//        Assertions.assertThat(members.size()).isEqualTo(2);
//    }
//
//    @Test
//    public void findByName_성공() throws Exception {
//        // given
//        String name = "user";
//        String password = "1234";
//        String phoneNumber = "01012341234";
//        GradeType gradeType = GradeType.CEO;
//        RoleType roleType = RoleType.TEST;
//        MemberStatus memberStatus = MemberStatus.WORK;
//
//        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
//        memberRepository.save(member);
//        // when
//        List<Member> findMembers = memberRepository.findByName(name);
//
//        // then
//        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
//    }
//
//    @Test
//    public void findByPhoneNumber_성공() throws Exception {
//        // given
//        String name = "user";
//        String password = "1234";
//        String phoneNumber = "01012341234";
//        GradeType gradeType = GradeType.CEO;
//        RoleType roleType = RoleType.TEST;
//        MemberStatus memberStatus = MemberStatus.WORK;
//
//        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
//        memberRepository.save(member);
//
//        // when
//        Member findMember = memberRepository.findByPhoneNumber(phoneNumber);
//
//        // then
//        Assertions.assertThat(findMember).isEqualTo(member);
//    }
//
//    @Test
//    public void findByGradeType_성공() throws Exception {
//        // given
//        String name = "user";
//        String password = "1234";
//        String phoneNumber = "01012341234";
//        GradeType gradeType = GradeType.CEO;
//        RoleType roleType = RoleType.TEST;
//        MemberStatus memberStatus = MemberStatus.WORK;
//
//        Member member = Member.createMember(name, password, phoneNumber, gradeType, memberStatus, roleType);
//        memberRepository.save(member);
//
//        // when
//        List<Member> findMembers = memberRepository.findByGradeType(gradeType);
//
//        // then
//        Assertions.assertThat(findMembers.contains(member)).isEqualTo(true);
//    }
//}