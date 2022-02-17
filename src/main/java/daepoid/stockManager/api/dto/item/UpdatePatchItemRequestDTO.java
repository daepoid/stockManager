package daepoid.stockManager.api.dto.item;

import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePatchItemRequestDTO {

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
}
