package daepoid.stockManager.api.dto.item;

import lombok.Data;

@Data
public class CreateItemResponseDTO {

    private Long itemId;

    public CreateItemResponseDTO(Long itemId) {
        this.itemId = itemId;
    }
}
