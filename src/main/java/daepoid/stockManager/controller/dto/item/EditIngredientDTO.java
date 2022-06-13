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
    private int quantity;

    // 재료 양 단위
    @NotNull
    private UnitType unitType;

    // 단위 가격
    @NotNull
    private double unitPrice;

    // 로스율
    @NotNull
    private double loss;

    @NotNull
    private double cost;

    public EditIngredientDTO(Ingredient ingredient) {
        this.itemId = ingredient.getItem().getId();

        this.quantity = ingredient.getQuantity();
        this.unitType = ingredient.getUnitType();
        this.unitPrice = ingredient.getUnitPrice();

        this.loss = ingredient.getLoss();
        this.cost = ingredient.getCost();
    }
}
