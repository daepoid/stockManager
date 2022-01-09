package daepoid.stockManager.dto;

import daepoid.stockManager.domain.duty.Duty;
import daepoid.stockManager.domain.member.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditDutyDTO {

    @NotNull
    private Long id;

    // 직무 이름
    @NotBlank
    private String name;

    // 직무 인센티브
    @NotNull
    private Double incentive;

    // 직무 할당자
    private List<Member> members = new ArrayList<>();

    public EditDutyDTO(Duty duty) {
        this.id = duty.getId();
        this.name = duty.getName();
        this.incentive = duty.getIncentive();
//        this.members = duty.getMembers();
    }
}
