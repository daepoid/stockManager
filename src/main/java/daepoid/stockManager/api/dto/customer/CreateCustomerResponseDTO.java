package daepoid.stockManager.api.dto.customer;

import lombok.Data;

@Data
public class CreateCustomerResponseDTO {

    private Long customerId;

    private Long cartId;

    public CreateCustomerResponseDTO(Long customerId, Long cartId) {
        this.customerId = customerId;
        this.cartId = cartId;
    }
}
