package daepoid.stockManager.dto;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditMemberDTO {

    private Long id;

    // 로그인 아이디
    private String loginId;

    // 이름
    private String name;

    // 비민번호, 로그인 시 사용
    private String password;

    // 회원정보 변경 시에 사용
    // 관리자는 임의로 비밀번호를 바꿀 수 있음
    private String passwordCheck;

    // 전화번호 '01012341234' 형태로 저장됨
    private String phoneNumber;

    // 직급
    @Enumerated(EnumType.STRING)
    private GradeType gradeType = GradeType.UNDEFINED;

    // 직원 상태 {재직, 휴직, 퇴직}
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.WORK;

    @ElementCollection(targetClass = RoleType.class)
    @Enumerated(EnumType.STRING)
    private Collection<RoleType> roles = new ArrayList<>();

    public EditMemberDTO(Member member) {
        this.id = member.getId();
        this.loginId = member.getLoginId();
        this.name = member.getName();
        this.password = member.getPassword();
        this.phoneNumber = member.getPhoneNumber();
        this.gradeType = member.getGradeType();
        this.memberStatus = member.getMemberStatus();
        this.roles = member.getRoles();
    }
}
