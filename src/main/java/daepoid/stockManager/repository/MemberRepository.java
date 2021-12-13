package daepoid.stockManager.repository;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Long save(Member member);
    void removeMember(Member member);
    void removeById(Long id);

    Optional<Member> findById(Long id);
    List<Member> findAll();
    List<Member> findByLoginId(String loginId);
    List<Member> findByName(String name);
    List<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findByGradeType(GradeType gradeType);
    List<Member> findByMemberStatus(MemberStatus memberStatus);
    List<Member> findByRoles(RoleType... roleType);
}
