package daepoid.stockManager.domain.member;

import daepoid.stockManager.domain.StoreUser;
import daepoid.stockManager.domain.duty.Duty;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Getter
@DiscriminatorValue("MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends StoreUser {

    // 전화번호 '01012341234' 형태로 저장됨
    @NotBlank
    private String phoneNumber;

    // 직원 상태 {재직, 휴직, 퇴직}
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.UNDEFINED;

    // 직무 형태 삭제 예정, @Embeddable로 변경 예정
    @ManyToMany(mappedBy="members", cascade = CascadeType.ALL)
    private List<Duty> duties = new ArrayList<>();


    // 직급 이외에 특별한 권한을 부여, 특정 물품에 대한 주문 발주 등등...
    @ElementCollection(targetClass = RoleType.class)
    @Enumerated(EnumType.STRING)
    private Collection<RoleType> roles = new ArrayList<>();

    //==연관 관계 메서드==//
    public void addRole(RoleType roleType) {
        this.roles.add(roleType);
    }

    //==생성 메서드==//
    @Builder
    public Member(String loginId, String password, String userName, GradeType gradeType,
                  String phoneNumber, MemberStatus memberStatus, List<RoleType> roles, List<Duty> duties) {
        this.changeLoginId(loginId);
        this.changePassword(password);
        this.changeUserName(userName);
        this.changeGradeType(gradeType);

        this.phoneNumber = phoneNumber;
        this.memberStatus = memberStatus;
        this.roles = roles;
        this.duties = duties;
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

    // 직무 변경
    public void changeDuties(List<Duty> duties) {
        this.duties = duties;
    }

    public void addDuty(Duty... duties) {
        this.duties.addAll(Arrays.asList(duties));
    }

    public void removeDuty(Duty... duties) {
        this.duties.removeAll(Arrays.asList(duties));
    }
}
