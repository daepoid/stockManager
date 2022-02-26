package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.search.DutySearch;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.DutyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryDutyRepository implements DutyRepository {

    private static final Map<Long, Duty> store = new HashMap<>();
    private static Long sequence = 0L;

    //==생성 로직==//
    @Override
    public Long save(Duty duty) {
        duty.changeDutyId(++sequence);
        store.put(sequence, duty);
        return duty.getId();
    }

    //==조회 로직==//
    @Override
    public Duty findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Duty> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public List<Duty> findAll(int maxResult) {
        return null;
    }

    @Override
    public List<Duty> findAll(int firstResult, int maxResult) {
        return null;
    }

    @Override
    public List<Duty> findByName(String name) {
        return store.values().stream()
                .filter(duty -> duty.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findByMember(Member member) {
        return store.values().stream()
                .filter(duty -> duty.getMembers().contains(member))
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findByIncentive(double incentive) {
        return store.values().stream()
                .filter(duty -> duty.getIncentive() == incentive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findUnderIncentive(double incentive) {
        return store.values().stream()
                .filter(duty -> duty.getIncentive() <= incentive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findOverIncentive(double incentive) {
        return store.values().stream()
                .filter(duty -> duty.getIncentive() >= incentive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findByDutySearch(DutySearch dutySearch) {
        return null;
    }

    //==수정 로직==//

    @Override
    public void changeName(Long dutyId, String name) {
        store.get(dutyId).changeDutyName(name);
    }

    @Override
    public void changeMembers(Long dutyId, Set<Member> members) {
        store.get(dutyId).changeDutyMembers(members);
    }

    @Override
    public void addMember(Long dutyId, Member member) {
        store.get(dutyId).getMembers().add(member);
    }

    @Override
    public void removeMember(Long dutyId, Member member) {
        store.get(dutyId).getMembers().remove(member);
    }

    @Override
    public void changeIncentive(Long dutyId, double incentive) {
        store.get(dutyId).changeDutyIncentive(incentive);
    }

    @Override
    public void removeDuty(Long dutyId) {
        store.remove(dutyId);
    }
}
