package daepoid.stockManager.domain.food;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Getter
@NoArgsConstructor
public class Ingredient {

    @Id @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    // 재료 이름
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 재료 이름
    @NotBlank
    private String name;

    // 재료 양
    @NotNull
    @Positive
    private Double quantity = 0.0;

    // 재료 양 단위
    @NotNull
    private UnitType unitType = UnitType.kg;

    // 로스율에 기반한 단위 가격
    @NotNull
    @Positive
    private Double unitPrice = 0.0;

    // 로스율
    @NotNull
    @PositiveOrZero
    private Double loss = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Food food;


    @Builder
    public Ingredient(Item item, String name, Double quantity, UnitType unitType,
                      Double unitPrice, Double loss, Food food) {
        this.item = item;
        this.name = name;

        this.quantity = quantity;
        this.unitType = unitType;

        this.unitPrice = unitPrice;
        this.loss = loss;

        this.food = food;
    }

    //==조회 로직==//
    public String getName() {
        updateName();
        return this.name;
    }

    //== item name 으로 업데이트 후 반환 ==//
    public void updateName() {
        this.name = item.getName();
    }

    public void changeQuantity(Double quantity) {
        double temp = this.food.getFoodCost() - this.quantity * this.unitPrice;
        this.food.changeFoodCost(temp + quantity * this.unitPrice);
        this.quantity = quantity;
    }

    public void changeUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void changeUnitPrice(Double unitPrice) {
        double temp = this.food.getFoodCost() - this.quantity * this.unitPrice;
        this.food.changeFoodCost(temp + this.quantity * unitPrice);
        this.unitPrice = unitPrice;
    }

    public void changeLoss(Double loss) {
        this.loss = loss;
    }

    public void changeFood(Food food) {
        this.food = food;
    }
}
