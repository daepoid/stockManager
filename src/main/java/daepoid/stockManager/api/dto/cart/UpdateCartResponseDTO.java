package daepoid.stockManager.api.dto.cart;

import daepoid.stockManager.domain.order.Cart;
import lombok.Data;

import java.util.Map;


@Data
public class UpdateCartResponseDTO {

    private Long cartId;

    private Map<Long, Integer> numberOfMenus;

    private Long customerId;

    public UpdateCartResponseDTO(Cart cart) {
        this.cartId = cart.getId();
        this.numberOfMenus = cart.getNumberOfMenus();
        this.customerId = cart.getCustomer().getId();
    }
}
