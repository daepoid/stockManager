package daepoid.stockManager.repository;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.search.DutySearch;
import daepoid.stockManager.domain.member.Member;

import java.util.List;
import java.util.Set;

public interface DutyRepository {

    //==생성 로직==//
    Long save(Duty duty);

    //==조회 로직==//
    Duty findById(Long id);
    List<Duty> findAll();
    List<Duty> findAll(int maxResult);
    List<Duty> findAll(int firstResult, int maxResult);
    List<Duty> findByName(String name);
    List<Duty> findByMember(Member member);

    List<Duty> findByIncentive(double incentive);
    List<Duty> findUnderIncentive(double incentive);
    List<Duty> findOverIncentive(double incentive);

    List<Duty> findByDutySearch(DutySearch dutySearch);

    //==수정 로직==//
    void changeName(Long dutyId, String name);
    void changeMembers(Long dutyId, Set<Member> members);
    void addMember(Long dutyId, Member member);
    void removeMember(Long dutyId, Member member);
    void changeIncentive(Long dutyId, double incentive);

    //==삭제 로직==//
    void removeDuty(Long dutyId);
}
