package daepoid.stockManager.api.dto.menu;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingMenuRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
