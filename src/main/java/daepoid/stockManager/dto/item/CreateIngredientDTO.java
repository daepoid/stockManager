package daepoid.stockManager.dto.item;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateIngredientDTO {

    // 재료 이름
    @NotNull
    private Long itemId;

    // 재료 양
    @NotNull
    private int quantity;

    // 재료 양 단위
    @NotNull
    private UnitType unitType;

    // 로스율에 기반한 단위 가격
    @NotNull
    private double unitPrice;

    // 로스율
    @NotNull
    private double loss;
}
