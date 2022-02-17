package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCustomerRequestDTO {

    private String name;

    private Integer tableNumber;

    private List<Order> orders;
}
