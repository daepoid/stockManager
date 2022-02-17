package daepoid.stockManager.api.dto.cart;

import lombok.Data;

@Data
public class ClearCartResponseDTO {

    private Long cartId;

    public ClearCartResponseDTO(Long cartId) {
        this.cartId = cartId;
    }
}
