package daepoid.stockManager.api.dto.order;

import daepoid.stockManager.domain.order.OrderMenu;

import lombok.Data;

@Data
public class OrderMenuDTO {

    private String menuName;

    private double orderPrice;

    private int orderCount;

    public OrderMenuDTO(OrderMenu orderMenu) {
        this.menuName = orderMenu.getFood().getFoodName();
        this.orderPrice = orderMenu.getOrderPrice();
        this.orderCount = orderMenu.getOrderCount();
    }
}
