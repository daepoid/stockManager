package daepoid.stockManager.api.dto.customer;

import lombok.Data;

@Data
public class DeleteCustomerResponseDTO {

    private Long customerId;

    private Long cartId;

    public DeleteCustomerResponseDTO(Long customerId, Long cartId) {
        this.customerId = customerId;
        this.cartId = cartId;
    }
}
