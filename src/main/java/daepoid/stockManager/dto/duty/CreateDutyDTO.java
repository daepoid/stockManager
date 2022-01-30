package daepoid.stockManager.dto.duty;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateDutyDTO {

    @NotBlank
    private String name;

    @NotNull
    private double incentive;

}
