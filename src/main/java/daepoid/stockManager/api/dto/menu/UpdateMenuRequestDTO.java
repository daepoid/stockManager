package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.food.FoodStatus;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateMenuRequestDTO {

    private String menuName;

    private Map<Long, Integer> numberOfFoods;

    private int salesCount;

    private FoodStatus foodStatus = FoodStatus.ORDERABLE;
}
