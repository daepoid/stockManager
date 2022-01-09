package daepoid.stockManager.repository;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface DutyRepository {

    //==생성 로직==//
    void save(Duty duty);

    //==조회 로직==//
    Optional<Duty> findById(Long id);
    List<Duty> findAll();
    List<Duty> findByName(String name);
    List<Duty> findByMember(Member member);
    List<Duty> findIncentive(Double incentive);

    List<Duty> findUnderIncentive(Double incentive);
    List<Duty> findOverIncentive(Double incentive);

    //==수정 로직==//
    void changeId(Long dutyId, Long id);
    void changeName(Long dutyId, String name);
    void changeMembers(Long dutyId, List<Member> members);
    void addMember(Long dutyId, Member member);
    void removeMember(Long dutyId, Member member);
    void changeIncentive(Long dutyId, Double incentive);

    //==삭제 로직==//
}
