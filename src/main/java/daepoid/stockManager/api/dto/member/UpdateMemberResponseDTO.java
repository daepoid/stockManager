package daepoid.stockManager.api.dto.member;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
public class UpdateMemberResponseDTO {

    private Long memberId;

    private String loginId;

    // 이름
    private String userName;

    // 전화번호 '01012341234' 형태로 저장됨
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private GradeType gradeType;

    // 직원 상태 {재직, 휴직, 퇴직}
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    public UpdateMemberResponseDTO(Long memberId, String loginId, String name, String phoneNumber, GradeType gradeType, MemberStatus memberStatus) {
        this.memberId = memberId;
        this.loginId = loginId;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.gradeType = gradeType;
        this.memberStatus = memberStatus;
    }

    public UpdateMemberResponseDTO(Member member) {
        this.memberId = member.getId();
        this.loginId = member.getLoginId();
        this.userName = member.getUserName();
        this.phoneNumber = member.getPhoneNumber();
        this.gradeType = member.getGradeType();
        this.memberStatus = member.getMemberStatus();
    }
}
