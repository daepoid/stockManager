package daepoid.stockManager.api.dto.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteCustomerRequestDTO {

    @NotBlank
    private String password;
}
