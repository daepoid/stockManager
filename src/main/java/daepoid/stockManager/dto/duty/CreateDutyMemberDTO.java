package daepoid.stockManager.dto.duty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDutyMemberDTO {

    @NotBlank
    private String name;

    @NotNull
    private Long MemberId;

    public CreateDutyMemberDTO(String name) {
        this.name = name;
    }
}
