package daepoid.stockManager.api.dto.duty;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateDutyResponseDTO {

    private Long dutyId;

    private String name;

    private Double incentive;

    private Set<Member> members;

    public UpdateDutyResponseDTO(Duty duty) {
        this.dutyId = duty.getId();
        this.name = duty.getName();;
        this.incentive = duty.getIncentive();
        this.members = duty.getMembers();
    }
}
