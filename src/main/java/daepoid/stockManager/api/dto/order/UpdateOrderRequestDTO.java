package daepoid.stockManager.api.dto.order;

import daepoid.stockManager.domain.order.OrderStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateOrderRequestDTO {

    @NotNull
    OrderStatus orderStatus;
}
