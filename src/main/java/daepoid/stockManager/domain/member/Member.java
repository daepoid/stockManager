package daepoid.stockManager.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    // 로그인 아이디
    private String loginId;

    // 이름
    private String name;

    // 비민번호, 로그인 시 사용
    private String password;

    // 전화번호 '01012341234' 형태로 저장됨
    // 로그인시 아이디의 대용으로 사용 가능
    private String phoneNumber;

    // 직급
    @Enumerated(EnumType.STRING)
    private GradeType gradeType = GradeType.UNDEFINED;

    // 근무 시간


    // 근무 요일


    // 직원 상태 {재직, 휴직, 퇴직}
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus = MemberStatus.WORK;

    // 직급 이외에 특별한 권한을 부여, 특정 물품에 대한 주문 발주 등등...
    // https://www.inflearn.com/questions/21303
    @ElementCollection(targetClass = RoleType.class)
    @Enumerated(EnumType.STRING)
    private Collection<RoleType> roles = new ArrayList<>();



//    // 엔티티를 이용하는 방법으로 일단 개발
//    private List<MemberRole> memberRole = new ArrayList<>();

    //==연관 관계 메서드==//
    public void addRole(RoleType roleType) {
        this.roles.add(roleType);
    }


    //==생성 메서드==//
    public static Member createMember(String loginId, String name, String password, String phoneNumber) {
        Member member = new Member();
        member.changeLoginId(loginId);
        member.changeName(name);
        member.changePassword(password);
        member.changePhoneNumber(phoneNumber);
        return member;
    }

    public static Member createMember(String loginId, String name, String password, String phoneNumber, GradeType gradeType, MemberStatus memberStatus) {
        Member member = new Member();
        member.changeLoginId(loginId);
        member.changeName(name);
        member.changePassword(password);
        member.changePhoneNumber(phoneNumber);
        member.changeGradeType(gradeType);
        member.changeMemberStatus(memberStatus);

        return member;
    }

    public static Member createMember(String loginId, String name, String password, String phoneNumber, GradeType gradeType, MemberStatus memberStatus, RoleType... roleTypes) {
        Member member = new Member();
        member.changeLoginId(loginId);
        member.changeName(name);
        member.changePassword(password);
        member.changePhoneNumber(phoneNumber);
        member.changeGradeType(gradeType);

        for (RoleType roleType : roleTypes) {
            member.addRole(roleType);
        }

        member.changeMemberStatus(memberStatus);

        return member;
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
}