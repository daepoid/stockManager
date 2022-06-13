package daepoid.stockManager.repository;

import daepoid.stockManager.domain.member.*;
import daepoid.stockManager.domain.search.MemberSearch;
import daepoid.stockManager.domain.users.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    //==생성 로직==//
    Long save(Member user);

    //==조회 로직==//
    Optional<Member> findById(Long id);
    List<Member> findAll();
    List<Member> findAll(int maxResult);
    List<Member> findAll(int firstResult, int maxResult);
    Optional<Member> findByLoginId(String loginId);
    List<Member> findByUserName(String userName);
    List<Member> findByGradeType(GradeType gradeType);
    Optional<Member> findByPhoneNumber(String phoneNumber);
    List<Member> findByMemberStatus(MemberStatus memberStatus);
    List<Member> findByMemberSearch(MemberSearch memberSearch);

    //==수정 로직==//
    void changeLoginId(Long userId, String loginId);
    void changePassword(Long userId, String password);
    void changeUserName(Long userId, String userName);
    void changeGradeType(Long memberId, GradeType gradeType);
    void changePhoneNumber(Long memberId, String phoneNumber);
    void changeMemberStatus(Long memberId, MemberStatus memberStatus);

    //==삭제 로직==//
    void remove(Long userId);
}
