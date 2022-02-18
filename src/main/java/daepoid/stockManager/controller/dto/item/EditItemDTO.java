package daepoid.stockManager.controller.dto.item;

import daepoid.stockManager.domain.item.Item;
import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditItemDTO {

    @NotNull
    private Long id;

    // 재료 이름
    @NotBlank
    private String name;

    // 재료 특성
    @NotNull
    private ItemType itemType;

    // 재료 가격 평균
    @NotNull
    private int price;

    // 패키지 수량
    @NotNull
    private int packageCount;

    // 재고 수량
    @NotNull
    private double quantity;

    // 재고 수량 단위 (g, ml, ...)
    @NotNull
    private UnitType unitType;

    public EditItemDTO (Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.itemType = item.getItemType();
        this.price = item.getPrice();
        this.packageCount = item.getPackageCount();
        this.quantity = item.getQuantity();
        this.unitType = item.getUnitType();
    }
}
