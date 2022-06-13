package daepoid.stockManager.api.dto.ingredient;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

@Data
public class UpdateIngredientResponseDTO {

    private Long ingredientId;

    private int quantity;

    private UnitType unitType;

    private double unitPrice;

    private double loss;

    private double cost;

    public UpdateIngredientResponseDTO(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.quantity = ingredient.getQuantity();
        this.unitType = ingredient.getUnitType();
        this.unitPrice = ingredient.getUnitPrice();
        this.loss = ingredient.getLoss();
        this.cost = ingredient.getCost();
    }
}
