package daepoid.stockManager.testData;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Member;
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
        memberService.join(
                Member.builder()
                        .loginId("ceo")
                        .password(passwordEncoder.encode("123"))
                        .userName("ceo_name")
                        .gradeType(GradeType.CEO)
                        .phoneNumber("01011111111")
                        .memberStatus(MemberStatus.WORK)
                        .build()
        );

        memberService.join(
                Member.builder()
                        .loginId("manager")
                        .password(passwordEncoder.encode("123"))
                        .userName("manager_name")
                        .gradeType(GradeType.MANAGER)
                        .phoneNumber("01022222222")
                        .memberStatus(MemberStatus.WORK)
                        .build()
        );

        memberService.join(
                Member.builder()
                        .loginId("chef")
                        .password(passwordEncoder.encode("123"))
                        .userName("chef_name")
                        .gradeType(GradeType.CHEF)
                        .phoneNumber("01033333333")
                        .memberStatus(MemberStatus.WORK)
                        .build()
        );

        memberService.join(
                Member.builder()
                        .loginId("cook")
                        .password(passwordEncoder.encode("123"))
                        .userName("cook_name")
                        .gradeType(GradeType.COOK)
                        .phoneNumber("01044444444")
                        .memberStatus(MemberStatus.WORK)
                        .build()
        );

        memberService.join(
                Member.builder()
                        .loginId("part_time")
                        .password(passwordEncoder.encode("123"))
                        .userName("part_time_name")
                        .gradeType(GradeType.PART_TIME)
                        .phoneNumber("01055555555")
                        .memberStatus(MemberStatus.WORK)
                        .build()
        );

        memberService.join(
                Member.builder()
                        .loginId("undefined")
                        .password(passwordEncoder.encode("123"))
                        .userName("undefined_name")
                        .gradeType(GradeType.UNDEFINED)
                        .phoneNumber("01066666666")
                        .memberStatus(MemberStatus.WORK)
                        .build()
        );
    }
}
