package daepoid.stockManager.domain.ingredient;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@NoArgsConstructor
public class Ingredient {

    @Id @GeneratedValue
    @Column(name = "ingredient_id")
    private Long id;

    // 재료 이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    @NotNull
    private Item item;

    // 재료 이름
    @NotBlank
    private String name;

    // 재료 양
    @NotNull
    private int quantity = 0;

    // 재료 양 단위
    @NotNull
    private UnitType unitType = UnitType.kg;

    // 로스율에 기반한 단위 가격
    @NotNull
    private double unitPrice = 0.0;

    // 로스율
    @NotNull
    private double loss = 0.0;

    // 투입 재료 가격 => 재료 양 * 단위 가격
    // 사용하지 않을 수 있음
    @NotNull
    private double cost = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    @Builder
    public Ingredient(Item item, String name, int quantity, UnitType unitType,
                      double unitPrice, double loss, double cost, Recipe recipe) {
        this.item = item;
        this.name = name;

        this.quantity = quantity;
        this.unitType = unitType;

        this.unitPrice = unitPrice;
        this.loss = loss;
        this.cost = cost;

        this.recipe = recipe;
    }

    //==조회 로직==//
    public String getName() {
        this.name = item.getName();
        return name;
    }

    public double getCost() {
        updateCost();
        return this.cost;
    }

    //==portion price 업데이트==//
    public void updateCost() {
        this.cost = getQuantity() * getUnitPrice();
    }

    //==개발 로직==//
    public void changeId(Long id) {
        this.id = id;
    }

    //==비즈니스 로직 (setter 제거)==//
    public void changeItem(Item item) {
        this.item = item;
        item.getIngredients().add(this);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeName() {
        if(item != null && item.getName() != null) {
            this.name = item.getName();
        }
    }

    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void changeUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void changeUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void changeLoss(double loss) {
        this.loss = loss;
    }

    public void changeCost(double cost) {
        this.cost = cost;
    }

    public void changeRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.getIngredients().add(this);
    }
}
