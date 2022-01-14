package daepoid.stockManager.domain.item;

import daepoid.stockManager.domain.ingredient.Ingredient;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
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
    private String name;

    // 재료 특성
    private ItemType itemType;

    // 재료 개당 가격 평균
    private Integer price;

    // 수량
    private Double quantity;

    // 단위 (g, ml, ...)
    private UnitType unitType;

    // 패키지 수량 (개, 박스, 통)
    private Integer packageCount;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Ingredient> ingredients = new ArrayList<>();

    // 원산지

    // 주의 사항

    // 거래처

    //==생성 메서드==//
    @Builder
    public Item(String name, ItemType itemType, Integer price, Double quantity,
                UnitType unitType, Integer packageCount, List<Ingredient> ingredients) {
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

    public void changePrice(Integer price) {
        this.price = price;
    }

    public void changeQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void changeUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public void changePackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }

    public void changeIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
