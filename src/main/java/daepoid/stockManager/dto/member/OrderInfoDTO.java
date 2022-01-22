package daepoid.stockManager.dto.member;

import daepoid.stockManager.domain.order.Customer;
import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {

    private Long orderId;

    private String customerName;

    private Integer tableNumber;

    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderInfoDTO(Order order) {
        this.orderId = order.getId();
        this.customerName = order.getCustomer().getName();
        this.tableNumber = order.getCustomer().getTableNumber();

        this.orderDateTime = order.getOrderDateTime();
        this.orderStatus = order.getOrderStatus();
    }
}
