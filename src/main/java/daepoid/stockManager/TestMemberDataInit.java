package daepoid.stockManager;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestMemberDataInit {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Member member1 = Member.createMember("ceo", "name1", "123", "123");
        member1.changeGradeType(GradeType.CEO);
        member1.changeMemberStatus(MemberStatus.WORK);

        Member member2 = Member.createMember("cook", "name2", "123", "123");
        member2.changeGradeType(GradeType.COOK);
        member2.changeMemberStatus(MemberStatus.WORK);

        Member member3 = Member.createMember("manager", "name2", "123", "123");
        member3.changeGradeType(GradeType.MANAGER);
        member3.changeMemberStatus(MemberStatus.WORK);

        memberService.join(member1);
        memberService.join(member2);
        memberService.join(member3);
    }
}
