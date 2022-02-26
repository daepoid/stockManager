package daepoid.stockManager.api.dto.ingredient;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagingIngredientRequestDTO {

    private Integer firstResult;

    @NotNull
    private Integer maxResult;
}
