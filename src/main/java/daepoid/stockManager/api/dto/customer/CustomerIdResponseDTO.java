package daepoid.stockManager.api.dto.customer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerIdResponseDTO {

    private Long customerId;

    public CustomerIdResponseDTO(Long customerId) {
        this.customerId = customerId;
    }
}
