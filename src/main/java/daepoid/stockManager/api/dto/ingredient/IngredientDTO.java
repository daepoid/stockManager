package daepoid.stockManager.api.dto.ingredient;

import daepoid.stockManager.domain.food.Ingredient;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

@Data
public class IngredientDTO {

    private Long ingredientId;

    private Long itemId;

    private String name;

    private int quantity;

    private UnitType unitType = UnitType.kg;

    private double unitPrice = 0.0;

    private double loss = 0.0;

    // 투입 재료 가격 => 재료 양 * 단위 가격
    private double cost = 0.0;

    // 재료가 사용되는 레시피 아이디
    private Long recipeId;

    // 재료가 사용되는 레시피 이름
    private String recipeName;

    public IngredientDTO(Ingredient ingredient) {
        this.ingredientId = ingredient.getId();
        this.itemId = ingredient.getItem().getId();
        this.name = ingredient.getName();
        this.quantity = ingredient.getQuantity();
        this.unitType = ingredient.getUnitType();
        this.unitPrice = ingredient.getUnitPrice();
        this.loss = ingredient.getLoss();
        this.cost = ingredient.getCost();
        this.recipeId = ingredient.getFood().getId();
        this.recipeName = ingredient.getFood().getName();
    }
}
