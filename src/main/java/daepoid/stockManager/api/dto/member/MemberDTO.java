package daepoid.stockManager.api.dto.member;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class MemberDTO {

    private final Long memberId;

    private final String loginId;

    // 이름
    private final String userName;

    // 전화번호 '01012341234' 형태로 저장됨
    private final String phoneNumber;

    // 직급
    @Enumerated(EnumType.STRING)
    private final GradeType gradeType;

    // 직원 상태 {재직, 휴직, 퇴직}
    @Enumerated(EnumType.STRING)
    private final MemberStatus memberStatus;

    public MemberDTO(Member member) {
        this.memberId = member.getId();
        this.loginId = member.getLoginId();
        this.userName = member.getUserName();
        this.phoneNumber = member.getPhoneNumber();
        this.gradeType = member.getGradeType();
        this.memberStatus = member.getMemberStatus();
    }
}
