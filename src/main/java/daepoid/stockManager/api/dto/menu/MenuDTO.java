package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.food.Menu;
import daepoid.stockManager.domain.food.FoodStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class MenuDTO {

    private Long menuId;

    @NotBlank
    private String menuName;

    @NotNull
    private int totalPrice;

    private Map<Long, Integer> numberOfFoods = new HashMap<>();

    // 최근에 추가된 메뉴를 확인하기 위해
    @NotNull
    private LocalDateTime addedDate;

    // 주문이 많이 된 음식을 찾기 위해
    @NotNull
    private int salesCount;

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus = FoodStatus.ORDERABLE;

    public MenuDTO(Menu menu) {
        this.menuId = menu.getId();
        this.menuName = menu.getName();
        this.totalPrice = menu.getPrice();
        this.numberOfFoods = menu.getNumberOfFoods();
        this.addedDate = menu.getAddedDate();
        this.salesCount = menu.getSalesCount();
        this.foodStatus = menu.getMenuStatus();
    }
}
