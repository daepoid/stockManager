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
        Member member1 = Member.builder()
                .loginId("ceo")
                .password(passwordEncoder.encode("123"))
                .name("name_ceo")
                .gradeType(GradeType.CEO)
                .build();
        memberService.join(member1);

        Member member2 = Member.builder()
                .loginId("manager")
                .password(passwordEncoder.encode("123"))
                .name("name_manager")
                .gradeType(GradeType.MANAGER)
                .build();
        memberService.join(member2);

        Member member3 = Member.builder()
                .loginId("cook")
                .password(passwordEncoder.encode("123"))
                .name("name_cook")
                .gradeType(GradeType.COOK)
                .build();
        memberService.join(member3);
    }
}
