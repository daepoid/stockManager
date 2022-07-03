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
    private Double unitPrice;

    private double loss;

    @NotNull
    private Long foodId;
}
