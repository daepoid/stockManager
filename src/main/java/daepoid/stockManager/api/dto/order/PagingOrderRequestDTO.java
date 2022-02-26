package daepoid.stockManager.api.dto.order;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingOrderRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
