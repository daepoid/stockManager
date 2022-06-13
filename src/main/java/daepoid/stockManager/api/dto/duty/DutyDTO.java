package daepoid.stockManager.api.dto.duty;

import daepoid.stockManager.domain.users.Member;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DutyDTO {

    private Long dutyId;

    private String name;

    private double incentive = 0.0;

    // 직무 타입 (주방, 홀, 재고, 권한)

    // 직무 할당자
    private Set<Member> members = new HashSet<>();

    public DutyDTO(Duty duty) {
        this.dutyId = duty.getId();
        this.name = duty.getName();
        this.incentive = duty.getIncentive();
        this.members = duty.getMembers();
    }

}
