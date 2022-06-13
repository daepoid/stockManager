package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.food.FoodStatus;
import lombok.Data;

import java.util.Map;

@Data
public class UpdatePatchMenuRequestDTO {

    private String menuName;

    private Map<Long, Integer> numberOfFoods;

    private Integer salesCount;

    private FoodStatus foodStatus = FoodStatus.ORDERABLE;
}
