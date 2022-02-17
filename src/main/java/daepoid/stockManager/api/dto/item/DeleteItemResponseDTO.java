package daepoid.stockManager.api.dto.item;

import lombok.Data;

@Data
public class DeleteItemResponseDTO {

    private Long itemId;

    public DeleteItemResponseDTO(Long itemId) {
        this.itemId = itemId;
    }
}
