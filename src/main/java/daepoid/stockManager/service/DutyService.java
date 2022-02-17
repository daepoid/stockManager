package daepoid.stockManager.service;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.jpa.JpaDutyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DutyService {

    private final JpaDutyRepository dutyRepository;

    //==생성 로직==//
    @Transactional
    public Long saveDuty(Duty duty) {
        return dutyRepository.save(duty);
    }

    //==조회 로직==//
    public Duty findDuty(Long id) {
        return dutyRepository.findById(id);
    }

    public List<Duty> findDuties() {
        return dutyRepository.findAll();
    }

    public List<Duty> findByName(String name) {
        return dutyRepository.findByName(name);
    }

    public List<Duty> findByMember(Member member) {
        return dutyRepository.findByMember(member);
    }

    public List<Duty> findByIncentive(double incentive) {
        return dutyRepository.findByIncentive(incentive);
    }

    public List<Duty> findUnderIncentive(double incentive) {
        return dutyRepository.findUnderIncentive(incentive);
    }

    public List<Duty> findOverIncentive(double incentive) {
        return dutyRepository.findOverIncentive(incentive);
    }

    //==수정 로직==//

    @Transactional
    public void changeName(Long dutyId, String name) {
        Duty duty = dutyRepository.findById(dutyId);
        duty.changeDutyName(name);
    }

    @Transactional
    public void changeMembers(Long dutyId, Set<Member> members) {
        Duty duty = dutyRepository.findById(dutyId);
        duty.changeDutyMembers(members);
    }

    @Transactional
    public void addMember(Long dutyId, Member member) {
        Duty duty = dutyRepository.findById(dutyId);
        duty.getMembers().add(member);
    }

    @Transactional
    public void removeMember(Long dutyId, Member member) {
        Duty duty = dutyRepository.findById(dutyId);
        duty.removeDutyMember(member);
    }

    @Transactional
    public void changeIncentive(Long dutyId, double incentive) {
        Duty duty = dutyRepository.findById(dutyId);
        duty.changeDutyIncentive(incentive);
    }

    //==삭제 로직==//

    @Transactional
    public void removeDuty(Long dutyId) {
        dutyRepository.removeDuty(dutyId);
    }

}
