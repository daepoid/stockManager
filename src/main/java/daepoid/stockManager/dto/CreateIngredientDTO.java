package daepoid.stockManager.dto;

import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.Data;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class CreateIngredientDTO {

    private Long id;

    // 재료 이름
    private String name;

    // 재료 양
    private Integer quantity;

    // 재료 양 단위
    private UnitType unitType;

    // 단위 가격
    private Double unitPrice;

    // 로스율
    private Double loss;

    // 투입 재료 가격 => 재료 양 * 단위 가격
    // 사용하지 않을 수 있음
    private Double portionPrice;

    private Recipe recipe;
}
