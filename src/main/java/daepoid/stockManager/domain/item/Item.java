package daepoid.stockManager.domain.item;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

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
    @Positive
    private Integer price = 0;

    // 수량
    @NotNull
    @PositiveOrZero
    private Double quantity = 0.0;

    // 단위 (g, ml, ...)
    @NotNull
    private UnitType unitType = UnitType.kg;

    // 패키지 수량 (박스, 통)
    @NotNull
    @PositiveOrZero
    private Integer packageCount = 0;

    // 원산지
    @NotBlank
    private String countryOfOrigin;

    // 주의 사항
    private String notice;

    // 거래처

    //==생성 메서드==//
    @Builder
    public Item(String name, ItemType itemType, Integer price,
                Double quantity, UnitType unitType,
                Integer packageCount, String countryOfOrigin, String notice) {
        this.name = name;

        this.itemType = itemType;

        this.price = price;

        this.quantity = quantity;
        this.unitType = unitType;

        this.packageCount = packageCount;
        this.countryOfOrigin = countryOfOrigin;
        this.notice = notice;
    }

    //==개발 로직==//

    //==비즈니스 로직==//
    public void changeName(String name) {
        this.name = name;
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

    public void changeCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public void changeNotice(String notice) {
        this.notice = notice;
    }
}
