package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.food.Menu;
import daepoid.stockManager.domain.food.FoodStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class UpdateMenuResponseDTO {

    private Long menuId;

    private String menuName;

    private int totalPrice;

    private Map<Long, Integer> numberOfFoods = new HashMap<>();

    // 최근에 추가된 메뉴를 확인하기 위해
    private LocalDateTime addedDate;

    // 주문이 많이 된 음식을 찾기 위해
    private int salesCount;

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus = FoodStatus.ORDERABLE;

    public UpdateMenuResponseDTO(Menu menu) {
        this.menuId = menu.getId();
        this.menuName = menu.getName();
        this.totalPrice = menu.getPrice();
        this.numberOfFoods = menu.getNumberOfFoods();
        this.addedDate = menu.getAddedDate();
        this.salesCount = menu.getSalesCount();
        this.foodStatus = menu.getMenuStatus();
    }
}
