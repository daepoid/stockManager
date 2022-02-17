package daepoid.stockManager.api.dto.duty;

import lombok.Data;

@Data
public class CreateDutyResponseDTO {

    private Long dutyId;

    public CreateDutyResponseDTO(Long dutyId) {
        this.dutyId = dutyId;
    }
}
