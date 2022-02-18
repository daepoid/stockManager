package daepoid.stockManager.repository;

import daepoid.stockManager.domain.StoreUser;
import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.*;
import daepoid.stockManager.domain.order.Customer;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //==생성 로직==//
    Long save(Member user);

    //==조회 로직==//
    Member findById(Long id);
    List<Member> findAll();
    Member findByLoginId(String loginId);
    List<Member> findByUserName(String userName);

    Member findByPhoneNumber(String phoneNumber);
    List<Member> findByGradeType(GradeType gradeType);
    List<Member> findByMemberStatus(MemberStatus memberStatus);
    List<Member> findByDuty(Duty duty);
    List<Member> findByRoles(RoleType... roleType);
    List<Member> findByMemberSearch(MemberSearch memberSearch);

    //==수정 로직==//
    void changePassword(Long userId, String password);
    void changeUserName(Long userId, String userName);
    void changePhoneNumber(Long memberId, String phoneNumber);
    void changeGradeType(Long memberId, GradeType gradeType);
    void changeMemberStatus(Long memberId, MemberStatus memberStatus);
    void changeDuties(Long memberId, List<Duty> duties);
    void addDuty(Long memberId, Duty... duties);
    void removeDuty(Long memberId, Duty... duties);

    //==삭제 로직==//
    void removeMember(Long userId);

}
