package daepoid.stockManager.api.dto.recipe;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingRecipeRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
