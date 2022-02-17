package daepoid.stockManager.api.dto.cart;

import daepoid.stockManager.domain.order.Cart;
import lombok.Data;

import java.util.Map;

@Data
public class AddCartResponseDTO {

    private Long cartId;

    private Map<Long, Integer> numberOfMenus;

    public AddCartResponseDTO(Cart cart) {
        this.cartId = cart.getId();
        this.numberOfMenus = cart.getNumberOfMenus();
    }
}
