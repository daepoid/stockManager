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
        memberService.join(Member.builder()
                .loginId("ceo")
                .password(passwordEncoder.encode("123"))
                .userName("ceo_name")
                .phoneNumber("01011111111")
                .gradeType(GradeType.CEO)
                .memberStatus(MemberStatus.WORK)
                .build());

        memberService.join(Member.builder()
                .loginId("manager")
                .password(passwordEncoder.encode("123"))
                .userName("manager_name")
                .phoneNumber("01022222222")
                .gradeType(GradeType.MANAGER)
                .memberStatus(MemberStatus.WORK)
                .build());

        memberService.join(Member.builder()
                .loginId("chef")
                .password(passwordEncoder.encode("123"))
                .userName("chef_name")
                .phoneNumber("01033333333")
                .gradeType(GradeType.CHEF)
                .memberStatus(MemberStatus.WORK)
                .build());

        memberService.join(Member.builder()
                .loginId("cook")
                .password(passwordEncoder.encode("123"))
                .userName("cook_name")
                .phoneNumber("01044444444")
                .gradeType(GradeType.COOK)
                .memberStatus(MemberStatus.WORK)
                .build());

        memberService.join(Member.builder()
                .loginId("part_time")
                .password(passwordEncoder.encode("123"))
                .userName("part_time_name")
                .phoneNumber("01055555555")
                .gradeType(GradeType.PART_TIME)
                .memberStatus(MemberStatus.WORK)
                .build());

        memberService.join(Member.builder()
                .loginId("undefined")
                .password(passwordEncoder.encode("123"))
                .userName("undefined_name")
                .phoneNumber("01066666666")
                .gradeType(GradeType.UNDEFINED)
                .memberStatus(MemberStatus.WORK)
                .build());
    }
}
