package daepoid.stockManager.repository.jpa;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.DutyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaDutyRepository implements DutyRepository {

    private final EntityManager em;

    //==생성 로직==//
    @Override
    public Long save(Duty duty) {
        em.persist(duty);
        return duty.getId();
    }

    //==조회 로직==//
    @Override
    public Duty findById(Long id) {
        return em.find(Duty.class, id);
    }

    @Override
    public List<Duty> findAll() {
        return em.createQuery("select d from Duty d", Duty.class)
                .getResultList();
    }

    @Override
    public List<Duty> findByName(String name) {
        return em.createQuery("select d from Duty d where d.name = :name", Duty.class)
                .setParameter("name", name)
                .getResultList();
    }

    public Duty findByUniqueName(String name) {
        return em.createQuery("select d from Duty d where d.name = :name", Duty.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Duty> findByMember(Member member) {
        return em.createQuery("select d from Duty d", Duty.class)
                .getResultList().stream()
                .filter(duty -> duty.getMembers().contains(member))
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findByIncentive(double incentive) {
        return em.createQuery("select d from Duty d where d.incentive = :incentive", Duty.class)
                .setParameter("incentive", incentive)
                .getResultList();
    }

    @Override
    public List<Duty> findUnderIncentive(double incentive) {
        return em.createQuery("select d from Duty d where d.incentive <= :incentive", Duty.class)
                .setParameter("incentive", incentive)
                .getResultList();
    }

    @Override
    public List<Duty> findOverIncentive(double incentive) {
        return em.createQuery("select d from Duty d where d.incentive >= :incentive", Duty.class)
                .setParameter("incentive", incentive)
                .getResultList();
    }

    //==변경 로직==//
    @Override
    public void changeId(Long dutyId, Long id) {
        Duty duty = em.find(Duty.class, dutyId);
        duty.changeDutyId(id);
    }

    @Override
    public void changeName(Long dutyId, String name) {
        Duty duty = em.find(Duty.class, dutyId);
        duty.changeDutyName(name);
    }

    @Override
    public void changeMembers(Long dutyId, Set<Member> members) {
        Duty duty = em.find(Duty.class, dutyId);
        duty.changeDutyMembers(members);
    }

    @Override
    public void addMember(Long dutyId, Member member) {
        Duty duty = em.find(Duty.class, dutyId);
        duty.getMembers().add(member);
    }

    @Override
    public void removeMember(Long dutyId, Member member) {
        Duty duty = em.find(Duty.class, dutyId);
        duty.getMembers().remove(member);
    }

    @Override
    public void changeIncentive(Long dutyId, double incentive) {
        Duty duty = em.find(Duty.class, dutyId);
        duty.changeDutyIncentive(incentive);
    }
}
