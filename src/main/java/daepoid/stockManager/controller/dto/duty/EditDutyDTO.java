package daepoid.stockManager.controller.dto.duty;

import daepoid.stockManager.domain.users.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditDutyDTO {

    @NotNull
    private Long id;

    // 직무 이름
    @NotBlank
    private String dutyName;

    // 직무 인센티브
    @NotNull
    private double incentive;

    // 직무 할당자
    private Set<Member> members = new HashSet<>();

    public EditDutyDTO(Duty duty) {
        this.id = duty.getId();
        this.dutyName = duty.getName();
        this.incentive = duty.getIncentive();
        this.members = duty.getMembers();
    }
}
