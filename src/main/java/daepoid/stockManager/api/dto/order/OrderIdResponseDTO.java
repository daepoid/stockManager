package daepoid.stockManager.api.dto.order;

import lombok.Data;

@Data
public class OrderIdResponseDTO {

    private Long orderId;

    public OrderIdResponseDTO(Long orderId) {
        this.orderId = orderId;
    }
}
