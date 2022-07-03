package daepoid.stockManager.api.dto.customer;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateCustomerRequestDTO {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;

    @NotBlank
    private String tableNumber;
}
