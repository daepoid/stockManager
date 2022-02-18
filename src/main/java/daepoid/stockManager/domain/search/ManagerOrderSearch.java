package daepoid.stockManager.domain.search;

import daepoid.stockManager.domain.order.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerOrderSearch {

    private String customerName;

    private OrderStatus orderStatus;
}
