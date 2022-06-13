package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.order.Order;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCustomerRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String tableNumber;

    private Cart cart;

    private List<Order> orders = new ArrayList<>();
}
