package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.StoreUser;
import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.member.*;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Array;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    //==생성 로직==//
    @Override
    public Long save(Member user) {
        em.persist(user);
        return user.getId();
    }

    //==조회 로직==//
    @Override
    public Member findById(Long memberId) {
        return em.createQuery("select m from Member m where m.id=:memberId", Member.class)
                .setParameter("memberId", memberId)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    @Override
    public Member findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId=:loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList().stream()
                .findFirst().orElse(null);
    }

    @Override
    public List<Member> findByUserName(String userName) {
        return em.createQuery("select m from Member m where m.userName=:userName", Member.class)
                .setParameter("userName", userName)
                .getResultList();
    }

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        return em.createQuery("select m from Member m where m.phoneNumber = :phoneNumber", Member.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList()
                .stream().findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> findByGradeType(GradeType gradeType) {
        return em.createQuery("select m from Member m where m.gradeType = :gradeType", Member.class)
                .setParameter("gradeType", gradeType)
                .getResultList();
    }

    @Override
    public List<Member> findByMemberStatus(MemberStatus memberStatus) {
        return em.createQuery("select m from Member m where m.memberStatus = :memberStatus", Member.class)
                .setParameter("memberStatus", memberStatus)
                .getResultList();
    }

    @Override
    public List<Member> findByDuty(Duty duty) {
        return em.createQuery("select m from Member m", Member.class).getResultList()
                .stream().filter(m -> m.getDuties().stream().anyMatch(d -> d.getId().equals(duty.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> findByRoles(RoleType... roleType) {
        return null;
    }

    @Override
    public List<Member> findByMemberSearch(MemberSearch memberSearch) {
        // language=JPAQL
        String jpql = "select m From Member m";

        boolean isFirstCondition = true;
        // 직원 등급 검색
        if (memberSearch.getGradeType() != null) {
            jpql += " where m.gradeType = :gradeType";
            isFirstCondition = false;
        }

        // 직원 근무 상태 검색
        if (memberSearch.getMemberStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.memberStatus = :memberStatus";
        }

        // 직원 이름 검색
        if (StringUtils.hasText(memberSearch.getName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.userName like :name";
        }
        TypedQuery<Member> query = em.createQuery(jpql, Member.class).setMaxResults(1000); //최대 1000건

        if (memberSearch.getGradeType() != null) {
            query = query.setParameter("gradeType", memberSearch.getGradeType());
        }
        if (memberSearch.getMemberStatus() != null) {
            query = query.setParameter("memberStatus", memberSearch.getMemberStatus());
        }
        if (StringUtils.hasText(memberSearch.getName())) {
            query = query.setParameter("name", "%" + memberSearch.getName() + "%");
        }
        return query.getResultList();
    }

    //==수정 로직==//

    @Override
    public void changePassword(Long userId, String password) {
        em.find(StoreUser.class, userId).changePassword(password);
    }

    @Override
    public void changeUserName(Long userId, String userName) {
        em.find(StoreUser.class, userId).changeUserName(userName);
    }

    @Override
    public void changePhoneNumber(Long memberId, String phoneNumber) {
        Member member = em.find(Member.class, memberId);
        member.changePhoneNumber(phoneNumber);
    }

    @Override
    public void changeGradeType(Long memberId, GradeType gradeType) {
        Member member = em.find(Member.class, memberId);
        member.changeGradeType(gradeType);
    }

    @Override
    public void changeMemberStatus(Long memberId, MemberStatus memberStatus) {
        Member member = em.find(Member.class, memberId);
        member.changeMemberStatus(memberStatus);
    }

    @Override
    public void changeDuties(Long memberId, List<Duty> duties) {
        Member member = em.find(Member.class, memberId);
        member.changeDuties(duties);
    }

    @Override
    public void addDuty(Long memberId, Duty... duties) {
        Member member = em.find(Member.class, memberId);
        member.addDuty(duties);
    }

    @Override
    public void removeDuty(Long memberId, Duty... duties) {
        Member member = em.find(Member.class, memberId);
        member.removeDuty(duties);
    }

    //==삭제 로직==//
    @Override
    public void removeMember(Long userId) {
        em.remove(em.find(Member.class, userId));
    }
}
