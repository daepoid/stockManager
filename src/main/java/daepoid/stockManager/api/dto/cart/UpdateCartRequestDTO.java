package daepoid.stockManager.api.dto.cart;

import lombok.Data;

import java.util.Map;

@Data
public class UpdateCartRequestDTO {

    Map<Long, Integer> numberOfMenus;
}
