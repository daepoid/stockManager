package daepoid.stockManager.controller.dto.order;

import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;
import daepoid.stockManager.domain.users.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderDTO {

    private Customer customer;

    @NotNull
    private LocalDateTime orderDateTime;

    @NotNull
    private OrderStatus orderStatus;

    @NotNull
    private Double totalOrderPrice;

    private List<OrderMenu> orderMenus = new ArrayList<>();

    public CustomerOrderDTO(Order order) {
        this.customer = order.getCustomer();
        this.orderDateTime = order.getOrderDateTime();
        this.orderStatus = order.getOrderStatus();
        this.totalOrderPrice = order.getTotalOrderPrice();
        this.orderMenus = order.getOrderMenus();
    }
}
