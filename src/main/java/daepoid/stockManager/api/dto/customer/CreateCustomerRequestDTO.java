package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Order;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class CreateCustomerRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotNull
    private int tableNumber;

    private Cart cart;

    private List<Order> orders = new ArrayList<>();
}
