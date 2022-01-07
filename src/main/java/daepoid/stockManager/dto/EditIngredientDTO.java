package daepoid.stockManager.dto;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.ingredient.Ingredient;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditIngredientDTO {

    // 재료 이름
    private Long itemId;

    private String name;

    // 재료 양
    private Integer quantity;

    // 재료 양 단위
    private UnitType unitType;

    // 단위 가격
    private Double unitPrice;

    // 로스율
    private Double loss;

    private Double cost;

    private Recipe recipe;

    public EditIngredientDTO(Ingredient ingredient) {
        this.itemId = ingredient.getItem().getId();
        this.name = ingredient.getName();

        this.quantity = ingredient.getQuantity();
        this.unitType = ingredient.getUnitType();
        this.unitPrice = ingredient.getUnitPrice();

        this.loss = ingredient.getLoss();
        this.cost = ingredient.getCost();
        this.recipe = ingredient.getRecipe();
    }
}
