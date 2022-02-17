package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Order;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UpdateCustomerRequestDTO {

    private String name;

    private String tableNumber;

    private Cart cart;

    private List<Order> orders;
}
