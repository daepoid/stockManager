package daepoid.stockManager.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    // 재료 이름
    private String name;

    // 재료 특성
    private ItemType itemType;

    // 재료 가격 평균
    private Integer price;

    // 패키지 수량
    private Integer packageCount;

    // 재고 수량
    private Double quantity;

    // 재고 수량 단위 (g, ml, ...)
    private UnitType unitType;

    // 주의 사항

    // 거래처

    //==생성 메서드==//
    public static Item createItem(String name, ItemType itemType, Integer price, Integer packageCount, Double quantity, UnitType unitType) {
        Item item = new Item();
        item.changeName(name);
        item.changeItemType(itemType);
        item.changePrice(price);
        item.changePackageCount(packageCount);
        item.changeQuantity(quantity);
        item.changeUnitType(unitType);

        return item;
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

    public void changePackageCount(Integer packageCount) {
        this.packageCount = packageCount;
    }

    public void changeQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public void changeUnitType(UnitType unitType) {
        this.unitType = unitType;
    }


    //==조회 로직==//

    public Double getTotalPrice() {
        return price * quantity;
    }
}
