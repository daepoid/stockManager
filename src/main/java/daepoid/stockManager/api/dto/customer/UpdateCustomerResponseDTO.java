package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCustomerResponseDTO {
    private Long customerId;

    private String name;

    private int tableNumber;

    private Long cartId;

    private List<Order> orders;

    public UpdateCustomerResponseDTO(Customer customer) {
        this.customerId = customer.getId();
        this.name = customer.getName();
        this.tableNumber = customer.getTableNumber();
        this.cartId = customer.getCart().getId();
        this.orders = customer.getOrders();
    }
}
