package daepoid.stockManager.api.dto.customer;

import lombok.Data;

@Data
public class CreateCustomerResponseDTO {

    private Long customerId;

    public CreateCustomerResponseDTO(Long customerId) {
        this.customerId = customerId;
    }
}
