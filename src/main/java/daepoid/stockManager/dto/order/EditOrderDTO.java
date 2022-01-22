package daepoid.stockManager.dto.order;

import daepoid.stockManager.domain.order.Order;
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
public class EditOrderDTO {

    private String customerName;

    private Long customerId;

    private String nameOfOrderMenu;

    private Integer numberOfOrderMenu;

    private LocalDateTime orderDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public EditOrderDTO(OrderMenu orderMenu) {
        this.customerName = orderMenu.getOrder().getCustomer().getName();
        this.customerId = orderMenu.getOrder().getCustomer().getId();

        this.nameOfOrderMenu = orderMenu.getMenu().getName();
        this.numberOfOrderMenu = orderMenu.getOrderCount();
        this.orderDateTime = orderMenu.getOrder().getOrderDateTime();
        this.orderStatus = orderMenu.getOrder().getOrderStatus();
    }
}
