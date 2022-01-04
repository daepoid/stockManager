package daepoid.stockManager.domain.ingredient;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.UnitType;
import daepoid.stockManager.domain.recipe.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Item item;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;


    public Ingredient(String name, Integer quantity, UnitType unitType, Double unitPrice, Double loss) {
        this.name = name;
        this.quantity = quantity;
        this.unitType = unitType;
        this.unitPrice = unitPrice;
        this.loss = loss;
        this.portionPrice = quantity * unitPrice;
    }

    public void changeId(Long id) {
        this.id = id;
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeQuantity(Integer quantity) {
        this.quantity = quantity;
//        updatePortionPrice();
    }

    public void changeUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void changeUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
//        updatePortionPrice();
    }

    public void changeLoss(Double loss) {
        this.loss = loss;
    }

    public void changeRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Double getCost() {
        return getQuantity() * getUnitPrice();
    }

    public void updatePortionPrice() {
        this.portionPrice = getCost();
    }

    public static Ingredient createIngredient(String name, Integer quantity, UnitType unitType, Double unitPrice, Double loss) {
        Ingredient ingredient = new Ingredient();
        ingredient.changeName(name);
        ingredient.changeQuantity(quantity);
        ingredient.changeUnitType(unitType);
        ingredient.changeUnitPrice(unitPrice);
        ingredient.changeLoss(loss);
        ingredient.updatePortionPrice();

        return ingredient;
    }
}
