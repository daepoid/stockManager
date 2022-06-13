package daepoid.stockManager.domain.users;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.MemberStatus;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends StoreUser {

    // 전화번호 '01012341234' 형태로 저장됨
    @NotBlank
    private String phoneNumber;

    // 직원 상태 {재직, 휴직, 퇴직}
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.UNDEFINED;

    //==연관 관계 메서드==//


    //==생성 메서드==//
    @Builder
    public Member(String loginId, String password, String userName, GradeType gradeType,
                  String phoneNumber, MemberStatus memberStatus) {
        this.changeLoginId(loginId);
        this.changePassword(password);
        this.changeUserName(userName);
        this.changeGradeType(gradeType);

        this.phoneNumber = phoneNumber;
        this.memberStatus = memberStatus;
    }

    //==개발 로직==//

    //==비즈니스 로직 (setter 제거)==//

    // 전화번호 변경
    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // 재직 상태 변경
    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }
}
