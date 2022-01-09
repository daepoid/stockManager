package daepoid.stockManager.service;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.repository.jpa.JpaDutyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DutyService {

    private final JpaDutyRepository dutyRepository;

    //==생성 로직==//
    @Transactional
    public void saveDuty(Duty duty) {
        dutyRepository.save(duty);
    }

    //==조회 로직==//
    public Duty findDuty(Long id) {
        return dutyRepository.findById(id).get();
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

    public List<Duty> findIncentive(Double incentive) {
        return dutyRepository.findIncentive(incentive);
    }

    public List<Duty> findUnderIncentive(Double incentive) {
        return dutyRepository.findUnderIncentive(incentive);
    }

    public List<Duty> findOverIncentive(Double incentive) {
        return dutyRepository.findOverIncentive(incentive);
    }

    //==수정 로직==//
    @Transactional
    public void changeId(Long dutyId, Long id) {
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.changeDutyId(id);
    }

    @Transactional
    public void changeName(Long dutyId, String name) {
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.changeDutyName(name);
    }

    @Transactional
    public void changeMembers(Long dutyId, List<Member> members) {
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.changeDutyMembers(members);
    }

    @Transactional
    public void addMember(Long dutyId, Member member) {
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.getMembers().add(member);
    }

    @Transactional
    public void removeMember(Long dutyId, Member member) {
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.removeDutyMember(member);
    }

    @Transactional
    public void changeIncentive(Long dutyId, Double incentive) {
        Duty duty = dutyRepository.findById(dutyId).get();
        duty.changeDutyIncentive(incentive);
    }

    //==삭제 로직==//

}
