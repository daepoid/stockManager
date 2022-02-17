package daepoid.stockManager.api.dto.menu;

import lombok.Data;

@Data
public class DeleteMenuResponseDTO {

    private Long menuId;

    public DeleteMenuResponseDTO(Long menuId) {
        this.menuId = menuId;
    }
}
