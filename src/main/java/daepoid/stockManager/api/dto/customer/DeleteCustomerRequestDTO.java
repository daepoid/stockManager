package daepoid.stockManager.api.dto.customer;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DeleteCustomerRequestDTO {

    @NotBlank
    private String password;
}
