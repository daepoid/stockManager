package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.recipe.MenuStatus;
import lombok.Data;

import java.util.Map;

@Data
public class UpdateMenuRequestDTO {

    private String menuName;

    private Map<Long, Integer> numberOfFoods;

    private int salesCount;

    private MenuStatus menuStatus = MenuStatus.ORDERABLE;
}
