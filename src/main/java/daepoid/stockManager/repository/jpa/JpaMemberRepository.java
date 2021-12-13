package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import daepoid.stockManager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

    @Override
    public Long save(Member member) {
        em.persist(member);
        log.info("save member = {}", member);
        return member.getId();
    }

    /**
     * member를 받아서 삭제하는 것이 맞는지 아니면 Id를 받아서 삭제하는 것이 맞는지 확인 필요
     * @param member
     */
    @Override
    public void removeMember(Member member) {
        em.remove(member);
    }

    @Override
    public void removeById(Long id) {
        Member member = em.find(Member.class, id);
        em.remove(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.of(em.find(Member.class, id));
    }

    @Override
    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
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
    public List<Member> findByPhoneNumber(String phoneNumber) {
        return em.createQuery("select m from Member m where m.phoneNumber = :phoneNumber", Member.class)
                .setParameter("phoneNumber", phoneNumber)
                .getResultList();
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
}
