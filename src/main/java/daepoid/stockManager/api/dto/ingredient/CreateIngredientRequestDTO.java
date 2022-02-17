package daepoid.stockManager.api.dto.ingredient;

import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateIngredientRequestDTO {

    @NotNull
    private Long itemId;

    @NotNull
    private Integer quantity;

    @NotNull
    private UnitType unitType;

    @NotNull
    private double unitPrice;

    private double loss;

    private double cost;

    @NotNull
    private Long recipeId;
}
