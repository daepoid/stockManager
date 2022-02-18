package daepoid.stockManager.domain.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ManagerOrderSearch {

    private String customerName;

    private OrderStatus orderStatus;
}
