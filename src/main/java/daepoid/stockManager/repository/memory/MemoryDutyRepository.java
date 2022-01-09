package daepoid.stockManager.repository.memory;

import daepoid.stockManager.domain.duty.Duty;
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
    public void save(Duty duty) {
        duty.changeDutyId(++sequence);
        store.put(sequence, duty);
    }

    //==조회 로직==//
    @Override
    public Optional<Duty> findById(Long id) {
        return Optional.of(store.get(id));
    }

    @Override
    public List<Duty> findAll() {
        return new ArrayList<>(store.values());
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
    public List<Duty> findIncentive(Double incentive) {
        return store.values().stream()
                .filter(duty -> duty.getIncentive().equals(incentive))
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findUnderIncentive(Double incentive) {
        return store.values().stream()
                .filter(duty -> duty.getIncentive() <= incentive)
                .collect(Collectors.toList());
    }

    @Override
    public List<Duty> findOverIncentive(Double incentive) {
        return store.values().stream()
                .filter(duty -> duty.getIncentive() >= incentive)
                .collect(Collectors.toList());
    }

    //==수정 로직==//
    @Override
    public void changeId(Long dutyId, Long id) {
        store.get(dutyId).changeDutyId(id);
    }

    @Override
    public void changeName(Long dutyId, String name) {
        store.get(dutyId).changeDutyName(name);
    }

    @Override
    public void changeMembers(Long dutyId, List<Member> members) {
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
    public void changeIncentive(Long dutyId, Double incentive) {
        store.get(dutyId).changeDutyIncentive(incentive);
    }
}
