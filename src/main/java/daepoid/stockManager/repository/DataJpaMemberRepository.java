package daepoid.stockManager.repository;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DataJpaMemberRepository extends JpaRepository<Member, Long> {

    //==생성 로직==//
    Member save(Member member);

    //==조회 로직==//
    Optional<Member> findById(Long id);
    List<Member> findAll();
    Optional<Member> findByLoginId(String loginId);
    List<Member> findByName(String name);
    Member findByPhoneNumber(String phoneNumber);
    List<Member> findByGradeType(GradeType gradeType);
    List<Member> findByMemberStatus(MemberStatus memberStatus);

//    List<Member> findByRoles(List<RoleType> roleType);

    //==삭제 로직==//
    void delete(Member member);
    void deleteById(Long id);
}
