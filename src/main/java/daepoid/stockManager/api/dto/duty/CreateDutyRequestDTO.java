package daepoid.stockManager.api.dto.duty;

import daepoid.stockManager.domain.member.Member;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class CreateDutyRequestDTO {

    @NotBlank
    private String name;

    @NotNull
    private Double incentive;

    // 직무 타입 (주방, 홀, 재고, 권한)
    private Set<Member> members;
}
