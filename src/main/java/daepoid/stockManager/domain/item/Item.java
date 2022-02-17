package daepoid.stockManager.domain.item;

import daepoid.stockManager.domain.ingredient.Ingredient;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    // 재료 이름
    @NotBlank
    private String name;

    // 재료 특성
    @NotNull
    private ItemType itemType;

    // 재료 개당 가격 평균
    @NotNull
    private int price;

    // 수량
    @NotNull
    private double quantity;

    // 단위 (g, ml, ...)
    @NotNull
    private UnitType unitType;

    // 패키지 수량 (개, 박스, 통)
    @NotNull
    private int packageCount;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    // 원산지

    // 주의 사항

    // 거래처

    //==생성 메서드==//
    @Builder
    public Item(String name, ItemType itemType, int price, double quantity,
                UnitType unitType, int packageCount, List<Ingredient> ingredients) {
        this.name = name;

        this.itemType = itemType;

        this.price = price;

        this.quantity = quantity;
        this.unitType = unitType;

        this.packageCount = packageCount;

        this.ingredients = ingredients;
    }

    //==개발 로직==//
    public void changeId(Long id) {
        this.id = id;
    }

    //==비즈니스 로직==//
    public void changeName(String name) {
        this.name = name;
    }

    public void changeItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void changePrice(int price) {
        this.price = price;
    }

    public void changeQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void changeUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void changePackageCount(int packageCount) {
        this.packageCount = packageCount;
    }

    public void changeIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.changeItem(this); // 이 부분이 필요한가?
    }
}
