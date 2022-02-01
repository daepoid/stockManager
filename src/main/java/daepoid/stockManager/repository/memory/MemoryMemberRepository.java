package daepoid.stockManager.repository.memory;


import daepoid.stockManager.domain.member.GradeType;
import daepoid.stockManager.domain.member.Member;
import daepoid.stockManager.domain.member.MemberStatus;
import daepoid.stockManager.domain.member.RoleType;
import daepoid.stockManager.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    //==개발 로직==//
    public void clearStore() {
        store.clear();
    }

    //==생성 로직==//
    @Override
    public Long save(Member member) {
        member.changeId(++sequence);
        store.put(member.getId(), member);
        return member.getId();
    }

    //==조회 로직==//
    @Override
    public Member findById(Long id) {
        return store.get(id);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Member findByLoginId(String loginId) {
        return store.values()
                .stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Member> findByName(String name) {
        return store.values()
                .stream()
                .filter(member -> member.getName().equals(name))
                .collect(Collectors.toList());

//        return store.keySet()
//                .stream()
//                .filter(id -> store.get(id).getName().equals(name))
//                .map(id -> store.get(id))
//                .collect(Collectors.toList());
    }

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        return store.values()
                .stream()
                .filter(member -> member.getPhoneNumber().equals(phoneNumber))
                .findAny()
                .orElse(null);
    }

    // gradeType equals 확인
    @Override
    public List<Member> findByGradeType(GradeType gradeType) {
        return store.values()
                .stream()
                .filter(member -> member.getGradeType().equals(gradeType))
                .collect(Collectors.toList());
    }

    // gradeType equals 확인
    @Override
    public List<Member> findByMemberStatus(MemberStatus memberStatus) {
        return store.values()
                .stream()
                .filter(member -> member.getMemberStatus().equals(memberStatus))
                .collect(Collectors.toList());
    }

    @Override
    public List<Member> findByRoles(RoleType... roleType) {
        return null;
    }


    //==수정 로직==//
    @Override
    public void changeName(Long memberId, String name) {
        store.get(memberId).changeName(name);
    }

    @Override
    public void changePassword(Long memberId, String password) {
        store.get(memberId).changePassword(password);
    }

    @Override
    public void changePhoneNumber(Long memberId, String phoneNumber) {
        store.get(memberId).changePhoneNumber(phoneNumber);

    }

    @Override
    public void changeGradeType(Long memberId, GradeType gradeType) {
        store.get(memberId).changeGradeType(gradeType);

    }

    @Override
    public void changeMemberStatus(Long memberId, MemberStatus memberStatus) {
        store.get(memberId).changeMemberStatus(memberStatus);

    }

    //==삭제 로직==//
    @Override
    public void removeMember(Member member) {
        store.remove(member.getId(), member);
    }

    @Override
    public void removeById(Long id) {
        Member member = store.get(id);
        store.remove(id, member);
    }
}
