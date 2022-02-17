package daepoid.stockManager.api.dto.duty;

import lombok.Data;

@Data
public class DeleteDutyMemberResponseDTO {

    private Long dutyId;

    private Long removedMemberId;

    public DeleteDutyMemberResponseDTO(Long dutyId, Long removedMemberId) {
        this.dutyId = dutyId;
        this.removedMemberId = removedMemberId;
    }
}
