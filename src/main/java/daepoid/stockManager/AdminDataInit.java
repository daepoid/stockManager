package daepoid.stockManager;

import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class AdminDataInit {

    private final MemberService memberService;

    @PostConstruct
    public void init() {
        Member member = Member.createMember("admin", "name", "123", "123");
        memberService.join(member);
    }
}
