package daepoid.stockManager.api.dto.item;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingItemRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
