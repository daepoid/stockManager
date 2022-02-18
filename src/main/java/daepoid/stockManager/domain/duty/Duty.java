package daepoid.stockManager.domain.duty;

import daepoid.stockManager.domain.member.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Duty {

    // 직무 구분 번호
    @Id
    @GeneratedValue
    @Column(name="duty_id")
    private Long id;

    // 직무 이름
    @NotBlank
    private String name;

    // 직무 인센티브
    @NotNull
    private double incentive = 0.0;

    // 직무 타입 (주방, 홀, 재고, 권한)

    // 직무 할당자
    @ManyToMany
    @JoinTable(
            name="duty_members",
            joinColumns=@JoinColumn(name="duty_id"),
            inverseJoinColumns=@JoinColumn(name="member_id")
    )
    private Set<Member> members = new HashSet<>();

    //==생성 메서드(빌더)==//
    @Builder
    public Duty(String name, double incentive, Set<Member> members) {
        this.name = name;
        this.incentive = incentive;
        this.members = members;
    }

    //==개발 로직==//
    public Long changeDutyId(Long id) {
        this.id = id;
        return id;
    }

    //==비즈니스 로직(setter 제거)==//
    public void changeDutyName(String name) {
        this.name = name;
    }

    public void changeDutyMembers(Set<Member> members) {
        this.members = members;
    }

    public void addDutyMember(Member member) {
        this.members.add(member);
    }

    public void removeDutyMember(Member member) {
        this.members.remove(member);
    }

    public void changeDutyIncentive(double incentive) {
        this.incentive = incentive;
    }
}
