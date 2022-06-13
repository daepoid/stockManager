package daepoid.stockManager.api.dto.menu;

import daepoid.stockManager.domain.food.FoodStatus;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class DeleteMenuRequestDTO {

    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus = FoodStatus.CLOSED;
}
