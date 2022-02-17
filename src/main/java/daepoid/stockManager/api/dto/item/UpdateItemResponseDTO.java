package daepoid.stockManager.api.dto.item;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

@Data
public class UpdateItemResponseDTO {

    // 재료 이름
    private String name;

    // 재료 특성
    private ItemType itemType;

    // 재료 개당 가격 평균
    private int price;

    // 수량
    private double quantity;

    // 단위 (g, ml, ...)
    private UnitType unitType;

    // 패키지 수량 (개, 박스, 통)
    private int packageCount;

    public UpdateItemResponseDTO(Item item) {
        this.name = item.getName();
        this.itemType = item.getItemType();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();
        this.unitType = item.getUnitType();
        this.packageCount = item.getPackageCount();
    }
}
