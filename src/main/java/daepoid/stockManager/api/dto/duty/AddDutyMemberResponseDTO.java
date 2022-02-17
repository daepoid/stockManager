package daepoid.stockManager.api.dto.duty;

import daepoid.stockManager.api.dto.member.MemberDTO;
import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class AddDutyMemberResponseDTO {

    private Long dutyId;

    private String dutyName;

    private Double incentive;

    private List<MemberDTO> dutyMembers;

    public AddDutyMemberResponseDTO(Duty duty) {
        this.dutyId = duty.getId();
        this.dutyName = duty.getName();
        this.incentive = duty.getIncentive();
        this.dutyMembers = duty.getMembers().stream()
                .map(MemberDTO::new)
                .collect(Collectors.toList());
    }
}
