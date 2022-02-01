package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import daepoid.stockManager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    //==생성 로직==//
    @Override
    public Long save(Member member) {
        em.persist(member);
        log.info("save member = {}", member);
        return member.getId();
    }

    //==조회 로직==//
    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public Member findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
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
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
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
    public List<Member> findByRoles(RoleType... roleType) {
        return null;
    }

    //==수정 로직==//
    @Override
    public void changeName(Long memberId, String name) {
        Member member = em.find(Member.class, memberId);
        member.changeName(name);
    }

    @Override
    public void changePassword(Long memberId, String password) {
        Member member = em.find(Member.class, memberId);
        member.changePassword(password);
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

    //==삭제 로직==//
    @Override
    @Transactional
    public void removeMember(Member member) {
        em.remove(member);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }
}
