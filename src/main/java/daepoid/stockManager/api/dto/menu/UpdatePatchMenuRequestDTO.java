package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.recipe.MenuStatus;
import lombok.Data;

import java.util.Map;

@Data
public class UpdatePatchMenuRequestDTO {

    private String menuName;

    private Map<Long, Integer> numberOfFoods;

    private Integer salesCount;

    private MenuStatus menuStatus = MenuStatus.ORDERABLE;
}
