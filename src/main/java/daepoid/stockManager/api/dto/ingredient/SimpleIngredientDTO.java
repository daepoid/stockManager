package daepoid.stockManager.api.dto.ingredient;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

@Data
public class SimpleIngredientDTO {

    private Long ingredientId;

    private Long itemId;

    private String name;

    private double quantity;

    private UnitType unitType = UnitType.kg;

    private double unitPrice = 0.0;

    private double loss = 0.0;

    // 재료가 사용되는 레시피 아이디
    private Long foodId;

    // 재료가 사용되는 레시피 이름
    private String recipeName;

    public SimpleIngredientDTO(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.itemId = ingredient.getItem().getId();
        this.name = ingredient.getName();
        this.quantity = ingredient.getQuantity();
        this.unitType = ingredient.getUnitType();
        this.unitPrice = ingredient.getUnitPrice();
        this.loss = ingredient.getLoss();
        this.foodId = ingredient.getFood().getId();
        this.recipeName = ingredient.getFood().getFoodName();
    }
}
