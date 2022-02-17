package daepoid.stockManager.api.dto.duty;

import lombok.Data;

@Data
public class AddDutyMemberRequestDTO {

    private Long memberId;

    private String loginId;
}
