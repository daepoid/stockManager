package daepoid.stockManager.controller.dto.duty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDutyMemberDTO {

    private String dutyName;

    @NotNull
    private Long MemberId;

    public CreateDutyMemberDTO(String dutyName) {
        this.dutyName = dutyName;
    }
}
