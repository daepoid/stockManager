package daepoid.stockManager.controller.dto.item;

import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.food.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditIngredientDTO {

    // 재료 이름
    // ingredient.getItem().getId();
    @NotNull
    private Long itemId;

    // ingredient.getName();

    // 재료 양
    @NotNull
    private Double quantity;

    // 재료 양 단위
    @NotNull
    private UnitType unitType;

    public EditIngredientDTO(Ingredient ingredient) {
        this.itemId = ingredient.getItem().getId();

        this.quantity = ingredient.getQuantity();
        this.unitType = ingredient.getUnitType();
    }
}
