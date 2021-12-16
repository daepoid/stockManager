package daepoid.stockManager.domain.recipe;

import daepoid.stockManager.domain.item.UnitType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Ingredient {

    @Id @GeneratedValue
    @Column(name = "ingredient_id")
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
    private Double portionPrice;

    public Ingredient(String name, Integer quantity, UnitType unitType,Double unitPrice, Double loss) {
        this.name = name;
        this.quantity = quantity;
        this.unitType = unitType;
        this.unitPrice = unitPrice;
        this.loss = loss;
        this.portionPrice = quantity * unitPrice;
    }
}
