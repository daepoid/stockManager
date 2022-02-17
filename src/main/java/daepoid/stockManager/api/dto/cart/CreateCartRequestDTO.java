package daepoid.stockManager.api.dto.cart;

import lombok.Data;

import java.util.Map;

@Data
public class CreateCartRequestDTO {

    private Long customerId;

    private Map<Long, Integer> numberOfMenus;

}
