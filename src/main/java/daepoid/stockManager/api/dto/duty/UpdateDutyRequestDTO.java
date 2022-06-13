package daepoid.stockManager.api.dto.duty;

import daepoid.stockManager.domain.users.Member;
import lombok.Data;

import java.util.Set;

@Data
public class UpdateDutyRequestDTO {

    private String name;

    private Double incentive;

    private Set<Member> members;
}
