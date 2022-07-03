package daepoid.stockManager.api.dto.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UpdateCustomerRequestDTO {

    @NotBlank
    private String password;

    private String userName;

    private String tableNumber;
}
