package daepoid.stockManager.api.dto.duty;

import lombok.Data;

@Data
public class DeleteDutyResponseDTO {

    private Long dutyId;

    public DeleteDutyResponseDTO(Long dutyId) {
        this.dutyId = dutyId;
    }
}
