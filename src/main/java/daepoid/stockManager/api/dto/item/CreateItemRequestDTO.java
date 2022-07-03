package daepoid.stockManager.api.dto.item;

import daepoid.stockManager.domain.item.ItemType;
import daepoid.stockManager.domain.item.UnitType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateItemRequestDTO {

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

    @NotBlank
    private String countryOfOrigin;

    private String notice;
}
