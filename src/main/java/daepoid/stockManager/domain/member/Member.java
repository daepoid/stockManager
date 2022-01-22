package daepoid.stockManager.domain.member;

import daepoid.stockManager.domain.duty.Duty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    // 로그인 아이디
    @NotBlank
    private String loginId;

    // 비민번호, 로그인 시 사용
    @NotBlank
    private String password;

    // 이름
    @NotBlank
    private String name;

    // 전화번호 '01012341234' 형태로 저장됨
    @NotBlank
    private String phoneNumber;

    // 직급
    @NotNull
    @Enumerated(EnumType.STRING)
    private GradeType gradeType = GradeType.UNDEFINED;

    // 직원 상태 {재직, 휴직, 퇴직}
    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.UNDEFINED;

    @ManyToMany(mappedBy="members", cascade = CascadeType.ALL)
    private List<Duty> duties = new ArrayList<>();


    // 직급 이외에 특별한 권한을 부여, 특정 물품에 대한 주문 발주 등등...
    // https://www.inflearn.com/questions/21303
    @ElementCollection(targetClass = RoleType.class)
    @Enumerated(EnumType.STRING)
    private Collection<RoleType> roles = new ArrayList<>();

    //==연관 관계 메서드==//
    public void addRole(RoleType roleType) {
        this.roles.add(roleType);
    }

    //==생성 메서드==//
    @Builder
    public Member(String loginId, String name, String password, String phoneNumber, GradeType gradeType, MemberStatus memberStatus, List<RoleType> roles, List<Duty> duties) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gradeType = gradeType;
        this.memberStatus = memberStatus;
        this.roles = roles;
        this.duties = duties;
    }

    //==개발 로직==//
    public void changeId(Long id) {
        this.id = id;
    }

    //==비즈니스 로직 (setter 제거)==//

    // 아이디 변경
    public void changeLoginId(String loginId) {
        this.loginId = loginId;
    }

    // 이름 변경
    public void changeName(String name) {
        this.name = name;
    }

    // 비밀번호 변경
    public void changePassword(String password) {
        this.password = password;
    }

    // 전화번호 변경
    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // 직급 변경
    public void changeGradeType(GradeType gradeType) {
        this.gradeType = gradeType;
    }

    // 재직 상태 변경
    public void changeMemberStatus(MemberStatus memberStatus) {
        this.memberStatus = memberStatus;
    }

    // 직무 변경
    public void changeDuties(List<Duty> duties) {
        this.duties = duties;
    }

    public void addDuty(Duty duty) {
        this.duties.add(duty);
    }

    public void removeDuty(Duty duty) {
        this.duties.remove(duty);
    }
}
