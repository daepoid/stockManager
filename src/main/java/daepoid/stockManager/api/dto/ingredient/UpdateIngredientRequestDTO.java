package daepoid.stockManager.api.dto.ingredient;

import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

@Data
public class UpdateIngredientRequestDTO {

    private int quantity;

    private UnitType unitType;

    private double unitPrice;

    private double loss;
}
