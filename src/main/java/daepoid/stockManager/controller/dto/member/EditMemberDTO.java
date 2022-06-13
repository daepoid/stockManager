package daepoid.stockManager.controller.dto.member;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.users.Member;
import daepoid.stockManager.domain.member.MemberStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMemberDTO {

    @NotNull
    private Long id;

    // 로그인 아이디
    @NotBlank
    private String loginId;

    // 이름
    @NotBlank
    private String userName;

    // 전화번호
    @NotBlank
    private String phoneNumber;

    // 직급
    @NotNull
    @Enumerated(EnumType.STRING)
    private GradeType gradeType = GradeType.UNDEFINED;

    // 직원 상태 {재직, 휴직, 퇴직}
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.WORK;

    @ElementCollection(targetClass = MemberRole.class)
    @Enumerated(EnumType.STRING)
    private Collection<MemberRole> roles = new ArrayList<>();

    @NotNull
    private List<Duty> duties = new ArrayList<>();

    public EditMemberDTO(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.userName = member.getUserName();
        this.phoneNumber = member.getPhoneNumber();

        this.gradeType = member.getGradeType();
        this.memberStatus = member.getMemberStatus();
        this.roles = member.getRoles();
        this.duties = member.getDuties();
    }
}
