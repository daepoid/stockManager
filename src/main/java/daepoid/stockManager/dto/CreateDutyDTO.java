package daepoid.stockManager.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateDutyDTO {

    @NotBlank
    private String name;

    @NotNull
    private Double incentive;

}
