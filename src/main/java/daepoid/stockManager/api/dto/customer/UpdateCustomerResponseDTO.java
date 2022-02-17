package daepoid.stockManager.api.dto.customer;

import daepoid.stockManager.domain.order.Cart;
import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import lombok.Data;

import java.util.List;

@Data
public class UpdateCustomerResponseDTO {
    private Long customerId;

    private String userName;

    private String tableNumber;

    private Cart cart;

    private List<Order> orders;

    public UpdateCustomerResponseDTO(Customer customer) {
        this.customerId = customer.getId();
        this.userName = customer.getUserName();
        this.tableNumber = customer.getTableNumber();
        this.cart = customer.getCart();
        this.orders = customer.getOrders();
    }
}
