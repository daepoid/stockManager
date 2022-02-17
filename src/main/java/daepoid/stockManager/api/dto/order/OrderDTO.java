package daepoid.stockManager.api.dto.order;

import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDTO {

    private Long orderId;

    private String customerName;

    private List<OrderMenuDTO> orderMenus;

    private LocalDateTime orderDateTime;

    private OrderStatus orderStatus;

    public OrderDTO(Order order) {
        this.orderId = order.getId();
        this.customerName = order.getCustomer().getName();
        this.orderMenus = order.getOrderMenus().stream()
                .map(OrderMenuDTO::new)
                .collect(Collectors.toList());
        this.orderDateTime = order.getOrderDateTime();
        this.orderStatus = order.getOrderStatus();
    }
}
