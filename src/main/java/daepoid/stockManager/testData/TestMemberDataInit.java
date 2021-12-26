package daepoid.stockManager.testData;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestMemberDataInit {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        Member member_CEO = Member.builder()
                .loginId("ceo")
                .password(passwordEncoder.encode("123"))
                .name("ceo_name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.CEO)
                .memberStatus(MemberStatus.WORK)
                .build();
        memberService.join(member_CEO);

        Member memberMANAGER = Member.builder()
                .loginId("manager")
                .password(passwordEncoder.encode("123"))
                .name("manager_name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.MANAGER)
                .memberStatus(MemberStatus.WORK)
                .build();
        memberService.join(memberMANAGER);

        Member member_CHEF = Member.builder()
                .loginId("chef")
                .password(passwordEncoder.encode("123"))
                .name("chef_name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.CHEF)
                .memberStatus(MemberStatus.WORK)
                .build();
        memberService.join(member_CHEF);

        Member member_COOK = Member.builder()
                .loginId("cook")
                .password(passwordEncoder.encode("123"))
                .name("cook_name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.COOK)
                .memberStatus(MemberStatus.WORK)
                .build();
        memberService.join(member_COOK);

        Member member_PART_TIME = Member.builder()
                .loginId("part_time")
                .password(passwordEncoder.encode("123"))
                .name("part_time_name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.PART_TIME)
                .memberStatus(MemberStatus.WORK)
                .build();
        memberService.join(member_PART_TIME);

        Member member_UNDEFINED = Member.builder()
                .loginId("undefined")
                .password(passwordEncoder.encode("123"))
                .name("undefined_name")
                .phoneNumber("01012341234")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.WORK)
                .build();
        memberService.join(member_UNDEFINED);
    }
}
