package daepoid.stockManager.api.dto.item;

import lombok.Data;

@Data
public class ItemIdResponseDTO {

    private Long itemId;

    public ItemIdResponseDTO(Long itemId) {
        this.itemId = itemId;
    }
}
