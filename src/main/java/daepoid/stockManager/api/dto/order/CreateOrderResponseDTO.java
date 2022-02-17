package daepoid.stockManager.api.dto.order;

import lombok.Data;

@Data
public class CreateOrderResponseDTO {

    private Long orderId;

    public CreateOrderResponseDTO(Long orderId) {
        this.orderId = orderId;
    }
}
