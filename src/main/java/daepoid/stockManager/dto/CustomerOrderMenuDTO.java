package daepoid.stockManager.dto;

import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderMenuDTO {

    private String nameOfOrderMenu;

    private Integer numberOfOrderMenu;

    private Integer orderPrice;

    private LocalDateTime orderedDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public CustomerOrderMenuDTO(OrderMenu orderMenu) {
        this.nameOfOrderMenu = orderMenu.getMenu().getName();
        this.numberOfOrderMenu = orderMenu.getOrderCount();
        this.orderPrice = orderMenu.getOrderPrice();
        this.orderedDateTime = orderMenu.getOrder().getOrderDateTime();
        this.orderStatus = orderMenu.getOrder().getOrderStatus();
    }
}
