package daepoid.stockManager.dto.order;

import daepoid.stockManager.domain.order.OrderMenu;
import daepoid.stockManager.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOrderMenuDTO {

    @NotBlank
    private String nameOfOrderMenu;

    @NotNull
    private int numberOfOrderMenu;

    @NotNull
    private int orderPrice;

    @NotNull
    private LocalDateTime orderedDateTime;

    @NotNull
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
