package daepoid.stockManager.api.dto.customer;

import lombok.Data;

@Data
public class DeleteCustomerResponseDTO {

    private Long customerId;

    public DeleteCustomerResponseDTO(Long customerId) {
        this.customerId = customerId;
    }
}
