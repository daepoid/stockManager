package daepoid.stockManager.dto.member;

import daepoid.stockManager.domain.order.Order;
import daepoid.stockManager.domain.order.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfoDTO {

    @NotNull
    private Long orderId;

    @NotBlank
    private String customerName;

    @NotNull
    private int tableNumber;

    @NotNull
    private LocalDateTime orderDateTime;

    @NotNull
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
