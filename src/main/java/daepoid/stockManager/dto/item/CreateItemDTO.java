package daepoid.stockManager.dto.item;

import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateItemDTO {

    // 재료 이름
    @NotBlank
    private String name;

    // 재료 특성
    @NotNull
    private ItemType itemType;

    // 재료 가격 평균
    @NotNull
    private Integer price;

    // 패키지 수량
    @NotNull
    private Integer packageCount;

    // 재고 수량
    @NotNull
    private Double quantity;

    // 재고 수량 단위 (g, ml, ...)
    @NotNull
    private UnitType unitType;
}
