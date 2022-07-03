package daepoid.stockManager.api.dto.order;

import lombok.Data;

@Data
public class CreateOrderRequestDTO {

    private Long customerId;

    private Long foodId;
}
