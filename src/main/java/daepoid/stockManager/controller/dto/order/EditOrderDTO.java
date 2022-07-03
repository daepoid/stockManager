package daepoid.stockManager.controller.dto.order;

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
public class EditOrderDTO {

    @NotBlank
    private String customerName;

    @NotNull
    private Long customerId;

    @NotBlank
    private String nameOfOrderFood;

    @NotNull
    private int numberOfOrderFood;

    @NotNull
    private LocalDateTime orderDateTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public EditOrderDTO(OrderMenu orderMenu) {
        this.customerName = orderMenu.getOrder().getCustomer().getUserName();
        this.customerId = orderMenu.getOrder().getCustomer().getId();

        this.nameOfOrderFood = orderMenu.getFood().getFoodName();
        this.numberOfOrderFood = orderMenu.getOrderCount();
        this.orderDateTime = orderMenu.getOrder().getOrderDateTime();
        this.orderStatus = orderMenu.getOrder().getOrderStatus();
    }
}
