package daepoid.stockManager.api.dto.order;

import daepoid.stockManager.domain.order.OrderMenu;

import lombok.Data;

@Data
public class OrderMenuDTO {

    private String menuName;

    private int orderPrice;

    private int orderCount;

    public OrderMenuDTO(OrderMenu orderMenu) {
        this.menuName = orderMenu.getMenu().getName();
        this.orderPrice = orderMenu.getOrderPrice();
        this.orderCount = orderMenu.getOrderCount();
    }
}
