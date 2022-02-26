package daepoid.stockManager.api.dto.duty;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingDutyRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
