package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.recipe.MenuStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Data
public class CreateMenuRequestDTO {

    @NotBlank
    private String menuName;

    private Map<Long, Integer> numberOfFoods = new HashMap<>();

    // 주문이 많이 된 음식을 찾기 위해
    private int salesCount;

    @Enumerated(EnumType.STRING)
    private MenuStatus menuStatus = MenuStatus.ORDERABLE;
}
