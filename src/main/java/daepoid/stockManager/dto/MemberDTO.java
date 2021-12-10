//package daepoid.stockManager.dto;
//
//import daepoid.stockManager.domain.member.GradeType;
//import daepoid.stockManager.domain.member.Member;
//import daepoid.stockManager.domain.member.RoleType;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//public class MemberDTO {
//
//    private Long id;
//    private String name;
//    private String phoneNumber;
//    private GradeType gradeType;
//    private List<RoleType> authorities = new ArrayList<>();
//
//
//    public Member createMember() {
//        Member member = new Member();
//        member.setId(this.id);
//        member.setName(this.name);
//        member.setPhoneNumber(this.phoneNumber);
//        member.setGradeType(this.gradeType);
//        member.setRoles(this.authorities);
//
//        return member;
//    }
//}
