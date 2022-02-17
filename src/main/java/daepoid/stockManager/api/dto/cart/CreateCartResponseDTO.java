package daepoid.stockManager.api.dto.cart;

import lombok.Data;

@Data
public class CreateCartResponseDTO {

    private Long cartId;

    public CreateCartResponseDTO(Long cartId) {
        this.cartId = cartId;
    }
}
