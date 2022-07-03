package daepoid.stockManager.api.dto.ingredient;

import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

@Data
public class UpdateIngredientRequestDTO {

    private Integer quantity;

    private UnitType unitType;

    private Double unitPrice;

    private Double loss;
}
