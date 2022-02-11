package daepoid.stockManager.repository;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //==생성 로직==//
    Long save(Member member);

    //==조회 로직==//
    Member findById(Long id);
    List<Member> findAll();
    Member findByLoginId(String loginId);
    List<Member> findByName(String name);
    Member findByPhoneNumber(String phoneNumber);
    List<Member> findByGradeType(GradeType gradeType);
    List<Member> findByMemberStatus(MemberStatus memberStatus);
    List<Member> findByDuty(Duty duty);
    List<Member> findByRoles(RoleType... roleType);

    //==수정 로직==//
    void changeName(Long memberId, String name);
    void changePassword(Long memberId, String password);
    void changePhoneNumber(Long memberId, String phoneNumber);
    void changeGradeType(Long memberId, GradeType gradeType);
    void changeMemberStatus(Long memberId, MemberStatus memberStatus);
    void changeDuties(Long memberId, List<Duty> duties);
    void addDuty(Long memberId, Duty... duties);
    void removeDuty(Long memberId, Duty... duties);

    //==삭제 로직==//
    void removeMember(Member member);
    void removeById(Long memberId);
}
